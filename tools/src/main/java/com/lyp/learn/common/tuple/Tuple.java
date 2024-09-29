package com.lyp.learn.common.tuple;

import java.io.Serializable;

/**
 * Tuple基类
 *
 * @author at 2023/12/24 21:55
 */
public abstract class Tuple implements Serializable {

    /**
     * 创建Tuple2
     *
     * @param t1   第一个值
     * @param t2   第二个值
     * @param <T1> 第一个值的元素类型
     * @param <T2> 第二个值的元素类型
     * @return {@link Tuple2}
     */
    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    /**
     * 创建Tuple3
     *
     * @param t1   第一个值
     * @param t2   第二个值
     * @param t3   第三个值
     * @param <T1> 第一个值的元素类型
     * @param <T2> 第二个值的元素类型
     * @param <T3> 第三个值的元素类型
     * @return {@link Tuple3}
     */
    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }
}
