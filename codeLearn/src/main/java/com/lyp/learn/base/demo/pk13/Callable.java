package com.lyp.learn.base.demo.pk13;
/**
 * 返回结果并可能引发异常的任务。
 * 实现者定义一个没有参数的单一方法，称为call 。
 * Callable接口类似于Runnable ，因为它们都是为其实例可能由另一个线程执行的类设计的。
 * 然而，A Runnable不返回结果，也不能抛出被检查的异常。
 *
 * 该Executors类包含的实用方法，从其他普通形式转换为Callable类。
 * @since 1.5
 * @author Doug Lea
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Callable<V> {
    /**
     * 计算一个结果，如果不能这样做，就会抛出一个异常。
     *
     * @return 计算结果
     * @throws Exception 如果无法计算结果
     */
    V call() throws Exception;
}
