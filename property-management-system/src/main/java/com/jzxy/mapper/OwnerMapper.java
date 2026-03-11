package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.Owner;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主管理Mapper
 * @date 2023/3/11 8:08
 */

@Mapper
public interface OwnerMapper extends BaseMapper<Owner> {

}
