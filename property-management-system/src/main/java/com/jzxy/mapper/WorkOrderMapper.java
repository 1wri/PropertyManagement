package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.WorkOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单Mapper
 * @date 2023/3/15 10:35
 */

@Mapper
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {
}
