package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;     //响应状态码
    private String msg;     //响应状态描述
    private Object data;    //响应数据

    public static Result success() {
        return new Result(1,"success",null); //响应成功，无需数据返回
    }

    public static Result success(Object data) {
        return new Result(1,"success",data);     //响应成功，返回数据
    }

    public static Result error(String msg) {
        return new Result(0,msg,null);      //响应失败，饭hi有描述信息
    }
}
