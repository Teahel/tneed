package com.teahel.tneed;

import com.teahel.tneed.account.controller.UserController;
import com.teahel.tneed.account.dao.ServerInfoV2Repository;
import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.Role;
import com.teahel.tneed.account.entity.ServerInfoV2DO;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.IUserService;
import com.teahel.tneed.account.service.ServerInfoV2Service;
import com.teahel.tneed.common.PropertyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class TestUser extends TneedApplicationTests{

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserController userController;

    @Autowired
    private IUserService userService;

    @Autowired
    private ServerInfoV2Service serverInfoV2Service;

    @Autowired
    PropertyConfig propertyConfig;

    @Test
    public void saveUser(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //encoder.encode("SHA-1");

        repository.save(new User("zhangsan", encoder.encode("123456"),
                Arrays.asList(new Role("USER"), new Role("ADMIN")),LocalDateTime.now(),LocalDateTime.now(), UUID.randomUUID().toString()));
        /*{bcrypt}$2a$10$RleCDGdJBvfQpyPRah8pGOz8d96Qy2LhjhLJ63aJXmKQiMcteNDJS*/
        System.out.println(encoder.encode("123456"));

    }

    @Test
    public void updateUser(){
        userService.updateUser("12","zhangsan");
    }

    /**
     * 保存服务信息
     */
    @Test
    public void addServerInfoV2(){

        ServerInfoV2DO serverInfoV2DO = new ServerInfoV2DO();
        serverInfoV2DO.setLocation("新加坡");
        serverInfoV2DO.setServerLink(
                "vmess://ewogICJ2IjogIjIiLAogICJwcyI6ICJ0ZXN0IiwKICAiYWRkIjogInRlYWhlbC5jbHViIiwKICAicG9ydCI6IDQ4OTE1LAogICJpZCI6IC" +
                        "IwODFiMzZhNi1mZTVkLTRmZTMtOTc1My05OWU1YmEzMzdmMTciLAogICJhaWQiOiA2NCwKICAibmV0IjogInRjcCIsCiAgInR5cGUiOiAibm9uZSI" +
                        "sCiAgImhvc3QiOiAiIiwKICAicGF0aCI6ICIiLAogICJ0bHMiOiAibm9uZSIKfQ==");
        serverInfoV2DO.setModifiedTime(LocalDateTime.now());
        serverInfoV2DO.setUserid(Long.valueOf(1));

     //   serverInfoV2Service.addServerInfoV2DO(serverInfoV2DO);


    }

    @Test
    public void address(){
        System.out.println(propertyConfig.getImageAddress());

    }

}
