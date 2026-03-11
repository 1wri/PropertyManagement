package com.jzxy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.dto.OwnerCommentDto;
import com.jzxy.mapper.ServeMapper;
import com.jzxy.pojo.Serve;
import com.jzxy.pojo.WorkOrder;
import com.jzxy.service.IServeService;
import com.jzxy.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 上门服务管理类
 * @date 2023/3/15 9:31
 */

@Service
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServeService {

    @Autowired
    private ServeMapper serveMapper;
    @Autowired
    private IWorkOrderService workOrderService;

    /**
     * @description 查看所有上门服务信息(分页 )
     * @param serve 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 9:38
     */
    public Result getServeListByPage(Serve serve, PageParameter pageParameter){
        //设置分页参数
        IPage<Serve> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Serve> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((serve.getIshandle() != null && !"".equals(serve.getIshandle())),
                Serve::getIshandle, serve.getIshandle());
        queryWrapper.like((serve.getCommunityName() != null && !"".equals(serve.getCommunityName())),
                Serve::getCommunityName, serve.getCommunityName());

        //设置只查询未处理的上门服务信息
        queryWrapper.like("".equals(serve.getIshandle()),Serve::getIshandle, 0);
        //按照提交时间降序排序
        queryWrapper.orderByDesc(Serve::getCommitTime);

        //根据分页参数查询
        page = serveMapper.selectPage(page, queryWrapper);

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
            page = serveMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Serve> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 根据业主信息查询对应的上门服务信息
     * @param serve 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/7 17:18
     */
    public Result selectServerByOwner(Serve serve){
        LambdaQueryWrapper<Serve> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Serve::getName, serve.getName());
        queryWrapper.eq(Serve::getTelephone, serve.getTelephone());

        //设置只查询未处理的上门服务信息
        queryWrapper.in(Serve::getIshandle, 0,2);

        List<Serve> serveList = serveMapper.selectList(queryWrapper);

        if (serveList == null){
            return Result.fail("您当前没有正在进行的上门服务信息！！！");
        }

        return Result.success(serveList);
    }

    /**
     * @description 根据业主信息获得上门服务的描述信息和提交时间（用于工单评价功能）
     * @param serve 业主基本信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 10:14
     */
    public Result getBasicInfo(Serve serve){
        LambdaQueryWrapper<Serve> queryWrapper = new LambdaQueryWrapper<>();

        //封装查询条件
        queryWrapper.eq(Serve::getName, serve.getName());
        queryWrapper.eq(Serve::getTelephone, serve.getTelephone());
        //只查询已经处理的上门服务信息
        queryWrapper.eq(Serve::getIshandle, 1);

        List<Serve> serveList = serveMapper.selectList(queryWrapper);

        if (serveList == null || serveList.size() == 0){
            return Result.fail("您当前没有需要评价的服务！！！");
        }

        //返回的数据
        List<OwnerCommentDto> commentDtoList = new ArrayList<>(serveList.size());

        //遍历每一个服务信息得到每一个工单Id
        for (Serve serve1 : serveList) {
            OwnerCommentDto ownerCommentDto = new OwnerCommentDto();

            LambdaQueryWrapper<WorkOrder> wrapper = new LambdaQueryWrapper<>();
            //设置只查询未评价的工单
            wrapper.eq(WorkOrder::getIscomment, 0);
            wrapper.eq(WorkOrder::getId, serve1.getWorkorderId());
            WorkOrder workOrder = workOrderService.getOne(wrapper);

            //如果未查询到数据，开始下一次循环
            if (workOrder == null){
                continue;
            }

            //封装工单Id
            ownerCommentDto.setWorkOrderId(workOrder.getId());

            //封装上门服务数据
            ownerCommentDto.setCommitTime(serve1.getCommitTime());
            ownerCommentDto.setDescription(serve1.getDescription());
            ownerCommentDto.setChargesMoney(workOrder.getChargesMoney());
            ownerCommentDto.setName(workOrder.getName());
            ownerCommentDto.setTelephone(workOrder.getTelephone());

            commentDtoList.add(ownerCommentDto);
        }

        //没有需要评价的服务
        if (commentDtoList.size() == 0){
            return Result.fail("当前您没有需要评价的服务！！！");
        }

        return Result.success(commentDtoList);
    }

    /**
     * @description 根据业主信息查询历史评价信息
     * @param serve 业主基本信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 12:57
     */
    public Result getHistoryInfo(Serve serve){
        LambdaQueryWrapper<Serve> queryWrapper = new LambdaQueryWrapper<>();

        //封装查询条件
        queryWrapper.eq(Serve::getName, serve.getName());
        queryWrapper.eq(Serve::getTelephone, serve.getTelephone());
        //只查询已经处理的上门服务信息
        queryWrapper.eq(Serve::getIshandle, 1);

        List<Serve> serveList = serveMapper.selectList(queryWrapper);

        if (serveList == null || serveList.size() == 0){
            return Result.fail("您当前没有历史评价信息！！！");
        }

        //返回的数据
        List<OwnerCommentDto> commentDtoList = new ArrayList<>(serveList.size());

        //遍历每一个服务信息得到每一个工单Id
        for (Serve serve1 : serveList) {
            OwnerCommentDto ownerCommentDto = new OwnerCommentDto();

            LambdaQueryWrapper<WorkOrder> wrapper = new LambdaQueryWrapper<>();
            //设置只查询已评价的工单
            wrapper.eq(WorkOrder::getIscomment, 1);
            wrapper.eq(WorkOrder::getId, serve1.getWorkorderId());
            WorkOrder workOrder = workOrderService.getOne(wrapper);

            //如果未查询到数据，开始下一次循环
            if (workOrder == null){
                continue;
            }

            //封装工单Id
            ownerCommentDto.setWorkOrderId(workOrder.getId());

            //封装上门服务数据
            ownerCommentDto.setCommitTime(serve1.getCommitTime());
            ownerCommentDto.setDescription(serve1.getDescription());
            ownerCommentDto.setChargesMoney(workOrder.getChargesMoney());
            ownerCommentDto.setName(workOrder.getName());
            ownerCommentDto.setTelephone(workOrder.getTelephone());
            ownerCommentDto.setComment(workOrder.getComment());

            commentDtoList.add(ownerCommentDto);
        }

        //没有历史评价服务信息
        if (commentDtoList.size() == 0){
            return Result.fail("当前您没有历史评价服务信息！！！");
        }

        return Result.success(commentDtoList);
    }
}
