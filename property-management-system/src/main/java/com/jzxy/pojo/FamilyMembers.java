package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 龙殇
 * @version 1.0
 * @description 家庭成员表
 * @date 2023/3/7 17:34
 */

@Data
@TableName("tb_family_members")
public class FamilyMembers {

    /**
    * @description 成员ID
    * @date 2023/3/7 17:37
    */
    private Integer id;
    /**
    * @description 户主ID
    * @date 2023/3/7 17:38
    */
    private Integer ownerId;
    /**
    * @description 成员名称
    * @date 2023/3/7 17:38
    */
    private String name;
    /**
    * @description 成员照片
    * @date 2023/3/7 17:38
    */
    private String picture;
    /**
    * @description 身份证号
    * @date 2023/3/7 17:39
    */
    private String idcard;
    /**
    * @description 联系方式
    * @date 2023/3/7 17:39
    */
    private String telephone;
    /**
    * @description 性别
    * @date 2023/3/7 17:39
    */
    private String sex;
    /**
    * @description 出生日期
    * @date 2023/3/7 17:40
    */
    private LocalDate birthday;
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
