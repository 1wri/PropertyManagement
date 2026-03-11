package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.User;

/**
 * @author 龙殇
 * @version 1.0
 * @description 访客管理服务接口
 * @date 2023/3/8 15:36
 */
public interface IUserService extends IService<User> {

    /**
     * @description 用户登录
     * @param user 用户信息(用户名，密码)
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 15:48
     */
    Result userLogin(User user);

    /**
     * @description 查看访客列表
     * @param userName  访客姓名
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 17:53
     */
     Result getUserList(String userName, PageParameter pageParameter);

    /**
     * @description 修改用户的账号状态
     * @param user 访客信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 21:52
     */
     Result modifyUserStatus(User user);

    /**
     * @description 新增用户
     * @param user  用户信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 22:10
     */
     Result addUser(User user);

    /**
     * @description 删除用户
     * @param id 用户Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 22:55
     */
    public Result deleteUser(Integer id);

    /**
     * @description 用户退出
     *
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 10:44
     */
    Result userLogout();
}
