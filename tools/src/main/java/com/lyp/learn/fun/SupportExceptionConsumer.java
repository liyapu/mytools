package com.lyp.learn.fun;

/**
 * @description: 支持抛出异常的函数式接口
 */
@FunctionalInterface
public interface SupportExceptionConsumer<T> {
    /**
     * 有一个入参，没有返回值
     *
     */
    void accept(T t) throws Exception;
}
