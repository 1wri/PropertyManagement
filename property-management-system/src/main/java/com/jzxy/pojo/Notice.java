package com.jzxy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author 龙殇
 * @version 1.0
 * @description 通知公告表
 * @date 2023/3/6 15:36
 */

@Data
@TableName("tb_notice")
public class Notice {

    /**
    * @description 主键Id
    * @date 2023/3/6 15:55
    */
    private Integer id;

    /**
     * @description 通知标题
     * @date 2023/3/6 15:55
     */
    private String title;

    /**
     * @description 通知内容
     * @date 2023/3/6 15:55
     */
    private String content;

    /**
     * @description 创建用户
     * @date 2023/3/6 15:55
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * @description 更新用户
     * @date 2023/3/6 15:55
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * @description 创建时间
     * @date 2023/3/6 15:55
     */
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    private LocalDate createDate;

    /**
     * @description 更新时间
     * @date 2023/3/6 15:55
     */
    private LocalDate updateDate;
}
