package com.lyp.learn.common;

/**
 * IntUtil.java
 *
 * @author at 2023/11/24 14:17
 */
public final class IntUtil {

    private IntUtil() {
    }

    /**
     * 能用整数代替的最大整2的指数
     */
    public static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    public static final Integer ZERO = 0;

    /**
     * 把long 转换为最接近的 int
     */
    public static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }
}
