package com.equityposition.common;

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "Sucess"), //成功

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "Param invalid"), //参数无效
    PARAM_IS_BLANK(10002, "Param null"), //参数为空
    PARAM_TYPE_BIND_ERROR(10003, "Param type error"), //参数为空
    PARAM_NOT_COMPLETE(10004, "Param missing"), //参数缺失

    DATA_IS_ERROR(10005, " Data is error"); //数据错误


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
