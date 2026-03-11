package com.jzxy;

import com.jzxy.pojo.Employee;
import com.jzxy.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 员工管理测试类
 * @date 2023/3/14 17:57
 */

@SpringBootTest
public class EmployeeTests {

    @Autowired
    private IEmployeeService employeeService;


    @Test
    public void testInsert(){
        List<Employee> employeeList = new ArrayList<Employee>(51);

        for (int i = 0; i < 51; i++) {
            Employee employee = new Employee();

            if (i % 2 == 0) {
                employee.setCommunityName("休伯利安");
                employee.setEmployeeName("幽兰");
                employee.setEmployeeSex("1");
                employee.setTelephone("157065842" + i);
                employee.setIdcard("14047852469880" + i);
                employee.setAge(22);
                employee.setEntryTime(LocalDate.now());
                employee.setOfficeType("0");
                employee.setOffice("经理");

                employeeList.add(employee);
            }else {
                employee.setCommunityName("休伯利安");
                employee.setEmployeeName("丽塔");
                employee.setEmployeeSex("1");
                employee.setTelephone("157065842" + i);
                employee.setIdcard("14047852469880" + i);
                employee.setAge(22);
                employee.setEntryTime(LocalDate.now());
                employee.setOfficeType("1");
                employee.setOffice("保洁");

                employeeList.add(employee);
            }
        }
        employeeService.saveBatch(employeeList);
    }

}
