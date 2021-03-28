package com.teahel.tneed.account.controller;


import com.teahel.tneed.account.entity.ServerInfoEntity;

import com.teahel.tneed.account.service.ServerInfoService;
import com.teahel.tneed.common.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

@RestController
@RequestMapping("/serverInfo")
public class ServerInfoContorller {

    @Autowired
    public ServerInfoService serverInfoService;

    /**
     *获取全部设备信息
     * @return 查询结果
     */
    @PostMapping("/select")
    public ResultUtils findServerInfo(@RequestBody ServerInfoEntity serverInfoEntity) {
        List<ServerInfoEntity> list = serverInfoService.findServerInfos(serverInfoEntity);
        return ResultUtils.ok(list);
    }

    /**
     * 新增服务信息
     * @param image 图片
     * @param serverName 服务名称
     * @param username 创建人账户名
     * @param location 地址
     * @param serverLink 服务链接
     * @param remark 备注
     * @return 存储结果
     */
    @PostMapping(value = "/save")
    public ResultUtils save(@RequestParam("image") MultipartFile image,String serverName,String username,String location,String serverLink,String remark){
        serverInfoService.addServerInfo(image,serverName,username,location,serverLink,remark);
        return ResultUtils.ok();
    }



}
