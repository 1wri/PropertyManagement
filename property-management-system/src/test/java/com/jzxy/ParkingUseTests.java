package com.jzxy;

import com.jzxy.pojo.ParkingUse;
import com.jzxy.service.IParkingUseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位使用测试类
 * @date 2023/3/13 21:02
 */

@SpringBootTest
public class ParkingUseTests {

    @Autowired
    private IParkingUseService parkingUseService;

    @Test
    public void testInsert(){
        List<ParkingUse> parkingUseList = new ArrayList<>(51);

        for (int i = 0; i < 51; i++) {
            ParkingUse parkingUse = new ParkingUse();

            parkingUse.setCode("FC202303090");
            parkingUse.setCommunityName("休伯利安");
            parkingUse.setOwnerName("幽兰");
            parkingUse.setTelephone("15703575815");
            parkingUse.setStartTime(LocalDate.now());
            parkingUse.setEndTime(LocalDate.now().plusMonths(3));
            parkingUse.setUseType("月租");

            parkingUseList.add(parkingUse);
        }

        parkingUseService.saveBatch(parkingUseList);
    }
}
