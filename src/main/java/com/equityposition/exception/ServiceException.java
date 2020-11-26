package com.equityposition.exception;

public class ServiceException extends RuntimeException {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

    public ServiceException(String msg,int code){
        super(msg);
        this.code=code;
    }
}
