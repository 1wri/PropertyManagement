package com.jzxy.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 返回给前端的分页数据
 * @date 2023/3/7 22:15
 */

@Data
public class ReturnPageData<T> {

    //查询数据列表
    private List<T> data = new ArrayList<>();
    //总条数
    private Long total;
    //当前页数
    private Long currentPage;
}
