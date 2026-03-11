package com.jzxy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.UserMapper;
import com.jzxy.pojo.User;
import com.jzxy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * @author 龙殇
 * @version 1.0
 * @description 访客管理服务类
 * @date 2023/3/8 15:37
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    /**
     * @description 用户登录
     * @param user 用户信息(用户名，密码)
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 15:48
     */
    public Result userLogin(User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //封装查询条件
        queryWrapper.eq(User::getUserName, user.getUserName())
                .eq(User::getPassword, MD5.create().digestHex16(user.getPassword()));

        User queryUser = userMapper.selectOne(queryWrapper);

        if (BeanUtil.isEmpty(queryUser)) {
            return  Result.fail("用户名或密码错误");
        }

        if(queryUser.getStatus() == 0){
            return  Result.fail("账号已被禁止登录,请联系超级管理员！");
        }

        //封装返回数据(只保存用户名，用户身份)
        User returnUser = new User();
        returnUser.setUserName(queryUser.getUserName());
        returnUser.setIdentity(queryUser.getIdentity());

        httpSession.setAttribute("userInfo", returnUser);

        return Result.success(returnUser);
    }

    /**
     * @description 查看访客列表
     * @param userName  访客姓名
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 17:53
     */
    public Result getUserList(String userName, PageParameter pageParameter){
        //判断是否有查询条件
        if (!"".equals(userName)){
            //有查询条件,把当前页数置为 1
            pageParameter.setCurrentPage(1);
        }

        //设置分页参数
        IPage<User> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUserName, userName);

        //根据分页参数查询
        page = userMapper.selectPage(page, queryWrapper);

        ReturnPageData<User> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 修改用户的账号状态
     * @param user 访客信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 21:52
     */
    public Result modifyUserStatus(User user){
        int flag = userMapper.updateById(user);

        return flag > 0 ? Result.success() : Result.fail();
    }

    /**
     * @description 新增用户
     * @param user  用户信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 22:10
     */
    public Result addUser(User user){
        //给用户密码添加MD5加密
        user.setPassword(
                MD5.create().digestHex16(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        int flag = userMapper.insert(user);

        return flag > 0 ? Result.success() : Result.fail();
    }

    /**
     * @description 删除用户
     * @param id 用户Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 22:55
     */
    public Result deleteUser(Integer id){
        int flag = userMapper.deleteById(id);

        return flag > 0 ? Result.success() : Result.fail();
    }

    /**
     * @description 用户退出
     *
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 10:44
     */
    public Result userLogout(){
        httpSession.removeAttribute("userInfo");

        return Result.success();
    }
}
