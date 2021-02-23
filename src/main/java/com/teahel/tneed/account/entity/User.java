package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-11-20
 */

@Data
@Entity
@NoArgsConstructor
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 邀请数量:默认邀请5位
     */
    private Integer inviteCount = Integer.valueOf(5);

    /**
     * 有效时间  充值之后显示该时间，有效期内充值则增加）
     */
    private LocalDateTime effectiveTime;

    /**
     * 旧密码
     */
    @Transient
    private String oldPassword;

    public User(String username, String password, List<Role> roles,LocalDateTime createTime,LocalDateTime effectiveTime,String inviteCode) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createTime = createTime;
        this.effectiveTime = effectiveTime;
        this.inviteCode = inviteCode;

    }


}
