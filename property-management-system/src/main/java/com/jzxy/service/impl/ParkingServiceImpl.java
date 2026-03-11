package com.jzxy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.config.mybatisplus.ParkingStateEnum;
import com.jzxy.dto.ParkingDto;
import com.jzxy.mapper.ParkingMapper;
import com.jzxy.pojo.Parking;
import com.jzxy.pojo.ParkingUse;
import com.jzxy.service.IParkingService;
import com.jzxy.service.IParkingUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位信息管理服务类
 * @date 2023/3/13 15:52
 */

@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements IParkingService {

    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private IParkingUseService parkingUseService;


    /**
     * @description 查看所有车位信息(分页 )
     * @param parkingDto 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 16:22
     */
    public Result getParkingListByPage(ParkingDto parkingDto, PageParameter pageParameter){
        //设置分页参数
        IPage<Parking> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Parking> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((parkingDto.getState() != null),
                Parking::getState, parkingDto.getState());
        queryWrapper.like((parkingDto.getCommunityName() != null && !"".equals(parkingDto.getCommunityName())),
                Parking::getCommunityName, parkingDto.getCommunityName());

        //根据分页参数查询
        page = parkingMapper.selectPage(page, queryWrapper);

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
            page = parkingMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Parking> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除车位信息
     * @param parkingIds 车位信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 17:50
     */
    public Result deleteParking(Integer[] parkingIds){
        List<Integer> parkingList = Arrays.stream(parkingIds).collect(Collectors.toList());

        int flag = parkingMapper.deleteBatchIds(parkingList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 车位信息
     * @param parkingDto 车位信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 18:27
     */
    public Result addParking(ParkingDto parkingDto){
        //判断是否是新增操作
        if(parkingDto.getId() == null) {
            //判断当前车位编号是否存在
            LambdaQueryWrapper<Parking> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Parking::getCode, parkingDto.getCode());
            queryWrapper.eq(Parking::getCommunityName, parkingDto.getCommunityName());
            Parking parking = getOne(queryWrapper);

            if (parking != null) {
                return Result.fail("当前车位已经添加，不可重复添加！");
            }
        }

        Parking parking = new Parking();

        BeanUtil.copyProperties(parkingDto, parking, CopyOptions.create());

        if(parkingDto.getState() == 0){
            //修改为在售状态
            LambdaQueryWrapper<ParkingUse> lambdaQuery = new LambdaQueryWrapper<>();
            lambdaQuery.eq(ParkingUse::getCode, parkingDto.getCode());
            ParkingUse parkingUse = parkingUseService.getOne(lambdaQuery);
            //判断当前停车位否有业主购买
            if (parkingUse != null){
                //有业主购买
                //判断使用时间是否过期
                if (LocalDate.now().isBefore(parkingUse.getEndTime())){
                    //使用时间尚未过期
                    return Result.fail("当前车位有业主购买，不可起售！");
                }
            }
            parking.setState(ParkingStateEnum.ONSALE);
        }else {
            //修改为停售状态
            parking.setState(ParkingStateEnum.STOPSALE);
        }

        return saveOrUpdate(parking) ? Result.success() : Result.fail("操作失败,请稍后再试!");
    }

    /**
     * @description 根据Id查找车位信息
     * @param id 车位信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/13 18:43
     */
    @GetMapping("/one")
    public Result getParkingById(Integer id){
        Parking parking = parkingMapper.selectById(id);

        if (BeanUtil.isEmpty(parking)) {
            return Result.fail();
        }

        return Result.success(parking);
    }
}
