package com.lyp.learn.fun;

@FunctionalInterface
public interface SupportExceptionSupplier<T> {
    /**
     * 没有入参，有返回值
     *
     * @return a result
     */
    T get() throws Exception;
}
