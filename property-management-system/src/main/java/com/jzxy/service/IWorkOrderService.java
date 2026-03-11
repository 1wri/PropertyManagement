package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.WorkOrder;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单服务接口
 * @date 2023/3/15 10:36
 */

public interface IWorkOrderService extends IService<WorkOrder> {

    /**
     * @description 查看所有工单信息(分页 )
     * @param workOrder 工单信息
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 10:54
     */
    Result getWorkOrderListByPage(WorkOrder workOrder, PageParameter pageParameter);

    /**
     * @description 用户评价
     * @param workOrder 工单信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 11:26
     */
    Result comment(WorkOrder workOrder);
}
