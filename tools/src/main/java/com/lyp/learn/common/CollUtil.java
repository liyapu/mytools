package com.lyp.learn.common;

import org.springframework.lang.NonNull;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 集合相关工具类
 * <p>
 * 此工具方法针对{@link Collection}及其实现类封装的工具。
 * <p>
 *
 * @author at 2023/11/23 17:25
 */
@SuppressWarnings("unused")
public class CollUtil {

    private static final int INDEX_NOT_FOUND = -1;

    protected CollUtil() {

    }

    /**
     * 把迭代器(Iterator)中的所有元素 全部加入 集合中（Collection）
     *
     * @param addTo    集合
     * @param iterator 迭代器
     * @param <T>      类型
     * @return 如果集合发生了改变，返回true
     * @since 1.0.0
     */
    public static <T> boolean addAll(@NonNull Collection<T> addTo, @NonNull Iterator<? extends T> iterator) {
        boolean wasModified = false;
        while (iterator.hasNext()) {
            wasModified |= addTo.add(iterator.next());
        }
        return wasModified;
    }

    /**
     * 强制类型转换 Iterable -> Collection
     *
     * @param iterable 带转换的迭代器
     * @param <T>      元素类型
     * @return 集合类型
     * @since 1.0.0
     */
    public static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection<T>) iterable;
    }

    /**
     * 如果提供的集合为{@code null}，返回一个不可变的默认空集合，否则返回原集合<br>
     *
     * @param <T>  集合元素类型
     * @param coll 提供的集合，可能为null
     * @return 原集合，若为null返回空集合
     * @since 1.0.0
     */
    public static <T> Collection<T> emptyIfNull(final Collection<T> coll) {
        //noinspection unchecked
        return coll == null ? Collections.EMPTY_LIST : coll;
    }

    /**
     * 如果提供的{@link List}为{@code null}，返回一个不可变的默认空{@link List}，否则返回原{@link List}<br>
     *
     * @param <T>  元素类型
     * @param list 提供的{@link List}，可能为null
     * @return 原List，若为null返回空List
     * @since 1.0.0
     */
    public static <T> List<T> emptyIfNull(final List<T> list) {
        //noinspection unchecked
        return list == null ? Collections.EMPTY_LIST : list;
    }

    /**
     * 如果提供的{@link Set}为{@code null}，返回一个不可变的默认空{@link Set}，否则返回原{@link Set}<br>
     *
     * @param <T> 元素类型
     * @param set 提供的{@link Set}，可能为null
     * @return 原Set，若为null返回空Set
     * @since 1.0.0
     */
    public static <T> Set<T> emptyIfNull(final Set<T> set) {
        //noinspection unchecked
        return set == null ? Collections.EMPTY_SET : set;
    }

    /**
     * 过滤集合里面的{@code null}, 返回一个空的{@link Stream}或者包含非空元素的流
     *
     * @param coll 提供的集合，可能为null
     * @param <T>  集合元素类型
     * @return 原集合，若存在null则过滤掉null的集合
     * @since 1.0.0
     */
    public static <T> Stream<T> filterNullStream(final Collection<T> coll) {
        return emptyIfNull(coll).stream().filter(Objects::nonNull);
    }

    /**
     * 过滤集合里面的{@code null}, 返回一个空的{@link Stream}或者包含非空元素的流
     *
     * @param coll 提供的集合，可能为null
     * @param <T>  集合元素类型
     * @return 原集合，若存在null则过滤掉null的集合
     * @since 1.0.0
     */
    public static <T> List<T> filterNull(final Collection<T> coll) {
        return emptyIfNull(coll).stream().filter(Objects::nonNull).collect(toList());
    }

    /**
     * 判断集合是否为非空或者非null
     *
     * @param coll 集合
     * @return true:如果是非null或者非空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断集合是否为null或者为空
     *
     * @param coll 集合
     * @return true:如果是null或者空
     * @since 1.0.0
     */
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 返回第一个元素
     *
     * @param coll 集合，可能为空
     * @param <T>  元素类型
     * @return Option
     * @since 1.0.8
     */
    public static <T> Optional<T> findFirst(final Collection<T> coll) {
        if (isEmpty(coll)) {
            return Optional.empty();
        } else {
            return coll.stream().findFirst();
        }
    }

    /**
     * 返回最后一个元素
     *
     * @param coll 集合，可能为空
     * @param <T>  元素类型
     * @return Option
     * @since 1.0.8
     */
    public static <T> Optional<T> findLast(final Collection<T> coll) {
        if (isEmpty(coll)) {
            return Optional.empty();
        } else {
            final int size = coll.size();
            if (coll instanceof List) {
                return Optional.ofNullable(((List<T>) coll).get(size - 1));
            } else {
                Iterator<T> iterator = coll.iterator();
                for (int i = 0; iterator.hasNext() && (i < size - 1); i++) {
                    iterator.next();
                }
                return Optional.ofNullable(iterator.next());
            }
        }
    }

    /**
     * 判断集合coll1是否包含集合coll2中的任意一个，即只要两个集合有任意一个交集，返回true，否则返回false
     *
     * @param coll1 集合coll1
     * @param coll2 集合coll2
     * @param <T>   元素类型
     * @return true:如果集合coll1和集合coll2有任一交集
     * @since 1.0.0
     */
    @SafeVarargs
    public static <T> boolean containsAny(final Collection<?> coll1, final T... coll2) {
        if (coll1.size() < coll2.length) {
            for (final Object aColl1 : coll1) {
                if (contains(coll2, aColl1)) {
                    return true;
                }
            }
        } else {
            for (final Object aColl2 : coll2) {
                if (coll1.contains(aColl2)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean contains(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    static <T> int indexOf(final T[] array, final Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    @SuppressWarnings("SameParameterValue")
    static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }
}
