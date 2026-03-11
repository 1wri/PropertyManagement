package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Employee;
import com.jzxy.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业员工管理Controller
 * @date 2023/3/14 17:55
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
    * @description 查看所有员工信息(分页 )
    * @param employee 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 18:12
    */
    @GetMapping("/list")
    public Result getEmployeeListByPage(Employee employee, PageParameter pageParameter){
        return employeeService.getEmployeeListByPage(employee, pageParameter);
    }

    /**
    * @description 删除员工信息
    * @param employeeIds 员工的Id的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 18:26
    */
    @DeleteMapping("/delete")
    public Result deleteEmployee(Integer[] employeeIds){
        return employeeService.deleteEmployee(employeeIds);
    }

    /**
    * @description 新增/更新 员工
    * @param employee 员工信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 18:43
    */
    @PutMapping("/add")
    public Result addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    /**
    * @description 根据Id查找员工信息
    * @param id 员工Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/14 18:51
    */
    @GetMapping("/one")
    public Result getEmployeeById(Integer id){
        return employeeService.getEmployeeById(id);
    }

}
