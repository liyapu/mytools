package com.lyp.learn.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * SetUtil.java
 *
 * @author at 2023/11/24 14:28
 */
@SuppressWarnings("unused")
public class SetUtil extends CollUtil {

    private SetUtil() {
    }

    /**
     * 根据给定的多个元素，创建HashSet
     */
    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> set = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * 创建一个新的可变的HashSet，并加入elements元素
     */
    public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
        return (elements instanceof Collection)
                ? new HashSet<>(CollUtil.cast(elements))
                : newHashSet(elements.iterator());
    }

    /**
     * 创建一个新的可变的HashSet，并加入elements
     */
    public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
        HashSet<E> set = newHashSet();
        addAll(set, elements);
        return set;
    }

    /**
     * 根据给定的大小，重新计算HashSet的容量
     */
    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(MapUtil.capacity(expectedSize));
    }
}
