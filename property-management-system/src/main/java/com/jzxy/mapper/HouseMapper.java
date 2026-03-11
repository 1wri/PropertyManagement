package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.House;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房屋管理Mapper
 * @date 2023/3/9 14:32
 */

@Mapper
public interface HouseMapper extends BaseMapper<House> {

}
