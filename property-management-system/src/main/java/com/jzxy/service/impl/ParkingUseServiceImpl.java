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
import com.jzxy.config.mybatisplus.ParkingStateEnum;
import com.jzxy.mapper.ParkingUseMapper;
import com.jzxy.pojo.Parking;
import com.jzxy.pojo.ParkingUse;
import com.jzxy.service.IParkingService;
import com.jzxy.service.IParkingUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位使用管理服务类
 * @date 2023/3/13 20:57
 */

@Service
public class ParkingUseServiceImpl extends ServiceImpl<ParkingUseMapper, ParkingUse> implements IParkingUseService {

    @Autowired
    private ParkingUseMapper parkingUseMapper;
    @Autowired
    private IParkingService parkingService;


    /**
     * @description 查看所有车位使用信息(分页 )
     * @param parkingUse 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 21:35
     */
    public Result getParkingUseListByPage(ParkingUse parkingUse, PageParameter pageParameter){
        //设置分页参数
        IPage<ParkingUse> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<ParkingUse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((parkingUse.getOwnerName() != null  && !"".equals(parkingUse.getOwnerName())),
                ParkingUse::getOwnerName, parkingUse.getOwnerName());
        queryWrapper.like((parkingUse.getUseType() != null && !"".equals(parkingUse.getUseType())),
                ParkingUse::getUseType, parkingUse.getUseType());

        //根据分页参数查询
        page = parkingUseMapper.selectPage(page, queryWrapper);

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
            page = parkingUseMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<ParkingUse> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除车位使用信息
     * @param parkingUseIds 车位使用信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 21:56
     */
    public Result deleteParkingUse(Integer[] parkingUseIds){
        List<Integer> parkingList = Arrays.stream(parkingUseIds).collect(Collectors.toList());

        int flag = parkingUseMapper.deleteBatchIds(parkingList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 车位使用信息
     * @param parkingUse 车位使用信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 22:14
     */
    @Transactional
    public Result addParkingUse(ParkingUse parkingUse){
        //判断是否是新增操作
        if(parkingUse.getId() == null){
            LambdaQueryWrapper<Parking> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Parking::getCode, parkingUse.getCode());
            queryWrapper.eq(Parking::getCommunityName, parkingUse.getCommunityName());
            Parking parking = parkingService.getOne(queryWrapper);
            if (parking == null){
                return Result.fail("当前车位编号不存在，请重新输入！");
            }
            if (parking.getState() == ParkingStateEnum.STOPSALE){
                return Result.fail("当前车位处于停售状态，不可购买！");
            }
            //新增车位使用信息
            save(parkingUse);
        }

        //更新车位信息表
        parkingService.update()
                .eq("code", parkingUse.getCode())
                .set("state", 1)
                .update();

        //更新车位使用表
        return updateById(parkingUse) ?
                Result.success() : Result.fail("更新失败");
    }

    /**
     * @description 根据Id查找车位使用信息
     * @param id 车位使用信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 22:16
     */
    public Result getParkingUseById(Integer id){
        ParkingUse parkingUse = parkingUseMapper.selectById(id);

        if (BeanUtil.isEmpty(parkingUse)) {
            return Result.fail();
        }

        return Result.success(parkingUse);
    }
}
