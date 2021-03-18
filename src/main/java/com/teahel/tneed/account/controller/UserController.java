package com.teahel.tneed.account.controller;


import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/misspassword")
    public String misspassword() {
        return "misspassword";
    }

    /**
     * 检测邀请码
     * 不正确邀请码不允许注册
     * @param model 模型
     * @param request 请求信息
     * @return 检测结果
     */
    @GetMapping("/register")
    public String register(Model model,  HttpServletRequest request) {

        //防止恶意使用工具多次请求，设置redis 控制访问IP  默认允许5次
        /*
        String ip = IpUtils.getIpAddress(request);
        int count = 0 ;
        String ipCount = redisUtils.get(ip);
        if (StringUtils.isNotEmpty(ipCount)) {
            count = Integer.valueOf(ipCount);
            if (count > Integer.valueOf(propertyConfig.getRegisterCount())) {
                model.addAttribute("inviteCode","当天请求已经超过"+propertyConfig.getRegisterCount()+"次,无法再请求！");
                model.addAttribute("inviteCodeError", false);
                return "register";
            }
            redisUtils.set(ip,++count);
        } else {
            //如果该ip未超过 次数则保存
            redisUtils.set(ip,count ++);
        }
        */

        if (request.getQueryString() != null) {
            User user = userService.findByInviteCode(request.getQueryString());
            if(user != null){
                model.addAttribute("inviteCode", request.getQueryString());
                model.addAttribute("inviteCodeError", true);
                return "register";
            }
        }
        model.addAttribute("inviteCode","该邀请码不存在!");
        model.addAttribute("inviteCodeError", false);
        return "register";
    }


}
