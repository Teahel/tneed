package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Role{

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 角色名称
     */
    String name;


    public Role(String name) {
        this.name = name;
    }


}
