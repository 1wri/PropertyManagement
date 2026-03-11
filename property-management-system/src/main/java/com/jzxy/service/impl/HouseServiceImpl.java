package com.jzxy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.HouseMapper;
import com.jzxy.pojo.House;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产管理服务类
 * @date 2023/3/9 14:33
 */

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {

    @Autowired
    private HouseMapper houseMapper;

    /**
     * @description 查看所有房产信息(分页 )
     * @param pageParameter 分页参数
     * @param house 条件查询参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 15:12
     */
    public Result getHouseListByPage(House house, PageParameter pageParameter){
        //设置分页参数
        IPage<House> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((house.getOwnerName() != null && !"".equals(house.getOwnerName())),
                House::getOwnerName, house.getOwnerName());
        queryWrapper.like((house.getCommunityName() != null && !"".equals(house.getCommunityName())),
                House::getCommunityName, house.getCommunityName());

        //根据分页参数查询
        page = houseMapper.selectPage(page, queryWrapper);

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
            page = houseMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<House> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除房产信息
     * @param houseIds  房产信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 16:46
     */
    public Result deleteHouse(Integer[] houseIds){
        List<Integer> houseList = Arrays.stream(houseIds).collect(Collectors.toList());

        int flag = houseMapper.deleteBatchIds(houseList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 房产信息
     * @param house 房产信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 17:20
     */
    public Result addHouse(House house){
        return saveOrUpdate(house) ? Result.success() : Result.fail();
    }

    /**
     * @description 根据Id查找房产信息
     * @param id 房产信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 17:23
     */
    public Result getHouseById(Integer id){
        House house = houseMapper.selectById(id);

        if (BeanUtil.isEmpty(house)) {
            return Result.fail();
        }

        return Result.success(house);
    }

    /**
     * @description 根据业主姓名和电话号码查询房产信息
     * @param owner 业主信息
     * @author 龙殇
     * @date 2023/4/7 13:53
     */
    public House getHouseByOwnerInfo(Owner owner) {
        //设置查询的条件
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                House::getOwnerName, owner.getName());
        queryWrapper.like(
                House::getTelephone, owner.getTelephone());

        //查询数据返回
        return houseMapper.selectOne(queryWrapper);
    }

}
