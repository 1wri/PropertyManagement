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
import com.jzxy.mapper.CarMapper;
import com.jzxy.pojo.Car;
import com.jzxy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车辆信息服务类
 * @date 2023/3/12 10:20
 */

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * @description 查看所有车辆信息(分页 )
     * @param car 车辆条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 10:37
     */
    public Result getCarListByPage(Car car, PageParameter pageParameter){
        //设置分页参数
        IPage<Car> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((car.getOwnerName() != null && !"".equals(car.getOwnerName())),
                Car::getOwnerName, car.getOwnerName());
        queryWrapper.like((car.getCarNumber() != null && !"".equals(car.getCarNumber())),
                Car::getCarNumber, car.getCarNumber());

        //根据分页参数查询
        page = carMapper.selectPage(page, queryWrapper);

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
            page = carMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Car> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除车辆信息
     * @param carIds 车辆信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 11:59
     */
    public Result deleteCar(Integer[] carIds){
        List<Integer> carList = Arrays.stream(carIds).collect(Collectors.toList());

        int flag = carMapper.deleteBatchIds(carList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 车辆信息
     * @param car 车辆信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 12:06
     */
    public Result addCar(@RequestBody Car car){
        return saveOrUpdate(car) ? Result.success() : Result.fail();
    }

    /**
     * @description 根据Id查找车辆信息
     * @param id 车辆的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/12 12:12
     */
    public Result getCarById(Integer id){
        Car car = carMapper.selectById(id);

        if (BeanUtil.isEmpty(car)) {
            return Result.fail();
        }

        return Result.success(car);
    }

}
