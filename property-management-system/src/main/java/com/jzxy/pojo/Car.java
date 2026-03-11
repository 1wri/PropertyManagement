package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车辆表
 * @date 2023/3/7 17:42
 */

@Data
@TableName("tb_car")
public class Car {

    /**
    * @description 车辆ID
    * @date 2023/3/7 17:44
    */
    private Integer id;
    /**
    * @description 所属业主
    * @date 2023/3/7 17:44
    */
    private String ownerName;
    /**
    * @description 车辆颜色
    * @date 2023/3/7 17:45
    */
    private String carColor;
    /**
    * @description 车牌号
    * @date 2023/3/7 17:45
    */
    private String carNumber;
    /**
    * @description 车辆型号
    * @date 2023/3/7 17:45
    */
    private String carType;
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
