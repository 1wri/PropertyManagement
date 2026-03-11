package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主管理Controller
 * @date 2023/3/11 8:13
 */

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private IOwnerService ownerService;

    /**
    * @description 查看所有业主信息(分页 )
    * @param owner 分页参数
     * @param pageParameter 条件查询参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 8:36
    */
    @GetMapping("/list")
    public Result getOwnerListByPage(Owner owner, PageParameter pageParameter){
        return ownerService.getOwnerListByPage(owner, pageParameter);
    }

    /**
    * @description 删除业主信息
    * @param ownerIds 业主信息的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 9:00
    */
    @DeleteMapping("/delete")
    public Result deleteOwner(Integer[] ownerIds){
        return ownerService.deleteOwner(ownerIds);
    }

    /**
    * @description 新增/更新 房产信息
    * @param owner 业主信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 15:36
    */
    @PutMapping("/add")
    public Result addOwner(@RequestBody Owner owner){
        return ownerService.addOwner(owner);
    }

    /**
    * @description 根据Id查找业主信息
    * @param id 房产信息Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 16:49
    */
    @GetMapping("/one")
    public Result getOwnerById(Integer id){
        return ownerService.getOwnerById(id);
    }

    /**
    * @description 业主登录
    * @param owner 业主信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/15 22:22
    */
    @GetMapping("/login")
    public Result ownerLogin(Owner owner){
        return ownerService.ownerLogin(owner);
    }
}
