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
        /**
         * 对账户密码加密保存
         * 使用默认bcrypt加密方式
         */
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
