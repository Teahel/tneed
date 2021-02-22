package com.teahel.tneed.account.controller;


import com.teahel.tneed.account.entity.ServerInfoEntity;

import com.teahel.tneed.account.service.ServerInfoService;
import com.teahel.tneed.common.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

@RestController
@RequestMapping("/server/v2")
public class ServerInfoContorller {

    @Autowired
    public ServerInfoService serverInfoService;

    /**
     *获取全部设备信息
     * @return 查询结果
     */
    @PostMapping("/select")
    public ResultUtils findServerInfoV2(){
        List<ServerInfoEntity> list = serverInfoService.findAll();
        return ResultUtils.ok(list);
    }

    /**
     * 新增服务信息
     * @param serverInfoDto 服务信息
     * @return 执行结果
     */
    @PostMapping("/save")
    public ResultUtils save(@RequestBody ServerInfoEntity serverInfoDto){
        serverInfoService.addServerInfo(serverInfoDto);
        return ResultUtils.ok();
    }




}
