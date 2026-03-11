package com.jzxy.common;

import lombok.Data;

/**
 * @author 榫欐畤
 * @version 1.0
 * @description 返回给前端的结果类
 * @date 2023/3/7 21:22
 */

@Data
public class Result {

    private Object data;
    private Boolean result;
    private String msg;

    public static Result success(Object data){
        Result result = new Result();

        result.setData(data);
        result.setResult(true);

        return result;
    }

    public static Result success(){
        Result result = new Result();

        result.setResult(true);

        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();

        result.setMsg(msg);
        result.setResult(false);

        return result;
    }

    public static Result fail(Object data, String msg){
        Result result = new Result();

        result.setData(data);
        result.setMsg(msg);
        result.setResult(false);

        return result;
    }

    public static Result fail(){
        Result result = new Result();

        result.setResult(false);

        return result;
    }
}
