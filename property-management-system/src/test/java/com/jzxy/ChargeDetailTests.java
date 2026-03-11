package com.jzxy;

import com.jzxy.pojo.ChargeDetail;
import com.jzxy.service.IChargeDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费明细测试类
 * @date 2023/3/14 10:20
 */

@SpringBootTest
public class ChargeDetailTests {

    @Autowired
    private IChargeDetailService chargeDetailService;

    @Test
    public void testInsert(){
        List<ChargeDetail> chargeDetailList = new ArrayList<ChargeDetail>(51);

        for (int i = 0; i < 51; i++) {
            ChargeDetail chargeDetail = new ChargeDetail();

            if (i % 2 == 0) {
                chargeDetail.setCommunityName("休伯利安");
                chargeDetail.setChargeRuleName("物业基础费用");
                chargeDetail.setChargeRuleType("电梯费");
                chargeDetail.setOwnerName("幽兰");
                chargeDetail.setOwnerName("幽兰");
                chargeDetail.setOwnerTelephone("157065758" + i);
                chargeDetail.setChargeYear("2023");
                chargeDetail.setPayMoney(500.0);
                chargeDetail.setActualMoney(500.0);
                chargeDetail.setIsarrears("1");
                chargeDetail.setPayTime(LocalDate.now());

                chargeDetailList.add(chargeDetail);
            }else {
                chargeDetail.setCommunityName("休伯利安");
                chargeDetail.setChargeRuleName("物业基础费用");
                chargeDetail.setChargeRuleType("电梯费");
                chargeDetail.setOwnerName("丽塔");
                chargeDetail.setOwnerTelephone("157065758" + i);
                chargeDetail.setChargeYear("2023");
                chargeDetail.setPayMoney(500.0);
                chargeDetail.setActualMoney(400.0);
                chargeDetail.setIsarrears("0");
                chargeDetail.setPayTime(LocalDate.now());

                chargeDetailList.add(chargeDetail);
            }
        }
        chargeDetailService.saveBatch(chargeDetailList);
    }
}
