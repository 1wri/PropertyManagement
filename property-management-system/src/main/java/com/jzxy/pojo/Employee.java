package com.jzxy.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author 龙殇
 * @version 1.0
 * @description 员工管理表
 * @date 2023/3/7 19:24
 */

@Data
public class Employee {

    /**
    * @description 员工主键
    * @date 2023/3/7 19:28
    */
    private Integer id;

    /**
     * @description 员工姓名
     * @date 2023/3/7 19:28
     */
    private String employeeName;

    /**
     * @description 员工性别:0-男（默认），1-女
     * @date 2023/3/7 19:28
     */
    private String employeeSex;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 19:28
     */
    private String communityName;

    /**
     * @description 联系电话
     * @date 2023/3/7 19:28
     */
    private String telephone;

    /**
     * @description 身份证号
     * @date 2023/3/7 19:28
     */
    private String idcard;

    /**
     * @description 年龄
     * @date 2023/3/7 19:28
     */
    private Integer age;

    /**
     * @description 入职日期
     * @date 2023/3/7 19:28
     */
    private LocalDate entryTime;

    /**
     * @description 离职日期
     * @date 2023/3/7 19:28
     */
    private LocalDate dimissionTime;

    /**
     * @description 是否离职,0-在职(默认) 1-离职
     * @date 2023/3/7 19:28
     */
    private String isdimission;

    /**
     * @description 职务类型,0-管理 1-普通
     * @date 2023/3/7 19:28
     */
    private String officeType;

    /**
     * @description 具体职位
     * @date 2023/3/7 19:28
     */
    private String office;
}
