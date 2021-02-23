package com.teahel.tneed.account.service.impl;

import com.teahel.tneed.account.dao.ServerInfoRepository;
import com.teahel.tneed.account.entity.ServerInfoEntity;
import com.teahel.tneed.account.service.ServerInfoService;
import com.teahel.tneed.common.PropertyConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public List<ServerInfoEntity> findAll() {
        return repository.findAll();
    }

    /**
     * 新增服务信息
     *
     * @param serverInfoEntity 服务信息
     */
    @Override
    public void addServerInfo(ServerInfoEntity serverInfoEntity) {

        ServerInfoEntity serverInfoV2DO = new ServerInfoEntity();

        serverInfoV2DO.setModifiedTime(LocalDateTime.now());
        String fileName = serverInfoEntity.getImage().getOriginalFilename();
        String filePath = UUID.randomUUID()+ FilenameUtils.getExtension(fileName);
        File imageFile = new File(filePath);
        try{
            serverInfoEntity.getImage().transferTo(imageFile);
        }catch (IOException e){
            throw new RuntimeException("保存图片失败",e.getCause());
        }
        //保存文件名 使得可以远程下载图片
        serverInfoV2DO.setImageAddress(propertyConfig.getImageAddress()+filePath);

        repository.save(serverInfoV2DO);
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
