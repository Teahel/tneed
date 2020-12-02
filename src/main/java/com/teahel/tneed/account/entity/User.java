package com.teahel.tneed.account.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-11-20
 */

@Data
//@Entity
public class User {
    private String userName;

    private String passWord;

    public User(String userName,String passWord){
        this.passWord = passWord;
        this.userName = userName;
        throw new RuntimeException("");
    }

}
