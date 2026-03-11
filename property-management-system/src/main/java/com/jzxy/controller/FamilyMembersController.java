package com.jzxy.controller;

import com.jzxy.common.Result;
import com.jzxy.pojo.FamilyMembers;
import com.jzxy.service.IFamilyMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 家庭成员Controller
 * @date 2023/3/11 18:01
 */

@RestController
@RequestMapping("/familyMembers")
public class FamilyMembersController {

    @Autowired
    private IFamilyMembersService familyMembersService;

    /**
    * @description 查看所有家庭成员
    * @param ownerId 户主的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 18:31
    */
    @GetMapping("/list")
    public Result getFamilyMembers(Integer ownerId){
        return familyMembersService.getFamilyMembers(ownerId);
    }

    /**
    * @description 删除家庭成员
    * @param familyMembersId 家庭成员Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 19:06
    */
    @DeleteMapping("/delete")
    public Result deleteFamilyMembers(Integer familyMembersId){
        return familyMembersService.deleteFamilyMembers(familyMembersId);
    }

    /**
    * @description 新增家庭成员
    * @param familyMembers 家庭成员信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 20:20
    */
    @PutMapping("/add")
    public Result addFamilyMembers(@RequestBody FamilyMembers familyMembers){
        return familyMembersService.addFamilyMembers(familyMembers);
    }
}
