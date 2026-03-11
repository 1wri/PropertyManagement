package com.jzxy.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位使用表
 * @date 2023/3/7 17:53
 */

@Data
public class ParkingUse {

    /**
    * @description 车位使用ID
    * @date 2023/3/7 17:57
    */
    private Integer id;

    /**
    * @description 所属小区名称
    * @date 2023/3/7 17:57
    */
    private String communityName;

    /**
     * @description 车位编号
     * @date 2023/3/7 17:57
     */
    private String code;

    /**
     * @description 车辆所有人（业主）名称
     * @date 2023/3/7 17:57
     */
    private String ownerName;

    /**
     * @description 联系方式
     * @date 2023/3/7 17:57
     */
    private String telephone;

    /**
     * @description 使用性质：0-购买(默认)，1-月租，2-年租
     * @date 2023/3/7 17:57
     */
    private String useType;

    /**
     * @description （使用）开始时间
     * @date 2023/3/7 17:57
     */
    private LocalDate startTime;

    /**
     * @description （使用）结束时间
     * @date 2023/3/7 17:57
     */
    private LocalDate endTime;

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
