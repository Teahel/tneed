package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.ServerInfoRepository;
import com.teahel.tneed.account.entity.ServerInfoEntity;
import com.teahel.tneed.account.service.ServerInfoService;
import com.teahel.tneed.common.PropertyConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ServerInfoServiceImpl implements ServerInfoService {

    final @NonNull EntityManager entityManager;

    @Autowired
    public ServerInfoRepository repository;

    @Autowired
    PropertyConfig propertyConfig;

    /**
     * 查询服务信息
     * @return 服务信息
     */
    @Override
    public List<ServerInfoEntity> findServerInfos(ServerInfoEntity serverInfoEntity) {
        if(StringUtils.isEmpty(serverInfoEntity.getUsername())){
            return repository.findAll(Sort.by(Sort.Direction.DESC,"modifiedTime"));
        }
        ServerInfoEntity info = new ServerInfoEntity();
        info.setUsername(serverInfoEntity.getUsername());
        Example<ServerInfoEntity> example = Example.of(info);
        return repository.findAll(example);
    }

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
    @Override
    public void addServerInfo(MultipartFile image, String serverName, String username, String location, String serverLink, String remark) {
        try{
        ServerInfoEntity serverInfo = new ServerInfoEntity();
        serverInfo.setModifiedTime(LocalDateTime.now());
        String fileName = image.getOriginalFilename();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String path = df.format(new Date())+"_tneed_"+ UUID.randomUUID()+"."+FilenameUtils.getExtension(fileName);
        String fileAddress= propertyConfig.getIamgeFile()+"/"+path;//文件保存位置
        String url = propertyConfig.getImageAddress()+"/"+path; //文件访问路径
        File imageFile = new File(fileAddress);
        image.transferTo(imageFile);
        serverInfo.setImageAddress(url);
        serverInfo.setServerName(serverName);
        serverInfo.setUsername(username);
        serverInfo.setLocation(location);
        serverInfo.setServerLink(serverLink);
        serverInfo.setRemark(remark);
        repository.save(serverInfo);
        }catch (IOException e){
            throw new RuntimeException("保存图片失败",e.getCause());
        }

    }

    /**
     * 修改服务信息,
     *
     * @param serverStatus 开启关闭状态
     * @param id 主键
     */
    @Override
    public void modifyServerStatus(Integer serverStatus, Long id) {
        Optional<ServerInfoEntity> serverInfoEntity = repository.findById(id);
        serverInfoEntity.get().setServerStatus(serverStatus);
        repository.save(serverInfoEntity.get());
    }



}
