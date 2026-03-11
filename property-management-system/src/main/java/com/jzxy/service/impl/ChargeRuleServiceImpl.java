package com.jzxy.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.ChargeRuleMapper;
import com.jzxy.pojo.ChargeDetail;
import com.jzxy.pojo.ChargeRule;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IChargeDetailService;
import com.jzxy.service.IChargeRuleService;
import com.jzxy.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业收费准则服务类
 * @date 2023/3/14 8:56
 */

@Service
public class ChargeRuleServiceImpl extends ServiceImpl<ChargeRuleMapper, ChargeRule> implements IChargeRuleService {

    @Autowired
    private ChargeRuleMapper chargeRuleMapper;
    @Autowired
    private IOwnerService ownerService;
    @Autowired
    private IChargeDetailService chargeDetailService;

    /**
     * @description 查看所有物业收费准则(分页 )
     * @param chargeRule 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:27
     */
    public Result getChargeRuleListByPage(ChargeRule chargeRule, PageParameter pageParameter){
        //设置分页参数
        IPage<ChargeRule> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<ChargeRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((chargeRule.getChargeYear() != null && !"".equals(chargeRule.getChargeYear())),
                ChargeRule::getChargeYear, chargeRule.getChargeYear());
        queryWrapper.like((chargeRule.getChargeName() != null && !"".equals(chargeRule.getChargeName())),
                ChargeRule::getChargeName, chargeRule.getChargeName());
        queryWrapper.eq((chargeRule.getCommunityName() != null && !"".equals(chargeRule.getCommunityName())),
                ChargeRule::getCommunityName, chargeRule.getCommunityName());

        //根据分页参数查询
        page = chargeRuleMapper.selectPage(page, queryWrapper);

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
            page = chargeRuleMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<ChargeRule> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除物业收费准则信息
     * @param chargeRuleIds 物业收费准则Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:42
     */
    public Result deleteChargeRule(Integer[] chargeRuleIds){
        List<Integer> houseList = Arrays.stream(chargeRuleIds).collect(Collectors.toList());

        int flag = chargeRuleMapper.deleteBatchIds(houseList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增物业收费准则
     * @param chargeRule 物业收费准则信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 9:54
     */
    @Transactional
    public Result addChargeRule(ChargeRule chargeRule){

        //查询当前物业收费属于哪个小区
        String communityName = chargeRule.getCommunityName();

        //从业主表中查询当前小区有哪些业主
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Owner::getCommunityName, communityName);
        List<Owner> ownerList = ownerService.list(queryWrapper);

        List<ChargeDetail> chargeDetailList = new ArrayList<>(ownerList.size());

        //遍历业主列表
        for (Owner owner : ownerList) {
            //为每个业主插入对应的收费明细信息
            ChargeDetail chargeDetail = new ChargeDetail();
            chargeDetail.setCommunityName(communityName);
            chargeDetail.setChargeRuleName(chargeRule.getChargeName());
            chargeDetail.setChargeRuleType(chargeRule.getChargeType());
            chargeDetail.setOwnerName(owner.getName());
            chargeDetail.setOwnerTelephone(owner.getTelephone());
            chargeDetail.setPayMoney(chargeRule.getChargeMoney());
            chargeDetail.setChargeYear(chargeRule.getChargeYear());

            chargeDetailList.add(chargeDetail);
        }
        chargeDetailService.saveBatch(chargeDetailList);

        return save(chargeRule) ? Result.success() : Result.fail();
    }
}
