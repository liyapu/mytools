package com.lyp.learn.utils;


import java.io.Serializable;

public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 1686004323683240269L;

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILED = -1;

    public static final String MSG_NONE = "";

    private int code = CODE_SUCCESS;
    private String msg = MSG_NONE;
    private T data;

    public WebResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static  WebResult<String> success(){
        return success(CODE_SUCCESS);
    }
    public static  WebResult<String> success(int code){
        return success(code,MSG_NONE);
    }
    public static WebResult<String> success(int code,String msg){
        return success(code,msg,MSG_NONE);
    }

    public static  WebResult<String> success(String msg){
        return success(CODE_SUCCESS,msg);
    }

    public static <T> WebResult<T> success(String msg, T data){
        return success(CODE_SUCCESS,msg,data);
    }
    public static <T> WebResult<T> success(T data){
        return success(CODE_SUCCESS,MSG_NONE,data);
    }

    public static <T> WebResult<T> success(int code, String msg, T data){
        return  new WebResult(code,msg,data);
    }

    public static  WebResult<String> failed(){
        return failed(CODE_FAILED);
    }
    public static  WebResult<String> failed(int code){
        return failed(code,MSG_NONE);
    }

    public static  WebResult<String> failed(String msg){
        return failed(CODE_FAILED,msg);
    }

    public static WebResult<String> failed(int code,String msg){
        return new WebResult(code,msg,MSG_NONE);
    }


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

    @Override
    public String toString() {
        return "WebResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

