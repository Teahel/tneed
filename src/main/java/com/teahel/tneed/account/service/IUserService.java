package com.teahel.tneed.account.service;

import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.common.ResultUtils;

import java.util.List;

public interface IUserService {

    /**
     * 保存账户
     * @param user 账户信息
     * @return 保存结果
     */
    public ResultUtils saveUser(User user);

    /**
     * 查询账户信息
     * @param user 查询条件：用户名
     * @return 查询结果
     */
    public User findUser(User user);

    /**
     * 修改账户密码
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param username 账户
     * @return 操作结果
     */
    void updateUsername(String password,String oldPassword,String username);

    /**
     * 根据账户名称查询账户
     * @param username 账户名称
     * @return 查询结果
     */
    User findByUsername(String username);

    /**
     * 重置密码
     * @param username 账户
     */
    void resetPassword(String username);

    /**
     * 查询是否存在邀请码
     * @param inviteCode 邀请码
     * @return 结果返回
     */
    User findByInviteCode(String inviteCode);

    /**
     * 查询所有的账户
     * 用户信息
     * @return 返回数据
     */
    List<User> findUsers(User user);

    /**
     * 支付成功后发送邮件
     * @param username 用户名
     * @return 执行结果
     */
    void alreadyPay(String username);
}
