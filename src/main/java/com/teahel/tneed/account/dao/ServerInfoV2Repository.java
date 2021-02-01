package com.teahel.tneed.account.dao;

import com.teahel.tneed.account.entity.ServerInfoV2DO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-01
 */

public interface ServerInfoV2Repository extends JpaRepository<ServerInfoV2DO, Long> {

    /**
     * 新增服务信息
     * @param serverInfoV2DO 服务信息
     * @return 存储结果
     */
     ServerInfoV2DO save (ServerInfoV2DO serverInfoV2DO);

    /**
     * 修改服务信息
     * @param serverStatus 设备状态
     * @param id 主键
     */
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update server_infov2do  s set s.server_status = ?1 where s.id = ?2")
    void modifyServerStatus(Integer serverStatus,Long id);

    /**
     * 查询全部
     * @return 所有列表
     */
    public List<ServerInfoV2DO> findAllOrOrderByModifiedTimeDesc();

    /**
     * 根据用户userid
     * @return 查询服务信息结果
     */
    public List<ServerInfoV2DO> findByUseridOrderByModifiedTimeDesc();


    /**
     * 根据删除
     * @param id
     */
    public void deleteById(Long id);

}
