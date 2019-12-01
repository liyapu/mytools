package com.lyp.learn.utils;


import java.io.Serializable;

@SuppressWarnings("unchecked")
public class WebResult<T> implements Serializable {
    
    private static final long serialVersionUID = -8966506048246446398L;

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAILED = 500;

<<<<<<< HEAD
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILED = -1;

=======
>>>>>>> 52a40e8473b84690e4beb482ed2c0327a7a8c7ac
    public static final String MSG_EMPTY = "";
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAILED = "failed";

    private int code = CODE_SUCCESS;
    private String msg = MSG_SUCCESS;
    private T result;

<<<<<<< HEAD
=======
    public WebResult(){
    }
    public WebResult(int code, String msg) {
        this(code,msg,null);
    }
>>>>>>> 52a40e8473b84690e4beb482ed2c0327a7a8c7ac
    public WebResult(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static  WebResult<String> success(){
        return success(CODE_SUCCESS);
    }
    public static  WebResult<String> success(int code){
        return success(code, MSG_SUCCESS);
    }
    public static WebResult<String> success(int code,String msg){
        return success(code,msg, MSG_EMPTY);
    }

    public static  WebResult<String> success(String msg){
        return success(CODE_SUCCESS,msg);
    }

    public static <T> WebResult<T> success(String msg, T result){
        return success(CODE_SUCCESS,msg,result);
    }
    public static <T> WebResult<T> success(T result){
        return success(CODE_SUCCESS, MSG_SUCCESS,result);
    }

    public static <T> WebResult<T> success(int code, String msg, T result){
        return  new WebResult(code,msg,result);
    }

    public static  WebResult<String> failed(){
        return failed(CODE_FAILED);
    }
    public static  WebResult<String> failed(int code){
        return failed(code, MSG_FAILED);
    }

    public static  WebResult<String> failed(String msg){
        return failed(CODE_FAILED,msg);
    }

    public static WebResult<String> failed(int code,String msg){
        return new WebResult(code,msg, MSG_EMPTY);
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "WebResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}

