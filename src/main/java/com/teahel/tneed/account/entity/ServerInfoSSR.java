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
public class ServerInfoSSR {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long serverId;

    /**
     * 服务地址
     */
    private String server;

    /**
     * 端口
     */
    private String serverPort;

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
    private LocalDateTime createTime;


}
