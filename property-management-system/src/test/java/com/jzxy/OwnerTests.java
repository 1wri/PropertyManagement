package com.jzxy;

import com.jzxy.pojo.Owner;
import com.jzxy.service.IOwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产管理测试类
 * @date 2023/3/11 8:14
 */

@SpringBootTest
//@Transactional
//@Rollback
public class OwnerTests {

    @Autowired
    private IOwnerService ownerService;

    //测试数据的插入
    @Test
    public void testInsert(){
        List<Owner> ownerList = new ArrayList<>(51);

        for (int i = 0; i < 51; i++) {
            Owner owner = new Owner();

            owner.setCommunityName("休伯利安");
            owner.setName("海月");
//            owner.setBirthday(LocalDate.now());
            owner.setFamilyMembers(i);
            owner.setTelephone("1570355715" + i);
            owner.setSex("女");
            owner.setIdcard("FC20230309" + i);

            ownerList.add(owner);
        }

        ownerService.saveBatch(ownerList);
    }
}
