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
import com.jzxy.mapper.EmployeeMapper;
import com.jzxy.pojo.Employee;
import com.jzxy.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 龙殇
 * @version 1.0
 * @description 物业员工服务类
 * @date 2023/3/14 17:54
 */

@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @description 查看所有员工信息(分页 )
     * @param employee 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:12
     */
    public Result getEmployeeListByPage(Employee employee, PageParameter pageParameter){
        //设置分页参数
        IPage<Employee> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        //管理层员工
        if ("0".equals(employee.getOfficeType())) {
            queryWrapper.eq(Employee::getOfficeType, 0);
            queryWrapper.like((employee.getEmployeeName() != null && !"".equals(employee.getEmployeeName())),
                    Employee::getEmployeeName, employee.getEmployeeName());
            queryWrapper.like((employee.getEmployeeSex() != null && !"".equals(employee.getEmployeeSex())),
                    Employee::getEmployeeSex, employee.getEmployeeSex());
        }else {
            queryWrapper.eq(Employee::getOfficeType, 1);
            queryWrapper.like((employee.getEmployeeName() != null && !"".equals(employee.getEmployeeName())),
                    Employee::getEmployeeName, employee.getEmployeeName());
            queryWrapper.like((employee.getCommunityName() != null && !"".equals(employee.getCommunityName())),
                    Employee::getCommunityName, employee.getCommunityName());
            queryWrapper.like((employee.getOffice() != null && !"".equals(employee.getOffice())),
                    Employee::getOffice, employee.getOffice());
        }
        //根据分页参数查询
        page = employeeMapper.selectPage(page, queryWrapper);

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
            page = employeeMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Employee> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除员工信息
     * @param employeeIds 员工的Id的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:26
     */
    public Result deleteEmployee(Integer[] employeeIds){
        List<Integer> employeeList = Arrays.stream(employeeIds).collect(Collectors.toList());

        int flag = employeeMapper.deleteBatchIds(employeeList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 新增/更新 员工
     * @param employee 员工信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:43
     */
    public Result addEmployee(@RequestBody Employee employee){
        return saveOrUpdate(employee) ? Result.success() : Result.fail();
    }

    /**
     * @description 根据Id查找员工信息
     * @param id 员工Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/14 18:51
     */
    public Result getEmployeeById(Integer id){
        Employee employee = employeeMapper.selectById(id);

        if (BeanUtil.isEmpty(employee)) {
            return Result.fail();
        }

        return Result.success(employee);
    }

}
