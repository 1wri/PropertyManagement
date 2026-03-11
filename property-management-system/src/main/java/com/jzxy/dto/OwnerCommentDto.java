package com.jzxy.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主评价Dto
 * @date 2023/4/8 10:21
 */

@Data
public class OwnerCommentDto {
    /**
    * @description 工单Id
    * @date 2023/4/8 11:12
    */
    private int workOrderId;

    /**
     * @description 问题描述
     * @date 2023/3/7 19:34
     */
    private String description;

    /**
     * @description 问题提交时间
     * @date 2023/3/15 10:06
     */
    private LocalDate commitTime;

    /**
     * @description 姓名
     * @date 2023/3/7 19:38
     */
    private String name;

    /**
     * @description 联系电话
     * @date 2023/3/7 19:38
     */
    private String telephone;

    /**
     * @description 收费金额
     * @date 2023/3/7 19:38
     */
    private double chargesMoney;

    /**
     * @description 用户评价
     * @date 2023/4/8 9:35
     */
    private String comment;

}
