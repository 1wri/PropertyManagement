package com.jzxy.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 收费明细表
 * @date 2023/3/7 19:17
 */

@Data
public class ChargeDetail {

    /**
    * @description 收费明细ID
    * @date 2023/3/7 19:21
    */
    private Integer id;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 19:21
     */
    private String communityName;

    /**
     * @description 收费名称
     * @date 2023/3/7 19:21
     */
    private String chargeRuleName;

    /**
     * @description 收费类型
     * @date 2023/3/7 19:21
     */
    private String chargeRuleType;

    /**
     * @description 业主名称
     * @date 2023/3/7 19:21
     */
    private String ownerName;

    /**
     * @description 业主联系电话
     * @date 2023/3/7 19:21
     */
    private String ownerTelephone;

    /**
     * @description 应收金额
     * @date 2023/3/7 19:21
     */
    private Double payMoney;

    /**
     * @description 实收金额
     * @date 2023/3/7 19:21
     */
    private Double actualMoney;

    /**
     * @description 收费年限
     * @date 2023/3/7 19:21
     */
    private String chargeYear;

    /**
     * @description 是否欠费：0-欠费（默认），1-未欠费
     * @date 2023/3/7 19:21
     */
    private String isarrears;

    /**
     * @description 缴费时间
     * @date 2023/3/7 19:21
     */
    private LocalDate payTime;

    /**
     * @description 创建时间
     * @date 2023/3/7 17:57
     */
    private LocalDateTime createTime;

    /**
     * @description 更新时间
     * @date 2023/3/7 17:57
     */
    private LocalDateTime updateTime;
}
