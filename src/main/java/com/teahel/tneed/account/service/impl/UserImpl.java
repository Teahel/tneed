package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    final EntityManager entityManager;

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


}
