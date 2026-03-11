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
import com.jzxy.dto.OwnerInfoDto;
import com.jzxy.mapper.OwnerMapper;
import com.jzxy.pojo.House;
import com.jzxy.pojo.Owner;
import com.jzxy.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主管理服务类
 * @date 2023/3/11 8:11
 */

@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements IOwnerService {
    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    private HouseServiceImpl houseService;

    /**
     * @description 查看所有业主信息(分页 )
     * @param owner 分页参数
     * @param pageParameter 条件查询参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 8:36
     */
    public Result getOwnerListByPage(Owner owner, PageParameter pageParameter){
        //设置分页参数
        IPage<Owner> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((owner.getName() != null && !("".equals(owner.getName()))),
                Owner::getName, owner.getName());
        queryWrapper.like((owner.getCommunityName() != null && !("".equals(owner.getCommunityName()))),
                Owner::getCommunityName, owner.getCommunityName());

        //根据分页参数查询
        page = ownerMapper.selectPage(page, queryWrapper);

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
            page = ownerMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Owner> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除业主信息
     * @param ownerIds 业主信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 9:00
     */
    public Result deleteOwner(Integer[] ownerIds){
        List<Integer> ownerList = Arrays.stream(ownerIds).collect(Collectors.toList());

        int flag = ownerMapper.deleteBatchIds(ownerList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 房产信息
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 15:36
     */
    public Result addOwner(Owner owner){
        return saveOrUpdate(owner) ? Result.success() : Result.fail();
    }

    /**
     * @description 根据Id查找业主信息
     * @param id 房产信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 16:49
     */
    public Result getOwnerById(Integer id){
        Owner owner = ownerMapper.selectById(id);

        if (BeanUtil.isEmpty(owner)) {
            return Result.fail();
        }

        return Result.success(owner);
    }

    /**
     * @description 业主登录
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 22:22
     */
    public Result ownerLogin(Owner owner){
        LambdaQueryWrapper<Owner> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Owner::getName, owner.getName());
        lambdaQueryWrapper.eq(Owner::getTelephone, owner.getTelephone());

        Owner selectOne = ownerMapper.selectOne(lambdaQueryWrapper);

        if (selectOne == null){
            Result.fail("姓名或手机号输入有误，请重新输入！！");
        }

        //根据业主姓名和电话号码查询房产信息
        House house = houseService.getHouseByOwnerInfo(selectOne);

        //封装返回数据
        OwnerInfoDto ownerInfoDto = new OwnerInfoDto();
        ownerInfoDto.setFloor(house.getFloor());
        ownerInfoDto.setCommunityName(house.getCommunityName());
        ownerInfoDto.setBuildingName(house.getBuildingName());
        ownerInfoDto.setName(selectOne.getName());
        ownerInfoDto.setTelephone(selectOne.getTelephone());
        ownerInfoDto.setRoomNum(house.getRoomNum());
        ownerInfoDto.setUnit(house.getUnit());

        return Result.success(ownerInfoDto);

    }

}
