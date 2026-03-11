package com.jzxy.dto;

import lombok.Data;

/**
 * @author 龙殇
 * @version 1.0
 * @description 户主信息Dto
 * @date 2023/4/7 13:30
 */

@Data
public class OwnerInfoDto {
    /**
     * @description 所属小区名称
     * @date 2023/3/7 17:30
     */
    private String communityName;

    /**
     * @description 业主名称
     * @date 2023/3/7 17:30
     */
    private String name;

    /**
     * @description 联系方式
     * @date 2023/3/7 17:31
     */
    private String telephone;

    /**
     * @description 所属栋数名称
     * @date 2023/3/7 17:20
     */
    private String  buildingName;

    /**
     * @description 房间号
     * @date 2023/3/7 17:22
     */
    private Integer roomNum;

    /**
     * @description 单元
     * @date 2023/3/7 17:22
     */
    private Integer unit;

    /**
     * @description 楼层
     * @date 2023/3/7 17:22
     */
    private Integer floor;

}
