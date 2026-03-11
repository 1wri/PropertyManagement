package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 龙殇
 * @version 1.0
 * @description '访客管理表'
 * @date 2023/3/8 15:24
 */

@Data
@TableName("tb_user")
public class User {

    /**
     * @description 主键
     * @date 2023/3/8 15:28
     */
    private Integer id;

    /**
     * @description 姓名(用户名)
     * @date 2023/3/8 15:28
     */
    private String userName;

    /**
     * @description 密码
     * @date 2023/3/8 15:28
     */
    private String password;

    /**
     * @description 手机号
     * @date 2023/3/8 15:28
     */
    private String phone;

    /**
     * @description 性别
     * @date 2023/3/8 15:28
     */
    private String sex;

    /**
     * @description 身份证号
     * @date 2023/3/8 15:28
     */
    private String idNumber;

    /**
     * @description 头像
     * @date 2023/3/8 15:28
     */
    private String avatar;

    /**
     * @description 用户身份(common : 普通用户, admin : 管理员)
     * @date 2023/3/8 15:28
     */
    private String identity;

    /**
     * @description 状态 0:禁用，1:正常
     * @date 2023/3/8 15:28
     */
    private Integer status;
}
