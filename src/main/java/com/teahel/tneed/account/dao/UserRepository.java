package com.teahel.tneed.account.dao;

import com.teahel.tneed.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 查询用户名称
     * @param username 用户名
     * @return 查询结果
     */
    User findByUsername(String username);
}
