package com.teahel.tneed.account.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-03-22
 */
@Data
@Entity(name = "pay_info")
@NoArgsConstructor
public class PayInfoEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 账户姓名
     */
    @NotBlank(message = "账户信息不能为空")
    private String username;

    /**
     * 数量
     */
    @NotBlank(message = "金额不能为空")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

}
