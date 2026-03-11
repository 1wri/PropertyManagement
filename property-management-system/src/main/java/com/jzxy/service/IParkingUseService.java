package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ParkingUse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位使用管理服务接口
 * @date 2023/3/13 20:56
 */

public interface IParkingUseService extends IService<ParkingUse> {

    /**
     * @description 查看所有车位使用信息(分页 )
     * @param parkingUse 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 21:35
     */
    Result getParkingUseListByPage(ParkingUse parkingUse, PageParameter pageParameter);

    /**
     * @description 删除车位使用信息
     * @param parkingUseIds 车位使用信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 21:56
     */
    Result deleteParkingUse(Integer[] parkingUseIds);

    /**
     * @description 新增/更新 车位使用信息
     * @param parkingUse 车位使用信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 22:14
     */
    Result addParkingUse(@RequestBody ParkingUse parkingUse);

    /**
     * @description 根据Id查找车位使用信息
     * @param id 车位使用信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 22:16
     */
    Result getParkingUseById(Integer id);
}
