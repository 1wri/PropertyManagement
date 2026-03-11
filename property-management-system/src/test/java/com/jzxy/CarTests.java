package com.jzxy;

import com.jzxy.pojo.Car;
import com.jzxy.service.ICarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description T车位管理测试类
 * @date 2023/3/12 10:25
 */

@SpringBootTest
public class CarTests {

    @Autowired
    private ICarService carService;

    @Test
    public void testInsert(){
        List<Car> carList = new ArrayList<Car>(51);

        for (int i = 0; i < 51; i++) {
            Car car = new Car();

            car.setOwnerName("龙殇");
            car.setCarColor("白色");
            car.setCarNumber("晋D1576" + i);
            car.setCarType("法拉利");

            carList.add(car);
        }
        carService.saveBatch(carList);
    }
}
