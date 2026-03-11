package com.jzxy.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jzxy.util.ThreadLocalUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author 龙殇
 * @version 1.0
 * @description MP的字段自动填充配置
 * @date 2023/3/9 12:34
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(
                metaObject,
                "createUser",
                String.class,
                ThreadLocalUtil.getUserInfo().getUserName());

        this.strictInsertFill(
                metaObject,
                "updateUser",
                String.class,
                ThreadLocalUtil.getUserInfo().getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(
                metaObject,
                "updateUser",
                String.class,
                ThreadLocalUtil.getUserInfo().getUserName());
    }
}
