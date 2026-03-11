package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Employee;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业员工接口类
 * @date 2023/3/14 17:53
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * @description 查看所有员工信息(分页 )
     * @param employee 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:12
     */
    Result getEmployeeListByPage(Employee employee, PageParameter pageParameter);

    /**
     * @description 删除员工信息
     * @param employeeIds 员工的Id的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:26
     */
    Result deleteEmployee(Integer[] employeeIds);

    /**
     * @description 新增/更新 管理层员工
     * @param employee 管理层员工信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:43
     */
    Result addEmployee(@RequestBody Employee employee);

    /**
     * @description 根据Id查找员工信息
     * @param id 员工Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:51
     */
    Result getEmployeeById(Integer id);
}
