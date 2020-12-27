package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Announcement {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 公告信息
     */
    private String information;

}
