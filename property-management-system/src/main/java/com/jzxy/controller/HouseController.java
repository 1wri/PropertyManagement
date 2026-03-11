package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.House;
import com.jzxy.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产管理Controller
 * @date 2023/3/9 14:35
 */

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private IHouseService houseService;

    /**
    * @description 查看所有房产信息(分页 )
    * @param pageParameter 分页参数
    * @param house 条件查询参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 15:12
    */
    @GetMapping("/list")
    public Result getHouseListByPage(House house, PageParameter pageParameter){
        return houseService.getHouseListByPage(house, pageParameter);
    }

    /**
    * @description 删除房产信息
    * @param houseIds  房产信息的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 16:46
    */
    @DeleteMapping("/delete")
    public Result deleteHouse(Integer[] houseIds){
        return houseService.deleteHouse(houseIds);
    }

    /**
    * @description 新增/更新 房产信息
    * @param house 房产信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 17:20
    */
    @PutMapping("/add")
    public Result addHouse(@RequestBody House house){
        return houseService.addHouse(house);
    }

    /**
    * @description 根据Id查找房产信息
    * @param id 房产信息Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 17:23
    */
    @GetMapping("/one")
    public Result getHouseById(Integer id){
        return houseService.getHouseById(id);
    }
}
