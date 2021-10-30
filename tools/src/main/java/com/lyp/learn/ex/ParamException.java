package com.lyp.learn.ex;


public class ParamException extends BaseException{

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(String type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String type, String message) {
        super(type, message);
    }

}
