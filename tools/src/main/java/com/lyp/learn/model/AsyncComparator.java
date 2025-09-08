package com.lyp.learn.model;

/**
 * 异步比较器
 * @param <T>
 */
public interface AsyncComparator<T> {

    /**
     * 结果比对
     * @param oldValue  旧逻辑读取结果
     * @param newValue  新逻辑读取结果
     * @return true：比对通过，false：比对不通过
     */
    boolean compare(T oldValue, T newValue);

    /**
     * 比对不通过后执行的行为
     * @param oldValue  旧逻辑读取结果
     * @param newValue  新逻辑读取结果
     */
    void actionWhenCompareFail(T oldValue, T newValue);
}
