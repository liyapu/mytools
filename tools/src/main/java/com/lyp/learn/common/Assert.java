package com.lyp.learn.common;

import org.springframework.lang.Nullable;

import java.util.function.Supplier;

/**
 * Assert.java
 *
 * @author at 2023/11/23 19:00
 */
@SuppressWarnings("unused")
public final class Assert {

    private Assert() {
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出给定的异常<br>
     *
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, IllegalArgumentException::new);
     * </pre>
     *
     * @param <X>        异常类型
     * @param expression 布尔值
     * @param supplier   指定断言不通过时抛出的异常
     * @throws X if expression is {@code false}
     * @since 1.0.0
     */
    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> supplier) throws X {
        if (LangUtil.isFalse(expression)) {
            throw supplier.get();
        }
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出 {@code IllegalArgumentException} 异常<br>
     *
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, "The value must be greater than zero");
     * </pre>
     *
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws IllegalArgumentException if expression is {@code false}
     * @since 1.0.0
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws
            IllegalArgumentException {
        isTrue(expression, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * 断言object不为{@code null}
     *
     * @param object  待校验object
     * @param message 错误提示信息
     * @since 1.0.1
     */
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言object不为{@code null}
     *
     * @param object   待校验object
     * @param supplier 异常提供
     * @throws T 异常
     * @since 1.0.8
     */
    public static <T extends Throwable> void notNull(@Nullable Object object, Supplier<T> supplier) throws T {
        if (object == null) {
            throw supplier.get();
        }
    }

    /**
     * 断言两个对象的类型相同
     * 如果不相同,抛出异常
     *
     * @param o1 对象1
     * @param o2 对象2
     * @since 1.0.1
     */
    public static void sameType(Object o1, Object o2, String message) {
        if (o1 == o2) {
            return;
        }
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException(message);
        }
        if (!o1.getClass().isInstance(o2)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言两个对象的类型相同
     * 如果不相同,抛出异常
     *
     * @param o1               对象1
     * @param o2               对象2
     * @param errorMsgTemplate 模板
     * @param params           参数
     * @since 1.0.1
     */
    public static void sameType(Object o1, Object o2, String errorMsgTemplate, Object... params) {
        sameType(o1, o2, StrUtil.format(errorMsgTemplate, params));
    }
}
