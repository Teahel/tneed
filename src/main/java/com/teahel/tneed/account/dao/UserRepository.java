package com.teahel.tneed.account.dao;

import com.teahel.tneed.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 查询用户名称
     * @param username 用户名
     * @return 查询结果
     */
    User findByUsername(String username);

    /**
     *  Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
     * 保存账户
     * @param user  账户信息
     * @return 保存结果
     */
    User save(User user);


    /**
     * 修改用户信息
     * @param password 密码
     * @param username 用户名
     *  clearAutomatically=true:clear the underlying persistence context after executing the modifying
     */
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User u set u.password = ?1 where u.username = ?2")
    void updateUser(String password,String username);


}
