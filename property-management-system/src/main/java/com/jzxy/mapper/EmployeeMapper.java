package com.jzxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzxy.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业员工管理Mapper
 * @date 2023/3/14 17:52
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
