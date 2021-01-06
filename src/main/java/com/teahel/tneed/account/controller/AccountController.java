package com.teahel.tneed.account.controller;

import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.common.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-12-29
 */

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    IUserService userService;

    /**
     * 查询账户信息
     * @param user 查询条件：账户名称
     * @return 查询结果
     */
    @PostMapping("/select")
    public ResultUtils select(@RequestBody User user){
        User u = userService.findUser(user);
        return ResultUtils.ok(u);
    }

    /*@PostMapping("/update")
    public ResultUtils update(@RequestBody User user){

    }*/


}
