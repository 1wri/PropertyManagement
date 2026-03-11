package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主表
 * @date 2023/3/7 17:25
 */

@Data
@TableName("tb_owner")
public class Owner {

    /**
    * @description 业主ID
    * @date 2023/3/7 17:29
    */
    private Integer id;

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
    * @description 业主照片
    * @date 2023/3/7 17:30
    */
    private String picture;

    /**
    * @description 身份证号
    * @date 2023/3/7 17:30
    */
    private String idcard;

    /**
    * @description 联系方式
    * @date 2023/3/7 17:31
    */
    private String telephone;

    /**
    * @description 性别
    * @date 2023/3/7 17:31
    */
    private String sex;

    /**
    * @description 出生日期
    * @date 2023/3/7 17:31
    */
    private LocalDate birthday;

    /**
    * @description 家庭成员人数
    * @date 2023/3/7 17:32
    */
    private Integer familyMembers;

    /**
    * @description 创建时间
    * @date 2023/3/7 17:32
    */
    private LocalDateTime  createTime;

    /**
    * @description 更新时间
    * @date 2023/3/7 17:33
    */
    private LocalDateTime  updateTime;
}
