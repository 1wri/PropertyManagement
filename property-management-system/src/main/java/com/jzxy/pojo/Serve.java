package com.jzxy.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author 龙殇
 * @version 1.0
 * @description 上门服务表
 * @date 2023/3/7 19:31
 */

@Data
public class Serve {

    /**
    * @description 主键
    * @date 2023/3/7 19:34
    */
    private Integer id;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 19:34
     */
    private String communityName;

    /**
     * @description 姓名
     * @date 2023/3/7 19:34
     */
    private String name;

    /**
     * @description 联系电话
     * @date 2023/3/7 19:34
     */
    private String telephone;

    /**
     * @description 所属栋数
     * @date 2023/3/7 19:34
     */
    private String buildingName;

    /**
     * @description 楼层
     * @date 2023/3/7 19:34
     */
    private Integer floor;

    /**
     * @description 单元
     * @date 2023/3/7 19:34
     */
    private Integer unit;

    /**
     * @description 房间号
     * @date 2023/3/7 19:34
     */
    private Integer roomNum;

    /**
     * @description 问题描述
     * @date 2023/3/7 19:34
     */
    private String description;

    /**
     * @description 是否处理：0-未处理（默认），1-已处理
     * @date 2023/3/7 19:34
     */
    private String ishandle;

    /**
    * @description 问题提交时间
    * @date 2023/3/15 10:06
    */
    private LocalDate commitTime;

    /**
    * @description 工单Id
    * @date 2023/4/7 20:37
    */
    private Integer workorderId;
}
