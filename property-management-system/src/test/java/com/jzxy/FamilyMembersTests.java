package com.jzxy;

import com.jzxy.pojo.FamilyMembers;
import com.jzxy.service.IFamilyMembersService;
import com.jzxy.service.IOwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 家庭成员测试类
 * @date 2023/3/11 18:07
 */

@SpringBootTest
public class FamilyMembersTests {

    @Autowired
    private IFamilyMembersService familyMembersService;
    @Autowired
    private IOwnerService ownerService;

    //批量插入数据
    @Test
    public void testInsert(){

        List<FamilyMembers> familyMembersList = new ArrayList<>(51);

        for (int i = 0; i < 51; i++) {
            FamilyMembers familyMembers = new FamilyMembers();

            familyMembers.setOwnerId(i);
            familyMembers.setName("艾瑞西亚");
            familyMembers.setBirthday(LocalDate.now());
            familyMembers.setTelephone("1570355715" + i);
            familyMembers.setSex("女");
            familyMembers.setIdcard("FC20230309" + i);

            familyMembersList.add(familyMembers);
        }

        familyMembersService.saveBatch(familyMembersList);

    }

    @Test
    public void testDelete(){
        Integer familyMembers = ownerService.getById(5).getFamilyMembers();

        System.out.println("familyMembers = " + familyMembers);

        ownerService.update()
                .set("family_members", familyMembers - 1)
                .eq("id", 5);
    }

}
