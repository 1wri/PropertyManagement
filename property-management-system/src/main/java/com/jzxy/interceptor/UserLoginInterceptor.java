package com.jzxy.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.jzxy.common.Result;
import com.jzxy.pojo.User;
import com.jzxy.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 龙殇
 * @version 1.0
 * @description 用户登录拦截器
 * @date 2023/3/8 23:14
 */

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = httpSession.getAttribute("userInfo");

        if (ObjectUtil.isEmpty(userInfo)) {
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail("NOTLOGIN")));
            return false;
        }

        User user = (User) userInfo;

        ThreadLocalUtil.setUserInfo(user);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.removeUserInfo();
    }
}
