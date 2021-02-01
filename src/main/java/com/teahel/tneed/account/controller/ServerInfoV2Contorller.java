package com.teahel.tneed.account.controller;

import com.teahel.tneed.account.entity.ServerInfoV2DO;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.ServerInfoV2Service;
import com.teahel.tneed.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

@RestController
@RequestMapping("/server/v2")
public class ServerInfoV2Contorller {

    @Autowired
    public ServerInfoV2Service serverInfoV2Service;

    /**
     * 若用户id为空查询全部设备信息
     *
     * @return 查询结果
     */
    @PostMapping("/select")
    public ResultUtils findServerInfoV2(ServerInfoV2DO serverInfoV2DO){
        if(serverInfoV2DO.getUserid() == null){
            return  ResultUtils.error("查询账户为空");
        }
        List<ServerInfoV2DO> list = serverInfoV2Service.findByUserid(serverInfoV2DO.getUserid().intValue());
        return ResultUtils.ok(list);
    }

    /**
     * 新增服务信息
     * @param serverInfoV2DO 服务信息
     * @return 执行结果
     */
    @PostMapping("/save")
    public ResultUtils save(ServerInfoV2DO serverInfoV2DO){

    }

}
