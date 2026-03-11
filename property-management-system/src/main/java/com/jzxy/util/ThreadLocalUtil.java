package com.jzxy.util;

import com.jzxy.pojo.User;

/**
 * @author 龙殇
 * @version 1.0
 * @description ThreadLocal工具类
 * @date 2023/3/8 16:00
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static User getUserInfo(){
        return threadLocal.get();
    }

    public static void setUserInfo(User user){
         threadLocal.set(user);
    }

    public static void removeUserInfo(){
        threadLocal.remove();
    }
}
