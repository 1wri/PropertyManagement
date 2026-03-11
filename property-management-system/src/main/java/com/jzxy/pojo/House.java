package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产信息表
 * @date 2023/3/7 17:15
 */

@Data
@TableName("tb_house")
public class House {
    /**
    * @description 房屋ID
    * @date 2023/3/7 17:20
    */
    private Integer id;
    /**
     * @description 所属小区名称
     * @date 2023/3/7 17:20
     */
    private String  communityName;
    /**
     * @description 所属栋数名称
     * @date 2023/3/7 17:20
     */
    private String  buildingName;
    /**
     * @description 房产编码
     * @date 2023/3/7 17:20
     */
    private String  code;
    /**
    * @description 户主（业主）ID
    * @date 2023/3/7 17:22
    */
    private Integer ownerId;
    /**
    * @description 户主（业主）名称
    * @date 2023/3/7 17:22
    */
    private String  ownerName;
    /**
    * @description 户主联系方式
    * @date 2023/3/7 17:22
    */
    private String telephone;
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
    /**
    * @description 入住时间
    * @date 2023/3/7 17:22
    */
    private LocalDateTime liveTime;
}
