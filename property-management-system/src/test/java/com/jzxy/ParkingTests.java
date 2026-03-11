package com.jzxy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jzxy.config.mybatisplus.ParkingStateEnum;
import com.jzxy.dto.ParkingDto;
import com.jzxy.pojo.Parking;
import com.jzxy.service.IParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位管理测试类
 * @date 2023/3/13 15:46
 */

@SpringBootTest
public class ParkingTests {

    @Autowired
    private IParkingService parkingService;

    @Test
    public void testInsert(){
        List<Parking> parkingList = new ArrayList<Parking>(51);

        for (int i = 0; i < 51; i++) {
            Parking parking = new Parking();

            parking.setCode("FC20230309" + i);
            parking.setCommunityName("休伯利安");
            parking.setType("地面停车场");
            if (i % 2 == 0) {
                parking.setState(ParkingStateEnum.ONSALE);
            }else {
                parking.setState(ParkingStateEnum.STOPSALE);
            }
            parkingList.add(parking);
        }
        parkingService.saveBatch(parkingList);
    }

    @Test
    public void testSelect(){

        LambdaQueryWrapper<ParkingDto> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ParkingDto::getState, "在售");

    }
}
