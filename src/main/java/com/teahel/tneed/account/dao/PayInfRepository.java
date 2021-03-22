package com.teahel.tneed.account.dao;

import com.teahel.tneed.account.entity.PayInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-03-22
 */

public interface PayInfRepository extends JpaRepository<PayInfoEntity,Integer> {
}
