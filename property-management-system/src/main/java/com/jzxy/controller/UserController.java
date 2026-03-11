package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.User;
import com.jzxy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 访客管理Controller
 * @date 2023/3/8 15:32
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
    * @description 用户登录
    * @param user 用户信息(用户名，密码)
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 15:48
    */
    @GetMapping("/login")
    public Result userLogin(User user){
        return userService.userLogin(user);
    }

    /**
    * @description 查看访客列表
    * @param userName  访客姓名
    * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 17:53
    */
    @GetMapping("/list")
    public Result getUserList(String userName, PageParameter pageParameter){
        return userService.getUserList(userName,pageParameter);
    }

    /**
    * @description 修改用户的账号状态
    * @param user 访客信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 21:52
    */
    @PutMapping("/modifyStatus")
    public Result modifyUserStatus(@RequestBody User user){
        return userService.modifyUserStatus(user);
    }

    /**
    * @description 新增用户
    * @param user  用户信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 22:10
    */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
    * @description 删除用户
    * @param id 用户Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 22:55
    */
    @DeleteMapping("/delete")
    public Result deleteUser(Integer id){
        return userService.deleteUser(id);
    }

    /**
    * @description 用户退出
    *
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 10:44
    */
    @PostMapping("/logout")
    public Result userLogout(){
        return userService.userLogout();
    }
}
