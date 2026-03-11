package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.Car;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车辆信息Mapper
 * @date 2023/3/12 10:18
 */

@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
