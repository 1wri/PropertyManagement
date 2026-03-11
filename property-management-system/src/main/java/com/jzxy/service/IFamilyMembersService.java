package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.Result;
import com.jzxy.pojo.FamilyMembers;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 榫欐畤
 * @version 1.0
 * @description 家庭成员接口类
 * @date 2023/3/11 18:04
 */
public interface IFamilyMembersService extends IService<FamilyMembers> {

    /**
     * @description 查看所有家庭成员
     * @param ownerId 户主的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 18:31
     */
    @GetMapping("/list")
    Result getFamilyMembers(Integer ownerId);

    /**
     * @description 删除家庭成员
     * @param familyMembersId 家庭成员Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 19:06
     */
    Result deleteFamilyMembers(Integer familyMembersId);

    /**
     * @description 新增家庭成员
     * @param familyMembers 家庭成员信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 20:20
     */
    Result addFamilyMembers(FamilyMembers familyMembers);
}
