package com.lyp.learn.common;

import org.springframework.lang.NonNull;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List工具类
 *
 * @author duanweidong
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public final class ListUtil extends CollUtil {

    private ListUtil() {
    }

    /**
     * 创建一个空的，不可编辑的集合
     *
     * @param <T> 元素类型
     * @return 空集合
     * @since 1.0.0
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * 把一个{@link List} 切分为固定大小的子{@link List} {@code [a, b, c, d, e]} 每个子{@link List}为3个，则为{@code
     * [[a, b, c], [d, e]]}
     *
     * @param <T>  元素的类型
     * @param list 原{@link List}
     * @param size 每个子{@link List}切分的大小
     * @return 切分后的List的集合
     * @throws NullPointerException     如果list是null
     * @throws IllegalArgumentException 如果size不为正
     * @since 1.0.0
     */
    public static <T> List<List<T>> partition(final List<T> list, final int size) {
        if (list == null) {
            throw new IllegalArgumentException("List must not be null");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        return new Partition<>(list, size);
    }

    /**
     * 返回一个新的List,包含第一个List和第二个List的并集。
     *
     * @param <E>   元素类型
     * @param list1 第一个List
     * @param list2 第二个List
     * @return 返回一个新的List, 包含两个子List
     * @throws NullPointerException 如果任意一个List是null
     * @since 1.0.0
     */
    public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
        final ArrayList<E> result = new ArrayList<>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    /**
     * 创建一个可以修改的ArrayList.
     *
     * @param <E> 元素类型
     * @return 空List
     * @since 1.0.0
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 创建一个新的可以改变的ArrayList，并把elements中的元素加入到该ArrayList中
     *
     * @param elements 元素
     * @param <E>      类型
     * @return 新建的ArrayList
     * @since 1.0.0
     */
    public static <E> ArrayList<E> newArrayList(@NonNull Iterable<? extends E> elements) {
        // Let ArrayList's sizing logic work, if possible
        return (elements instanceof Collection)
                ? new ArrayList<>(cast(elements))
                : newArrayList(elements.iterator());
    }

    /**
     * 把迭代器中的元素加入到新建的ArrayList中
     *
     * @param elements 迭代器元素
     * @param <E>      类型
     * @return 新建的ArrayList
     * @since 1.0.0
     */
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        addAll(list, elements);
        return list;
    }

    /**
     * 根据可变长度参数创建ArrayList
     *
     * @param elements 可变长度参数
     * @param <E>      类型
     * @return 创建的ArrayList
     * @since 1.0.0
     */
    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(@NonNull E... elements) {
        // Avoid integer overflow when a large array is passed in
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * 返回不可编辑的List
     *
     * @param list list
     * @param <E>  元素类型
     * @since 1.0.7
     */
    public static <E> List<E> unmodifiableList(List<E> list) {
        return Collections.unmodifiableList(list);
    }

    /**
     * 创建指定大小的ArrayList
     *
     * @param initialArraySize 初始化大小
     * @return 返回一个初始化大小的ArrayList
     * @since 1.0.0
     */
    public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
        Assert.isTrue(initialArraySize > 0, "initialArraySize must >= 0");
        return new ArrayList<>(initialArraySize);
    }

    /**
     * 创建一个链表 LinkedList
     *
     * @param <E> 元素类型
     * @return LinkedList
     * @since 1.0.0
     */
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * 创建一个并发List CopyOnWriteArrayList
     * <b>CopyOnWrite系列适用于小数据量，多读少写的多线程场景!!!</b>
     *
     * @param <E> 元素类型
     * @return CopyOnWriteArrayList
     * @since 1.0.4
     */
    public static <E> List<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    static int computeArrayListCapacity(int arraySize) {
        Assert.isTrue(arraySize > 0, "arraySize must >= 0");
        // 参考guava
        return IntUtil.saturatedCast(5L + arraySize + (arraySize / 10));
    }

    private static class Partition<T> extends AbstractList<List<T>> {

        private final List<T> list;
        private final int size;

        private Partition(final List<T> list, final int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(final int index) {
            final int listSize = size();
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            }
            if (index >= listSize) {
                throw new IndexOutOfBoundsException("Index " + index + " must be less than size " +
                        listSize);
            }
            final int start = index * size;
            final int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }

        @Override
        public int size() {
            return (int) Math.ceil((double) list.size() / (double) size);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}