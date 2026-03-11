package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ParkingUse;
import com.jzxy.service.IParkingUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位使用管理Controller
 * @date 2023/3/13 20:59
 */

@RestController
@RequestMapping("/parkingUse")
public class ParkingUseController {

    @Autowired
    private IParkingUseService parkingUseService;

    /**
    * @description 查看所有车位使用信息(分页 )
    * @param parkingUse 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 21:35
    */
    @GetMapping("/list")
    public Result getParkingUseListByPage(ParkingUse parkingUse, PageParameter pageParameter){
        return parkingUseService.getParkingUseListByPage(parkingUse, pageParameter);
    }

    /**
    * @description 删除车位使用信息
    * @param parkingUseIds 车位使用信息的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 21:56
    */
    @DeleteMapping("/delete")
    public Result deleteParkingUse(Integer[] parkingUseIds){
        return parkingUseService.deleteParkingUse(parkingUseIds);
    }

    /**
    * @description 新增/更新 车位使用信息
    * @param parkingUse 车位使用信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 22:14
    */
    @PutMapping("/add")
    public Result addParkingUse(@RequestBody ParkingUse parkingUse){
        return parkingUseService.addParkingUse(parkingUse);
    }

    /**
    * @description 根据Id查找车位使用信息
    * @param id 车位使用信息Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 22:16
    */
    @GetMapping("/one")
    public Result getParkingUseById(Integer id){
        return parkingUseService.getParkingUseById(id);
    }

}
