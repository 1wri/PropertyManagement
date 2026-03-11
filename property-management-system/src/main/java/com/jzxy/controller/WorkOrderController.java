package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.dto.WorkOrderDto;
import com.jzxy.pojo.Serve;
import com.jzxy.pojo.WorkOrder;
import com.jzxy.service.IServeService;
import com.jzxy.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单管理Controller
 * @date 2023/3/15 10:38
 */

@RestController
@RequestMapping("workOrder")
public class WorkOrderController {

    @Autowired
    private IWorkOrderService workOrderService;
    @Autowired
    private IServeService serveService;

    /**
    * @description 查看所有工单信息(分页 )
    * @param workOrder 工单信息
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/15 10:54
    */
    @GetMapping("/list")
    public Result getWorkOrderListByPage(WorkOrder workOrder, PageParameter pageParameter){
        return workOrderService.getWorkOrderListByPage(workOrder, pageParameter);
    }

    /**
    * @description 新增工单
    * @param workOrderDto 工单Dto
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/16 13:04
    */
    @PutMapping("/add")
    public Result addWorkOrder(@RequestBody WorkOrderDto workOrderDto){
        if (workOrderDto.getChargesMoney() > 0){
            workOrderDto.setIscharges("1");
        }

        boolean flag = workOrderService.save(workOrderDto);

        Serve serve = new Serve();
        serve.setId(workOrderDto.getServerId());
        serve.setWorkorderId(workOrderDto.getId());

        serveService.updateById(serve);

        return flag ? Result.success() : Result.fail();
    }

    /**
    * @description 根据上门服务Id查询工单信息
    * @param serveId 上门服务Id
    * @date 2023/4/7 22:03
    */
    @GetMapping("/select")
    public Result select(Integer serveId){

        Integer workorderId = serveService.getById(serveId).getWorkorderId();

        WorkOrder workOrder = workOrderService.getById(workorderId);

        return workOrder == null ? Result.fail("数据不存在！！！") : Result.success(workOrder);

    }

    /**
    * @description 用户评价
    * @param workOrder 工单信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/4/8 11:26
    */
    @PostMapping("/comment")
    public Result comment(@RequestBody WorkOrder workOrder){
        return workOrderService.comment(workOrder);
    }

}
