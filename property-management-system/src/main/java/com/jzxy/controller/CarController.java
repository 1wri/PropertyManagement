package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Car;
import com.jzxy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车辆管理Controller
 * @date 2023/3/12 10:21
 */

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService carService;

    /**
    * @description 查看所有车辆信息(分页 )
    * @param car 车辆条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/12 10:37
    */
    @GetMapping("/list")
    public Result getCarListByPage(Car car, PageParameter pageParameter){
        return carService.getCarListByPage(car, pageParameter);
    }

    /**
    * @description 删除车辆信息
    * @param carIds 车辆信息的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/12 11:59
    */
    @DeleteMapping("/delete")
    public Result deleteCar(Integer[] carIds){
        return carService.deleteCar(carIds);
    }

    /**
    * @description 新增/更新 车辆信息
    * @param car 车辆信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/12 12:06
    */
    @PutMapping("/add")
    public Result addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    /**
    * @description 根据Id查找车辆信息
    * @param id 车辆的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/12 12:12
    */
    @GetMapping("/one")
    public Result getCarById(Integer id){
        return carService.getCarById(id);
    }
}
