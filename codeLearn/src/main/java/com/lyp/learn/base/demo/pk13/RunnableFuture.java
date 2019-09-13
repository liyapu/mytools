package com.lyp.learn.base.demo.pk13;

import java.util.concurrent.Future;

/**
 * A Future那是Runnable
 * 在成功执行run方法使完成Future并允许访问其结果。
 * @param <V> The result type returned by this Future's {@code get} method
 */
public interface RunnableFuture<V> extends Runnable, Future<V> {
    /**
     * 将此Future设置为其计算结果，除非已被取消。
     */
    void run();
}
