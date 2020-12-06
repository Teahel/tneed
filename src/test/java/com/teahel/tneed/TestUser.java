package com.teahel.tneed;

import com.teahel.tneed.account.dao.UserRepository;
import com.teahel.tneed.account.entity.Role;
import com.teahel.tneed.account.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class TestUser extends TneedApplicationTests{

    @Autowired
    private UserRepository repository;

    @Test
    public void saveUser(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //encoder.encode("SHA-1");

        repository.save(new User("zhangsan", encoder.encode("123456"),
                Arrays.asList(new Role("USER"), new Role("ADMIN"))));
        /*{bcrypt}$2a$10$RleCDGdJBvfQpyPRah8pGOz8d96Qy2LhjhLJ63aJXmKQiMcteNDJS*/
        System.out.println(encoder.encode("123456"));


    }
}
