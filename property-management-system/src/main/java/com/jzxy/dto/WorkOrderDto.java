package com.jzxy.dto;

import com.jzxy.pojo.WorkOrder;
import lombok.Data;

/**
 * @author 龙殇
 * @version 1.0
 * @description 工单Dto
 * @date 2023/4/7 20:33
 */

@Data
public class WorkOrderDto extends WorkOrder {

    private Integer serverId;

}
