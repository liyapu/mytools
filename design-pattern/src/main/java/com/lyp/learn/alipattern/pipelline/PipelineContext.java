package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:02
 * @description
 */
public interface PipelineContext {

    String FOR_TEST = "forTest";

    /**
     * 设置
     *
     * @param contextKey
     * @param contextValue
     */
    void set(String contextKey, Object contextValue);

    /**
     * 获取值
     *
     * @param contextKey
     * @return
     */
    Object get(String contextKey);
}
