package com.lyp.learn.streampk;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description 转换工具
 */

@Slf4j
public class SafeUtil {
    public static String safeToString(Object value) {
        return getValueOrDefault(value::toString, "");
    }

    /**
     * 通用取值方法（带格式转换）
     *
     * @param supplier     取值函数（如：skuInfoModel::getGuaranteeDays）
     * @param defaultValue 默认值
     * @param converter    格式转换函数（如：DateUtils::toYYYYMMDDFromMill）
     * @return 目标值或默认值
     */
    public static <T, R> R getValueOrDefault(Supplier<T> supplier,
                                             R defaultValue,
                                             Function<T, R> converter) {
        T value = supplier.get();
        return Objects.nonNull(value) ? converter.apply(value) : defaultValue;
    }

    /**
     * 通用取值方法（直接赋值，无需转换）
     */
    public static <T> T getValueOrDefault(Supplier<T> supplier, T defaultValue) {
        return getValueOrDefault(supplier, defaultValue, Function.identity());
    }
}