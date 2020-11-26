package com.equityposition.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResult <T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //成功，不返回具体数据
    public static <T> RestResult<T> successNoData(ResultCode code){
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
    //成功，返回数据
    public static <T> RestResult<T> success(T t, ResultCode code){
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    //失败，返回失败信息
    public static <T> RestResult<T> fail(ResultCode code){
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    //失败，返回失败信息
    public static <T> RestResult<T> failIncludeData(T t, ResultCode code){
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }
}
