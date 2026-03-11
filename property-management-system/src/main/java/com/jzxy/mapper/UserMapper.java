package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 访客管理Mapper
 * @date 2023/3/8 15:35
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
