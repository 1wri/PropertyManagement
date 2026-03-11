package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ChargeRule;
import com.jzxy.service.IChargeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费准则Controller
 * @date 2023/3/14 8:58
 */

@RestController
@RequestMapping("/chargeRule")
public class ChargeRuleController {

    @Autowired
    private IChargeRuleService chargeRuleService;

    /**
    * @description 查看所有物业收费准则(分页 )
    * @param chargeRule 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 9:27
    */
    @GetMapping("/list")
    public Result getChargeRuleListByPage(ChargeRule chargeRule, PageParameter pageParameter){
        return chargeRuleService.getChargeRuleListByPage(chargeRule, pageParameter);
    }

    /**
    * @description 删除物业收费准则信息
    * @param chargeRuleIds 物业收费准则Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 9:42
    */
    @DeleteMapping("/delete")
    public Result deleteChargeRule(Integer[] chargeRuleIds){
        return chargeRuleService.deleteChargeRule(chargeRuleIds);
    }

    /**
    * @description 新增物业收费准则
    * @param chargeRule 物业收费准则信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 9:54
    */
    @PutMapping("/add")
    public Result addChargeRule(@RequestBody ChargeRule chargeRule){
        return chargeRuleService.addChargeRule(chargeRule);
    }

}
