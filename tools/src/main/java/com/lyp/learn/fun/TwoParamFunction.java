package com.lyp.learn.fun;

@FunctionalInterface
public interface TwoParamFunction<T1,T2,R> {
    /**
     * 有两个入参，有返回值
     *
     * @return a result
     */
    R apply(T1 t1, T2 t2) throws Exception;
}
