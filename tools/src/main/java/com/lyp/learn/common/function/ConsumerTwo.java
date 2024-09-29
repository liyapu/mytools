package com.lyp.learn.common.function;

/**
 * ConsumerTwo
 *
 * @author   at 2023/12/19 14:42
 */
@FunctionalInterface
public interface ConsumerTwo<F, S> {

    /**
     * Applies this function to the given argument.
     *
     * @param first  first argument
     * @param second second argument
     */
    void accept(F first, S second);
}
