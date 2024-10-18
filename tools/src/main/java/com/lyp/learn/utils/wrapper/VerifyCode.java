package com.lyp.learn.utils.wrapper;

/**
 * @Author： guozhongquan
 * @CreateTime: 2022/05/18  5:20 下午
 * @Description:
 */
public interface VerifyCode<R> {
    boolean verify(R r);
}
