package com.teahel.tneed.account.service;

import com.teahel.tneed.account.entity.ServerInfoEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

public interface ServerInfoService {

    /**
     * 查询服务信息
     * @return 服务信息
     */
    public List<ServerInfoEntity> findAll();

    /**
     * 新增服务信息
     * @param image 图片
     * @param serverName 服务名称
     * @param username 账户名
     * @param location 地址
     * @param serverLink 服务链接
     * @param remark 备注
     * @return 存储结果
     */
    public void addServerInfo(MultipartFile image, String serverName, String username, String location, String serverLink, String remark);

    /**
     * 修改服务信息
     *
     * @param serverStatus 开启关闭状态
     * @param id 主键
     */
    void modifyServerStatus(Integer serverStatus,Long id);



}
