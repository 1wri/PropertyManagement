package com.jzxy.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位信息Dto
 * @date 2023/3/13 17:11
 */

@Data
public class ParkingDto {

    /**
     * @description 车位ID
     * @date 2023/3/7 17:51
     */
    private Integer id;

    /**
     * @description 所属小区名称
     * @date 2023/3/7 17:51
     */
    private String  communityName;

    /**
     * @description 车位编号
     * @date 2023/3/7 17:51
     */
    private String  code;

    /**
     * @description 车位类型:地面停车场,地下停车场
     * @date 2023/3/7 17:51
     */
    private String type;

    /**
     * @description 车位状态 0-在售 1-停售
     * @date 2023/3/13 17:24
     */
    private Integer state;

    /**
     * @description 创建时间
     * @date 2023/3/7 17:40
     */
    private LocalDateTime createTime;

    /**
     * @description 更新时间
     * @date 2023/3/7 17:41
     */
    private LocalDateTime updateTime;

}
