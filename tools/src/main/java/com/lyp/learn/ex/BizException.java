package com.lyp.learn.ex;


/**
 * @Description BizException
 */
public class BizException extends BaseException {

    public BizException(String message) {
        super(message);
    }

    public BizException(String type, String desc) {
        super(type, desc);
    }
    public BizException(String type, String desc, Throwable cause) {
        super(type, desc, cause);
    }
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message, boolean writableStackTrace){
        super(message, writableStackTrace);
    }
}
