package com.lyp.learn.utils.wrapper;


public interface RemoteCallable<T, R> {
    R call(T t) throws Exception;
}
