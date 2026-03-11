package com.jzxy.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 龙殇
 * @version 1.0
 * @description MP通用枚举配置
 * @date 2023/3/13 15:39
 */
public enum ParkingStateEnum {

    ONSALE(0, "在售"), STOPSALE(1, "停售");

    @EnumValue
    @JsonValue
    private final int state;
    private final String descp;

    ParkingStateEnum(int state, String descp) {
        this.state = state;
        this.descp = descp;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
