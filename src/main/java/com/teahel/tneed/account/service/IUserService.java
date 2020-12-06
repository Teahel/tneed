package com.teahel.tneed.account.service;

import com.teahel.tneed.account.entity.User;

public interface IUserService {

    /**
     * 保存账户
     * @param user 账户信息
     * @return 保存结果
     */
    public User saveUser(User user);
}
