package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.dto.ParkingDto;
import com.jzxy.service.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位信息管理Controller
 * @date 2023/3/13 15:48
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private IParkingService parkingService;

    /**
    * @description 查看所有车位信息(分页 )
    * @param parkingDto 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 16:22
    */
    @GetMapping("/list")
    public Result getParkingListByPage(ParkingDto parkingDto, PageParameter pageParameter){
        return parkingService.getParkingListByPage(parkingDto, pageParameter);
    }

    /**
    * @description 删除车位信息
    * @param parkingIds 车位信息的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 17:50
    */
    @DeleteMapping("/delete")
    public Result deleteParking(Integer[] parkingIds){
        return parkingService.deleteParking(parkingIds);
    }

    /**
    * @description 新增/更新 车位信息
    * @param parkingDto 车位信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 18:27
    */
    @PutMapping("/add")
    public Result addParking(@RequestBody ParkingDto parkingDto){
        return parkingService.addParking(parkingDto);
    }

    /**
    * @description 根据Id查找车位信息
    * @param id 车位信息Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/13 18:43
    */
    @GetMapping("/one")
    public Result getParkingById(Integer id){
        return parkingService.getParkingById(id);
    }
}
