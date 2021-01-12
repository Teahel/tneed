package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

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
     * @param password 密码
     * @param username 用户名
     */
    @Override
    public void updateUser(String password,String username) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String newPassword = encoder.encode(password);
        userRepository.updateUser(newPassword,username);
    }


}
