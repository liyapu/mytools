package com.lyp.learn.common.function;

/**
 * {@link java.util.function.Function} 的扩展板
 *
 * @author at 2023/11/23 17:29
 */
@FunctionalInterface
public interface FunctionExtension<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Throwable Any
     */
    R apply(T t) throws Throwable;
}
