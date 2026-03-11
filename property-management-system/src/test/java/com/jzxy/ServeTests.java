package com.jzxy;

import com.jzxy.pojo.Serve;
import com.jzxy.service.IServeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 上门服务测试类
 * @date 2023/3/15 9:45
 */

@SpringBootTest
public class ServeTests {

    @Autowired
    private IServeService serveService;

    @Test
    public void testInsert(){
        List<Serve> serveList = new ArrayList<>(51);

        for (int i = 0; i < 51; i++) {
            Serve serve = new Serve();

            serve.setName("幽兰");
            serve.setTelephone("1570657425" + i);
            serve.setBuildingName("17号楼");
            serve.setUnit(5);
            serve.setFloor(15);
            serve.setRoomNum(4);
            serve.setDescription("楼道灯坏了，需要维修");
            serve.setCommitTime(LocalDate.now());

            if(i % 2 == 0) {
                serve.setCommunityName("休伯利安");
            }else {
                serve.setCommunityName("璃月");
            }

            serveList.add(serve);
        }

        serveService.saveBatch(serveList);
    }

}
