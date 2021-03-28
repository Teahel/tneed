package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */
@Data
@Entity(name = "server_info")
@NoArgsConstructor
public class ServerInfoEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 账户id
     */
    @NotBlank(message = "账户信息不能为空")
    private String username;

    /**
     * 服务名称
     */
    @NotBlank(message = "服务名称不能为空")
    private String serverName;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String location;

    /**
     * 图片地址
     */
    private String imageAddress;

    /**
     * 服务状态
     * 0 开启
     * 1 未开启
     */
    private Integer serverStatus = Integer.valueOf(1);

    /**
     * 服务链接
     */
    private String serverLink;

    /**
     * 备注
     */
    private String remark;

    /**
     * 变动时间
     */
    private LocalDateTime modifiedTime;


    /**
     * 图片
     */
    @Transient
    private MultipartFile image;

    /**
     * 状态描述
     */
    @Transient
    private String statusMessage;

    /*
    主键 id
    服务名称 server_name
    用户名称 username
    服务地址 location
    图片地址 image_address
    服务状态 server_status
    服务链接 serverLink
    备注  remark
    */

}
