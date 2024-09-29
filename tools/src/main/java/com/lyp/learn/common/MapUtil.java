package com.lyp.learn.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Map相关工具类
 *
 * @author at 2023/11/24 14:27
 */
@SuppressWarnings("unused")
public class MapUtil {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private MapUtil() {
    }

    /**
     * 判断一个map是否为非空或者非null
     *
     * @param map 传入的map
     * @return true:如果map为非null或者为非空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个map是否为空或者null
     *
     * @param map 传入的map
     * @return true:如果map为null或者为空
     * @since 1.0.0
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 如果map是null，返回一个空的map集合，否则返回一个自身
     *
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map map参数
     * @return 如果map是个null, 返回一个空map
     * @since 1.0.0
     */
    public static <K, V> Map<K, V> emptyIfNull(final Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    /**
     * 把当前map包装成一个不可修改的map
     *
     * @param map 当前map
     * @param <K> K的类型
     * @param <V> V的类型
     * @return 返回一个不可变的map
     * @since 1.0.0
     */
    public static <K, V> Map<K, V> unmodifiableMap(final Map<? extends K, ? extends V> map) {
        return Collections.unmodifiableMap(map);
    }

    /**
     * 创建一个 ConcurrentHashMap, 默认大小为 16
     *
     * @return ConcurrentMap
     * @since 1.0.0
     */
    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap<>(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 创建一个 HashMap, 默认大小为16
     *
     * @return HashMap
     * @since 1.0.0
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 创建HashMap 根据给定的预期大小
     *
     * @param expectedSize 预期大小
     * @return HashMap
     * @since 1.0.0
     */
    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(capacity(expectedSize));
    }

    /**
     * 根据给定的值，重新计算容量
     *
     * @return 容量
     * @since 1.0.0
     */
    public static int capacity(int expectedSize) {
        final int lowSize = 3;
        if (expectedSize < lowSize) {
            Assert.isTrue(expectedSize >= 0, "expectedSize must be >= 0");
            return expectedSize + 1;
        }
        if (expectedSize < IntUtil.MAX_POWER_OF_TWO) {
            return expectedSize + expectedSize / 3;
        }
        return Integer.MAX_VALUE;
    }
}
