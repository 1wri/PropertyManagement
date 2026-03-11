package com.jzxy.config;

import com.jzxy.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 龙殇
 * @version 1.0
 * @description webMvc相关配置
 * @date 2023/3/7 23:10
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns(
                        "/user/*",
                        "/notice/*",
                        "/house/*",
                        "/owner/*",
                        "/familyMembers/*",
                        "/parking/*",
                        "/parkingUse/*",
                        "/chargeRule/*",
                        "/chargeDetail/*",
                        "/car/*",
                        "/employee/*",
                        "/serve/*",
                        "/workOrder/*"
                )
                .excludePathPatterns(
                        "/user/login",
                        "/owner/login",
                        "/notice/list",
                        "/serve/add",
                        "/serve/selectByOwner",
                        "/workOrder/select",
                        "/workOrder/comment",
                        "/serve/update",
                        "/serve/info",
                        "/serve/historyInfo",
                        "/chargeDetail/info"
        );
    }
}
