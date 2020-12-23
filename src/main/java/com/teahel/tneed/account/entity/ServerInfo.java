package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * 服务信息表
 */
@Data
@Entity
@NoArgsConstructor
public class ServerInfo {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long server_id;

    /**
     * 服务地址
     */
    private String server;

    /**
     * 端口
     */
    private String server_port;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密方式
     */
    private String method;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 有效时间  充值之后显示该时间，有效期内充值则增加）
     */
    private LocalDateTime effective_time;


}
