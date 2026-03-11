package com.jzxy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.ChargeDetailMapper;
import com.jzxy.pojo.ChargeDetail;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IChargeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费明细服务类
 * @date 2023/3/14 10:17
 */

@Service
public class ChargeDetailServiceImpl extends ServiceImpl<ChargeDetailMapper, ChargeDetail> implements IChargeDetailService {

    @Autowired
    private ChargeDetailMapper chargeDetailMapper;

    /**
     * @description 查看所有物业收费明细(分页 )
     * @param chargeDetail 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 10:38
     */
    public Result getChargeDetailListByPage(ChargeDetail chargeDetail, PageParameter pageParameter){
        //设置分页参数
        IPage<ChargeDetail> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<ChargeDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((chargeDetail.getIsarrears() != null && !"".equals(chargeDetail.getIsarrears())),
                ChargeDetail::getIsarrears, chargeDetail.getIsarrears());
        queryWrapper.like((chargeDetail.getOwnerName() != null && !"".equals(chargeDetail.getOwnerName())),
                ChargeDetail::getOwnerName, chargeDetail.getOwnerName());
        queryWrapper.like((chargeDetail.getChargeYear() != null && !"".equals(chargeDetail.getChargeYear())),
                ChargeDetail::getChargeYear, chargeDetail.getChargeYear());
        queryWrapper.eq((chargeDetail.getCommunityName() != null && !"".equals(chargeDetail.getCommunityName())),
                ChargeDetail::getCommunityName, chargeDetail.getCommunityName());

        //根据分页参数查询
        page = chargeDetailMapper.selectPage(page, queryWrapper);

        //查询的总条数
        double total = page.getTotal();
        //总的页面数
        double pageCount = Math.ceil(total / pageParameter.getPageSize());
        //判断当前查询的页面数是否大于总页面数
        if (pageParameter.getCurrentPage() > pageCount){
            //大于页面总数
            //把当前页数置为 1
            pageParameter.setCurrentPage(1);
            page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());
            //重新查询第一页数据
            page = chargeDetailMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<ChargeDetail> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 根据业主信息查询缴费情况
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 21:13
     */
    public Result getInfoByCondition(Owner owner){
        LambdaQueryWrapper<ChargeDetail> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(ChargeDetail::getOwnerName, owner.getName());
        queryWrapper.eq(ChargeDetail::getOwnerTelephone, owner.getTelephone());
        //只查询欠费的信息
        queryWrapper.eq(ChargeDetail::getIsarrears, 0);

        List<ChargeDetail> chargeDetailList = chargeDetailMapper.selectList(queryWrapper);

        if (chargeDetailList.size() == 0){
            return Result.fail("您当前没有需要缴纳的物业费用！！！");
        }

        return Result.success(chargeDetailList);
    }
}
