package com.teahel.tneed.account.controller;


import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.common.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /**
     * 查询账户信息
     * @param user 查询条件：账户名称
     * @return 查询结果
     */
    @RequestMapping("/userinfo/select")
    @ResponseBody
    public ResultUtils select(@RequestBody User user){
        User u = userService.findUser(user);
        return ResultUtils.ok(u);
    }




}
