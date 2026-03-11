package com.jzxy;

import com.jzxy.pojo.ChargeRule;
import com.jzxy.service.IChargeRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费准则测试类
 * @date 2023/3/14 8:59
 */

@SpringBootTest
public class ChargeRuleTests {

    @Autowired
    private IChargeRuleService chargeRuleService;

    @Test
    public void testInsert(){
        List<ChargeRule> chargeRuleList = new ArrayList<ChargeRule>(51);

        for (int i = 0; i < 51; i++) {
            ChargeRule chargeRule = new ChargeRule();

            if (i % 2 == 0) {
                chargeRule.setCommunityName("休伯利安");
                chargeRule.setChargeName("物业基础费用");
                chargeRule.setChargeType("电梯费");
                chargeRule.setChargeYear("2023");
                chargeRule.setChargeMoney(500.0);

                chargeRuleList.add(chargeRule);
            }else {
                chargeRule.setCommunityName("璃月");
                chargeRule.setChargeName("服务费用");
                chargeRule.setChargeType("卫生费");
                chargeRule.setChargeYear("2023");
                chargeRule.setChargeMoney(100.0);

                chargeRuleList.add(chargeRule);
            }
        }
        chargeRuleService.saveBatch(chargeRuleList);
    }

}
