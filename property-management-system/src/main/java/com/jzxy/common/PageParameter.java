package com.jzxy.common;

import lombok.Data;

/**
 * @author 龙殇
 * @version 1.0
 * @description 分页参数
 * @date 2023/3/7 21:35
 */

@Data
public class PageParameter {
    //当前页数
    private Integer currentPage = 1;
    //每页大小
    private Integer pageSize = 5;
}
