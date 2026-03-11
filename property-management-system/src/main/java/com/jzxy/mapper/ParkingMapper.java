package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.Parking;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 车位信息管理Mapper
 * @date 2023/3/13 15:49
 */

@Mapper
public interface ParkingMapper extends BaseMapper<Parking> {

}
