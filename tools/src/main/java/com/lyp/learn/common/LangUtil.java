package com.lyp.learn.common;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * LangUtil.
 *
 * @author at 2023/2/9 15:45
 */
@SuppressWarnings("unused")
public final class LangUtil {

    private LangUtil() {
    }

    /**
     * {@code false} 判断，用于替换使用 {@code !} 取反
     */
    public static boolean isFalse(Boolean b) {
        return Boolean.FALSE.equals(b);
    }

    /**
     * {@code false} 判断，用于替换使用 {@code !} 取反
     */
    public static boolean isFalse(@NonNull Supplier<Boolean> supplier) {
        return Boolean.FALSE.equals(supplier.get());
    }

    /**
     * {@code Object} 不等判断，用于替换使用 {@code !} 取反
     */
    public static boolean nonEquals(Object a, Object b) {
        return Boolean.FALSE.equals(Objects.equals(a, b));
    }
}
