package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单表
 * @date 2023/3/7 19:36
 */

@Data
@TableName("tb_workorder")
public class WorkOrder {

    /**
    * @description 主键
    * @date 2023/3/7 19:38
    */
    private Integer id;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 19:38
     */
    private String communityName;

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
     * @description 是否收费：0-不收费（默认），1-收费
     * @date 2023/3/7 19:38
     */
    private String ischarges;

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

    /**
    * @description 是否评价
    * @date 2023/4/8 9:40
    */
    private String iscomment;
}
