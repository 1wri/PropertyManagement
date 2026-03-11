package com.jzxy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.WorkOrderMapper;
import com.jzxy.pojo.WorkOrder;
import com.jzxy.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单管理服务类
 * @date 2023/3/15 10:37
 */

@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Autowired
    private WorkOrderMapper workOrderMapper;


    /**
     * @description 查看所有工单信息(分页 )
     * @param workOrder 工单信息
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 10:54
     */
    public Result getWorkOrderListByPage(WorkOrder workOrder, PageParameter pageParameter){
        //设置分页参数
        IPage<WorkOrder> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq((workOrder.getIscharges() != null && !"".equals(workOrder.getIscharges())),
                WorkOrder::getIscharges, workOrder.getIscharges());
        queryWrapper.like((workOrder.getCommunityName() != null && !"".equals(workOrder.getCommunityName())),
                WorkOrder::getCommunityName, workOrder.getCommunityName());
        queryWrapper.like((workOrder.getName() != null && !"".equals(workOrder.getName())),
                WorkOrder::getName, workOrder.getName());

        //根据分页参数查询
        page = workOrderMapper.selectPage(page, queryWrapper);

        //查询的总条数
        double total = page.getTotal();
        //总的页面数
        double pageCount = Math.ceil(total / pageParameter.getPageSize());
        //判断当前查询的页面数是否大于总页面数
        if (pageParameter.getCurrentPage() > pageCount){
            //大于页面总数
            //把当前页数置为 1
            pageParameter.setCurrentPage(1);
            page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());
            //重新查询第一页数据
            page = workOrderMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<WorkOrder> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 用户评价
     * @param workOrder 工单信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 11:26
     */
    public Result comment(WorkOrder workOrder){
        //修改评价状态为已评价
        workOrder.setIscomment("1");

        int flag = workOrderMapper.updateById(workOrder);

        if (flag > 0){
            return Result.success("评价成功！！！");
        }else {
            return Result.success("评价失败，请稍后再试！！！");
        }
    }
}
