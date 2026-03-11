package com.jzxy;

import com.jzxy.pojo.House;
import com.jzxy.service.IHouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产管理测试类
 * @date 2023/3/9 14:29
 */

@SpringBootTest
public class HouseTests {

    @Autowired
    private IHouseService houseService;

    //批量插入数据
    @Test
    public void testInsert(){

        List<House> houseList = new ArrayList<>(50);

        for (int i = 0; i < 51; i++) {
            House house = new House();

            house.setCommunityName("休伯利安");
            house.setBuildingName(String.valueOf(i));
            house.setFloor(i);
            house.setUnit(i);
            house.setTelephone("1570355715" + i);
            house.setOwnerId(888);
            house.setOwnerName("芽衣");
            house.setCode("FC20230309" + i);
            house.setRoomNum(i);

            houseList.add(house);
        }

        houseService.saveBatch(houseList);

    }

}
