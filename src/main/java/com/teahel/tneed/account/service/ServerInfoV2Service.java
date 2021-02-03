package com.teahel.tneed.account.service;

import com.teahel.tneed.account.entity.ServerInfoV2DO;

import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

public interface ServerInfoV2Service {

    /**
     * 查询服务信息
     * @return 服务信息
     */
    public List<ServerInfoV2DO> findAll();

    /**
     * 新增服务信息
     * @param serverInfoV2DO 服务信息
     */
    public void addServerInfoV2DO(ServerInfoV2DO serverInfoV2DO);

    /**
     * 修改服务信息
     *
     * @param serverStatus 开启关闭状态
     * @param id 主键
     */
    void modifyServerStatus(Integer serverStatus,Long id);



}
