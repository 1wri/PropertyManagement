package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.dto.ParkingDto;
import com.jzxy.pojo.Parking;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位信息管理服务接口
 * @date 2023/3/13 15:51
 */
public interface IParkingService extends IService<Parking> {

    /**
     * @description 查看所有车位信息(分页 )
     * @param parkingDto 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 16:22
     */
    Result getParkingListByPage(ParkingDto parkingDto, PageParameter pageParameter);

    /**
     * @description 删除车位信息
     * @param parkingIds 车位信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 17:50
     */
    Result deleteParking(Integer[] parkingIds);

    /**
     * @description 新增/更新 车位信息
     * @param parkingDto 车位信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 18:27
     */
    Result addParking(@RequestBody ParkingDto parkingDto);

    /**
     * @description 根据Id查找车位信息
     * @param id 车位信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 18:43
     */
    Result getParkingById(Integer id);
}
