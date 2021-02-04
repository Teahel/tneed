package com.teahel.tneed.account.controller;

import com.teahel.tneed.account.entity.ServerInfoV2DO;
import com.teahel.tneed.account.entity.ServerInfoV2DTO;
import com.teahel.tneed.account.entity.User;
import com.teahel.tneed.account.service.ServerInfoV2Service;
import com.teahel.tneed.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
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
public class ServerInfoV2Contorller {

    @Autowired
    public ServerInfoV2Service serverInfoV2Service;

    /**
     *获取全部设备信息
     * @return 查询结果
     */
    @PostMapping("/select")
    public ResultUtils findServerInfoV2(){
        List<ServerInfoV2DO> list = serverInfoV2Service.findAll();
        return ResultUtils.ok(list);
    }

    /**
     * 新增服务信息
     * @param ServerInfoV2DTO 服务信息
     * @return 执行结果
     */
    @PostMapping("/save")
    public ResultUtils save(@RequestBody ServerInfoV2DTO ServerInfoV2DTO){
        serverInfoV2Service.addServerInfoV2DO(ServerInfoV2DTO);
        return ResultUtils.ok();
    }




}
