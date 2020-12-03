package com.teahel.tneed.account.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/info")
public class UserController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "userName") String userName,@RequestParam(value = "password") String password){
        return "1";
    }
}
