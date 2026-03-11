package com.jzxy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.Result;
import com.jzxy.mapper.FamilyMembersMapper;
import com.jzxy.pojo.FamilyMembers;
import com.jzxy.service.IFamilyMembersService;
import com.jzxy.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 家庭成员服务类
 * @date 2023/3/11 18:04
 */

@Service
public class FamilyMembersImpl extends ServiceImpl<FamilyMembersMapper, FamilyMembers> implements IFamilyMembersService {

    @Autowired
    private FamilyMembersMapper familyMembersMapper;
    @Autowired
    private IOwnerService ownerService;

    /**
     * @description 查看所有家庭成员
     * @param ownerId 户主的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 18:31
     */
    public Result getFamilyMembers(Integer ownerId){
        LambdaQueryWrapper<FamilyMembers> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(FamilyMembers::getOwnerId, ownerId);

        List<FamilyMembers> familyMembersList = familyMembersMapper.selectList(queryWrapper);

        return
                CollectionUtil.isEmpty(familyMembersList) ?
                        Result.fail("当前户主没有家庭成员！") : Result.success(familyMembersList);
    }

    /**
     * @description 删除家庭成员
     * @param familyMembersId 家庭成员Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 19:06
     */
    @Transactional
    public Result deleteFamilyMembers(Integer familyMembersId){

        Integer ownerId = familyMembersMapper.selectById(familyMembersId).getOwnerId();

        int flag = familyMembersMapper.deleteById(familyMembersId);

        //当前户主的家庭成员数 - 1
        if (flag > 0) {
            Integer familyMembers = ownerService.getById(ownerId).getFamilyMembers();
            ownerService.update()
                    .set("family_members", familyMembers - 1)
                    .eq("id", ownerId)
            .update();
        }

        return  Result.success(ownerId);
    }

    /**
     * @description 新增家庭成员
     * @param familyMembers 家庭成员信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 20:20
     */
    @Transactional
    public Result addFamilyMembers(FamilyMembers familyMembers){
        int flag = familyMembersMapper.insert(familyMembers);

        //当前户主的家庭成员数 + 1
        if (flag > 0){
            Integer members = ownerService.getById(familyMembers.getOwnerId()).getFamilyMembers();
            ownerService.update()
                    .set("family_members", members + 1)
                    .eq("id", familyMembers.getOwnerId())
            .update();

            return Result.success(familyMembers.getOwnerId());
        }

        return Result.fail();
    }

}
