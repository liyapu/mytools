package com.lyp.learn.utils;

import java.math.BigDecimal;
import java.util.Objects;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author liyapu
 * @date 2022-07-21 11:48
 * @description
 */
public class NumberUtil {

    /**
     * 保留三位小数
     */
    private static final int SCALE_THREE = 3;

    /**
     * 除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    保留几位小数
     * @return 默认使用四舍五入方式，返回字符串去除尾部多余0的形式
     */
    public static String divide(Long dividend, Long divisor, int scale) {
        return divide(dividend, divisor, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     *
     * @param dividend     被除数
     * @param divisor      除数
     * @param scale        保留几位小数
     * @param roundingMode 舍入模式
     * @return 字符串去除尾部多余0的形式
     */
    public static String divide(Long dividend, Long divisor, int scale, int roundingMode) {
        if (Objects.isNull(divisor) || Objects.equals(NumberUtils.LONG_ZERO, divisor)) {
            return String.valueOf(NumberUtils.LONG_ZERO);
        }
        return BigDecimal.valueOf(dividend).divide(BigDecimal.valueOf(divisor), scale, roundingMode)
            .stripTrailingZeros()
            .toPlainString();
    }

    public static void main(String[] args) {
        System.out.println(divide(5L, 0L, 2));
        System.out.println(divide(0L, 2L, 2));
        System.out.println(divide(1566L, 2L, 2));
        System.out.println(divide(1561L, 1000L, 2));
        System.out.println(divide(1566L, 1000L, 2));
        System.out.println(divide(156600100L, 1000L, 2));
        System.out.println(divide(156600120L, 1000L, 2));
    }

}
