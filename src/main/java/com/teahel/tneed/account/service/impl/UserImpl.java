package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.common.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    public User saveUser(User user) {
        User exitUser = findUser(user);
        if( exitUser != null){
           throw new RuntimeException("不允许出现重复名称");
        }
        /**
         * 对账户密码加密保存
         * 使用默认bcrypt加密方式
         */
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
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


}
