package com.teahel.tneed.account.controller;

import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.common.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-12-29
 */

@RestController
public class AccountController {

    @Autowired
    IUserService userService;

    /**
     * 查询账户信息
     * @return 查询结果
     */
    @PostMapping("/user/select")
    public ResultUtils select(@RequestBody User user){
        List<User> users = userService.findUsers(user);
        return ResultUtils.ok(users);
    }

    /**
     * 修改账户密码
     * user 用户信息
     * @return 操作结果
     */
    @PostMapping("/user/update")
    public ResultUtils update(@RequestBody User user){
        userService.updateUsername(user.getPassword(),user.getOldPassword(),user.getUsername());
        return ResultUtils.ok();
    }

    /**
     * 重置密码
     * @param email  邮箱
     * @return 操作结果
     */
    @PostMapping("/userinfo/resetPassword")
    public ResultUtils resetPassword(String email){
        if(userService.findByUsername(email) == null){
           return ResultUtils.error("用户不存在");
        };
        userService.resetPassword(email);
        return ResultUtils.ok();
    }

    /**
     * 注册
     * @param user 用户信息
     * @return 操作结果
     */
    @PostMapping("/happy")
    public ResultUtils saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 支付成功后发送邮件
     * @param username 用户名
     * @return 执行结果
     */
    @PostMapping("/pay")
    public ResultUtils alreadyPay(String username) {
       userService.alreadyPay(username);
       return ResultUtils.ok();
    }


}
