package com.lyp.learn.common.function;

/**
 * {@link java.util.function.Supplier} 的扩展板
 *
 * @author   at 2023/11/24 14:33
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface SupplierExtension<T> {

    /**
     * 执行一段逻辑，返回某个结果
     *
     * @return 结果
     *
     * @throws Throwable 异常类型
     * @since 1.0.2
     */
    T get() throws Throwable;
}
