package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ChargeRule;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费准则接口类
 * @date 2023/3/14 8:55
 */
public interface IChargeRuleService extends IService<ChargeRule> {

    /**
     * @description 查看所有物业收费准则(分页 )
     * @param chargeRule 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:27
     */
    Result getChargeRuleListByPage(ChargeRule chargeRule, PageParameter pageParameter);

    /**
     * @description 删除物业收费准则信息
     * @param chargeRuleIds 物业收费准则Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:42
     */
    Result deleteChargeRule(Integer[] chargeRuleIds);

    /**
     * @description 新增物业收费准则
     * @param chargeRule 物业收费准则信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:54
     */
    Result addChargeRule(ChargeRule chargeRule);
}
