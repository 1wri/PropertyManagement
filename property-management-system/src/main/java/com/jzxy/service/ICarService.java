package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Car;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车辆信息接口类
 * @date 2023/3/12 10:19
 */
public interface ICarService extends IService<Car> {

    /**
     * @description 查看所有车辆信息(分页 )
     * @param car 车辆条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 10:37
     */
    Result getCarListByPage(Car car, PageParameter pageParameter);

    /**
     * @description 删除车辆信息
     * @param carIds 车辆信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 11:59
     */
    Result deleteCar(Integer[] carIds);

    /**
     * @description 新增/更新 车辆信息
     * @param car 车辆信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 12:06
     */
    Result addCar(@RequestBody Car car);

    /**
     * @description 根据Id查找车辆信息
     * @param id 车辆的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 12:12
     */
    public Result getCarById(Integer id);
}
