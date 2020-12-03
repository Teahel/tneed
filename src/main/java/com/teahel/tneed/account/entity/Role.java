package com.teahel.tneed.account.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role{
    @Id
    @GeneratedValue
    private Long id;

    public Role(String name) {
        this.name = name;
    }

    String name;

    public Role() {

    }
}
