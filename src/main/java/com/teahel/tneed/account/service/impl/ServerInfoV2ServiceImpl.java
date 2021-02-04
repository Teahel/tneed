package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.ServerInfoV2Repository;
import com.teahel.tneed.account.entity.ServerInfoV2DO;
import com.teahel.tneed.account.entity.ServerInfoV2DTO;
import com.teahel.tneed.account.service.ServerInfoV2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */
@Slf4j
@Service
public class ServerInfoV2ServiceImpl implements ServerInfoV2Service {

    @Autowired
    public ServerInfoV2Repository repository;

    /**
     * 查询服务信息
     * @return 服务信息
     */
    @Override
    public List<ServerInfoV2DO> findAll() {
        return repository.findAll();
    }

    /**
     * 新增服务信息
     *
     * @param serverInfoV2DTO 服务信息
     */
    @Override
    public void addServerInfoV2DO(ServerInfoV2DTO serverInfoV2DTO) {

        ServerInfoV2DO serverInfoV2DO = new ServerInfoV2DO();
/*

        serverInfoV2DO.setUserid();
        serverInfoV2DO.setModifiedTime(LocalDateTime.now());
        serverInfoV2DO.setLocation(serverInfoV2DTO.getLocation());
        serverInfoV2DO.setServerName(serverInfoV2DTO.getServerName());

        serverInfoV2DO.setImageAddress();



        repository.save(serverInfoV2DO);
        */

    }

    /**
     * 修改服务信息,
     *
     * @param serverStatus 开启关闭状态
     * @param id 主键
     */
    @Override
    public void modifyServerStatus(Integer serverStatus, Long id) {
        repository.modifyServerStatus(serverStatus,id);
    }


}