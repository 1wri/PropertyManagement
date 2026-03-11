package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.ChargeDetail;
import com.jzxy.pojo.Owner;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费明细接口类
 * @date 2023/3/14 10:16
 */
public interface IChargeDetailService extends IService<ChargeDetail> {

    /**
     * @description 查看所有物业收费明细(分页 )
     * @param chargeDetail 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 10:38
     */
    Result getChargeDetailListByPage(ChargeDetail chargeDetail, PageParameter pageParameter);

    /**
     * @description 根据业主信息查询缴费情况
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 21:13
     */
    Result getInfoByCondition(Owner owner);
}
