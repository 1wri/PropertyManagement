package com.jzxy;

import com.jzxy.pojo.WorkOrder;
import com.jzxy.service.IWorkOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单服务测试类
 * @date 2023/3/15 10:48
 */

@SpringBootTest
public class WorkOrderTests {

    @Autowired
    private IWorkOrderService workOrderService;

    @Test
    public void testInsert(){
        List<WorkOrder> workOrderList = new ArrayList<>(51);

        for (int i = 0; i < 51; i++) {
            WorkOrder workOrder = new WorkOrder();

            workOrder.setTelephone("157065715" + i);
            workOrder.setName("海月");

            if(i % 2 == 0) {
                workOrder.setCommunityName("休伯利安");
            }else {
                workOrder.setCommunityName("璃月");
            }

            workOrderList.add(workOrder);
        }

        workOrderService.saveBatch(workOrderList);
    }

}
