package com.lyp.learn.ex;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description 异常类
 */
public class BaseException extends RuntimeException {

    private String type;

    /**
     * 错误信息
     */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public String getType() {
        if (StringUtils.isNotEmpty(type)) {
            return type;
        }
        return msg;
    }

    public BaseException(String msg) {
        this("", msg, null);
    }

    public BaseException(String type, String msg) {
        this(type, msg, null);
    }

    public BaseException(String msg, Throwable cause) {
        this("", msg, cause);
    }

    public BaseException(String type, String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.type = type;
    }

    /**
     * 构建不打印堆栈的异常
     *
     * @param msg
     * @param writableStackTrace
     */
    public BaseException(String msg, boolean writableStackTrace) {
        super(msg, null, false, writableStackTrace);
        this.msg = msg;
        this.type = "";
    }
}
