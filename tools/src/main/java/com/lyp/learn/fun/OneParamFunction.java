package com.lyp.learn.fun;

@FunctionalInterface
public interface OneParamFunction<T,R> {

    /**
     * 有一个入参，有返回值
     *
     * @return a result
     */
    R apply(T t) throws Exception;
}
