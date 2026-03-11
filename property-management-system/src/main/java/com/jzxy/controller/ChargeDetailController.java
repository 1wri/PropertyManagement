package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ChargeDetail;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IChargeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费明细Controller
 * @date 2023/3/14 10:18
 */

@RestController
@RequestMapping("chargeDetail")
public class ChargeDetailController {

    @Autowired
    private IChargeDetailService chargeDetailService;

    /**
    * @description 查看所有物业收费明细(分页 )
    * @param chargeDetail 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 10:38
    */
    @GetMapping("/list")
    public Result getChargeDetailListByPage(ChargeDetail chargeDetail, PageParameter pageParameter){
        return chargeDetailService.getChargeDetailListByPage(chargeDetail, pageParameter);
    }

    /**
    * @description 根据业主信息查询缴费情况
    * @param owner 业主信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/4/8 21:13
    */
    @GetMapping("/info")
    public Result getInfoByCondition(Owner owner){
        return chargeDetailService.getInfoByCondition(owner);
    }
}


