package com.jzxy.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 收费准则表
 * @date 2023/3/7 19:12
 */

@Data
public class ChargeRule {

    /**
    * @description 收费项目ID
    * @date 2023/3/7 19:14
    */
    private Integer id;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 19:14
     */
    private String communityName;

    /**
     * @description 收费名称
     * @date 2023/3/7 19:14
     */
    private String chargeName;

    /**
     * @description 收费类型
     * @date 2023/3/7 19:14
     */
    private String chargeType;

    /**
     * @description 收费年限
     * @date 2023/3/7 19:14
     */
    private String chargeYear;

    /**
     * @description 收费金额（年）
     * @date 2023/3/7 19:14
     */
    private Double chargeMoney;

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
