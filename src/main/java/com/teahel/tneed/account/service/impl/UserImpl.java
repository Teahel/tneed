package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.Role;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.common.EmailUtils;
import com.teahel.tneed.common.ResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    final EntityManager entityManager;

    @Autowired
    private EmailUtils emailUtils;


    /**
     * 保存账户
     * @param user 账户信息
     * @return 保存结果
      */
    @Override
    public ResultUtils saveUser(User user) {
        try{
            User exitUser = findUser(user);
            if (exitUser != null) {
                log.error("不允许出现重复名称: ",user.getUsername());
                return ResultUtils.error("不允许出现重复名称");
            }

            User inviteUser = findByInviteCode(user.getInviteCode());
            if (inviteUser == null) {
                log.error("邀请码不存在: ",user.getInviteCode());
                return ResultUtils.error("邀请码不存在");
            }

            //生产邀请码 10位 数字和字母
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String password = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //随机密码生产之后发送目标账户邮箱
            emailUtils.sendEmail(user.getUsername(),"这是你的Tneed学习码,如你不是账户主人,请忽略以下数字码  \n\n "+password);

            /**
             * 对账户密码加密保存
             * 使用默认bcrypt加密方式
             * 邀请码亦是初始密码
             */
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(encoder.encode(password));

            String inviteCode = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            user.setInviteCode(inviteCode);

            userRepository.save(new User(user.getUsername(),user.getPassword(), Arrays.asList(new Role("ADMIN"),new Role("USER")),
                    LocalDateTime.now(),LocalDateTime.now(),user.getInviteCode()));
        }catch (Exception e){
            log.error("保存账户异常",e);
            return ResultUtils.error("保存账户异常");
        }
        return ResultUtils.ok();
    }

    /**
     * 查询账户信息
     *
     * @param user 查询条件：用户名
     * @return 查询结果
     */
    @Override
    public User findUser(User user) {
        return  userRepository.findByUsername(user.getUsername());
    }

    /**
     * 修改账户密码
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param username 账户
     * @return 操作结果
     */
    @Override
    public void updateUsername(String password,String oldPassword,String username){
        User user = userRepository.findByUsername(username);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if(user == null || !encoder.matches(oldPassword,user.getPassword())){
            throw new RuntimeException("请输入正确的原密码！");
        }
        if(encoder.matches(password,user.getPassword())){
            throw new RuntimeException("原密码与新密码相同！");
        }
        userRepository.updateUser(encoder.encode(password),username);
    }

    /**
     * 根据账户名称查询账户
     *
     * @param username 账户名称
     * @return 查询结果
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 重置密码
     *
     * @param username 账户
     */
    @Override
    public void resetPassword(String username) {
        try{
            //生产随机密码 10位 数字和字母
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String password = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            //随机密码生产之后发送目标账户邮箱
            emailUtils.sendEmail(username,"这是你的新密码,如你不是账户主任,请忽略一下密码  \n\n "+password);

            //保存重置后密码
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            userRepository.updateUser(encoder.encode(password),username);
        }catch (Exception e){
            log.error("密码重置异常");
            throw new RuntimeException("密码重置错误:"+username);
        }

    }

    /**
     * 查询是否存在邀请码
     *
     * @param inviteCode 邀请码
     * @return 结果返回
     */
    @Override
    public User findByInviteCode(String inviteCode) {
        return userRepository.findByInviteCode(inviteCode);
    }


}
