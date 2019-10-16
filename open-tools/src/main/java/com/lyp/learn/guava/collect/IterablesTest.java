package com.lyp.learn.guava.collect;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.*;
import static java.util.Arrays.asList;

/**
 * Iterables
 * 在可能的情况下，Guava提供的工具方法更偏向于接受Iterable而不是Collection类型。
 * 在Google，对于不存放在主存的集合——比如从数据库或其他数据中心收集的结果集，
 * 因为实际上还没有攫取全部数据，这类结果集都不能支持类似size()的操作 ——通常都不会用Collection类型来表示。
 *
 * 因此，很多你期望的支持所有集合的操作都在Iterables类中。
 * 大多数Iterables方法有一个在Iterators类中的对应版本，用来处理Iterator。
 *
 * Iterables使用FluentIterable类进行了补充，它包装了一个Iterable实例，并对许多操作提供了”fluent”（链式调用）语法。
 * 推荐更多的使用java8 Stream 方法实现
 *
 *
 */
public class IterablesTest {

    /**
     * 创建方式
     */
    @Test
    public void create() {
        //  Iterable  Iterator  不一样
        Iterator<Integer> iterator = Arrays.asList(0, 1).iterator();

        Iterable<String> iterable1 = Collections.emptySet();

        Iterable<Integer> iterable2 = () -> Arrays.asList(0, 1).iterator();

        Iterable<String> iterable3 = Sets.newHashSet("a", null, "b");

        Iterable<String> iterable4 = Collections.singleton("a");

        Iterable<String> iterable5 = Collections.singletonList("foo");
    }

    /**
     * getOnlyElement(Iterable<T> iterable) 返回iterable中包含的单个元素.
     * getOnlyElement(Iterable<? extends T> iterable, T defaultValue) 返回iterable中包含的单个元素，如果iterable为空，则返回defaultValue.
     */
    @Test
    public void getOnlyElement() {
        Iterable<String> iterable = Collections.emptySet();
        //java.util.NoSuchElementException
//        System.out.println(Iterables.getOnlyElement(iterable));
        System.out.println(Iterables.getOnlyElement(iterable, "bar"));

        Iterable<String> iterable2 = Collections.singleton("a");
        System.out.println(Iterables.getOnlyElement(iterable2));

//        java.lang.IllegalArgumentException: expected one element but was: <a, b>
//        Iterable<String> iterable3 = Sets.newHashSet("a", "b");
//        System.out.println(Iterables.getOnlyElement(iterable3));

    }

    /**
     * any(Iterable<T> iterable, Predicate<? super T> predicate)
     * 如果iterable中的任何元素满足 predicate，则返回true.
     *
     * all(Iterable<T> iterable, Predicate<? super T> predicate)
     * 如果iterable中的每个元素都满足 predicate，则返回true。.
     *
     * find(Iterable<T> iterable, Predicate<? super T> predicate)
     * 返回iterable中满足给定predicate的第一个元素; 仅在已知存在此类元素时才使用此方法.
     *
     * find(Iterable<? extends T> iterable, Predicate<? super T> predicate, T defaultValue)
     * 返回满足给定predicate的iterable中的第一个元素，如果没有找到则返回defaultValue.
     *
     * tryFind(Iterable<T> iterable, Predicate<? super T> predicate)
     * 返回一个Optional，其中包含iterable中满足给定predicate的第一个元素（如果存在这样的元素）。
     */
    @Test
    public void and_all_find_tryFind() {
        List<String> list = Lists.newArrayList("cool", "pants");
        Predicate<String> predicate = Predicates.equalTo("pants");

        System.out.println(Iterables.any(list, predicate));     // true
        System.out.println(Iterables.all(list, predicate));     // false

        System.out.println(Iterables.find(list, predicate));    // pants
        System.out.println(Iterables.find(list, Predicates.equalTo("aaa"), "bbb"));  // bbb
        System.out.println(Iterables.find(list, Predicates.alwaysFalse(), "bbb"));         // bbb
        System.out.println(Iterables.find(list, Predicates.alwaysTrue(), "bbb"));          // cool

        System.out.println(Iterables.tryFind(list, predicate));                         // Optional.of(pants)
        System.out.println(Iterables.tryFind(list, Predicates.equalTo("aaa")));  // Optional.absent()
        System.out.println(Iterables.tryFind(list, Predicates.alwaysFalse()));          // Optional.absent()
        System.out.println(Iterables.tryFind(list, Predicates.alwaysTrue()));           // Optional.of(cool)
    }

    /**
     * 	filter(Iterable<T> unfiltered, Predicate<? super T> retainIfTrue)
     * 返回未过滤的视图，其中包含满足输入predicate 的所有元
     *
     * filter(Iterable<?> unfiltered, Class<T> desiredType)
     * 返回未过滤的视图，其中包含所有类型为desiredType的元素.
     */
    @Test
    public void filter() {
        List<Object> list = Lists.newArrayList("A", "B", "C", "A", null, 1, null, 2);
        Iterable<Object> iterable = Iterables.filter(list, Predicates.equalTo("A"));
        System.out.println(iterable);   // [A, A]

        Iterable<Integer> filter = Iterables.filter(list, Integer.class);
        System.out.println(filter);     // [1, 2]

        System.out.println(Iterables.filter(list, Predicates.notNull()));  // [A, B, C, A, 1, 2]
    }

    /**
     * toArray(Iterable<? extends T> iterable, Class<T> type)
     * 将iterable的元素复制到数组中.
     */
    @Test
    public void toArray() {
        Iterable<String> iterable = Collections.singletonList("a");
        String[] array = Iterables.toArray(iterable, String.class);
        System.out.println(Arrays.toString(array));         // [a]
    }

    /**
     * transform(Iterable<F> fromIterable, Function<? super F,? extends T> function)
     * 返回一个视图，其中包含将函数应用于fromIterable的每个元素的结果.
     */
    @Test
    public void transform() {
        List<String> input = asList("1", "2", "3");
        Iterable<Integer> result = Iterables.transform(input, from -> Integer.valueOf(from));

        List<Integer> actual = Lists.newArrayList(result);
        System.out.println(actual);     // [1, 2, 3]

        List<String> input2 = asList("a", "b", "c");
        Iterable<String> result2 = Iterables.transform(input2, from -> from.toUpperCase());
        System.out.println(result2);    // [A, B, C]
    }

    /**
     * cycle(Iterable<T> iterable)
     * 返回一个 Iterable，迭代器在iterable元素上无限循环.
     *
     * cycle(T... elements)
     * 返回一个迭代器，其迭代器在提供的元素上无限循环.
     */
    @Test
    public void cycle() {
        // 循环迭代
        Iterable<String> cycle = Iterables.cycle("a", "b", "c");
        System.out.println(cycle);  // [a, b, c] (cycled)

        int i = 0;
        for (String str : cycle) {
            i++;
            if (i > 20) {
                break;
            }
            System.out.println(str);
        }
    }


    /**
     * concat(Iterable<? extends Iterable<? extends T>> inputs)
     * 将多个iterables组合成一个iterables.
     *
     * concat(Iterable<? extends T>... inputs)
     * 将多个iterables组合成一个iterables.
     *
     * concat(Iterable<? extends T> a, Iterable<? extends T> b)
     * 将两个iterables组合成一个iterable.
     *
     * concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T>c)
     * 将三个个iterables组合成一个iterable..
     *
     * concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T>c, Iterable<? extends T> d)
     * 将四个iterables组合成一个iterable..
     */
    @Test
    public void concat() {
        List<Integer> list1 = Lists.newArrayList(1);
        List<Integer> list2 = Lists.newArrayList(4);

        List<List<Integer>> input = Lists.newArrayList(list1, list2);
        Iterable<Integer> result = Iterables.concat(input);
        System.out.println(result);     // [1, 4]

        List<Integer> list3 = Lists.newArrayList(7, 8);
        List<Integer> list4 = Lists.newArrayList(9);
        List<Integer> list5 = Lists.newArrayList(10);
        result = Iterables.concat(list1, list2, list3, list4, list5);
        System.out.println(result);     // [1, 4, 7, 8, 9, 10]
    }

    @Test
    public void concat2() {
        Iterable<Integer> iterable = asList(1, 2, 3);
        int n = 4;
        Iterable<Integer> repeated = Iterables.concat(Collections.nCopies(n, iterable));
        System.out.println(repeated);   // [1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3]
    }

    /**
     * partition(Iterable<T> iterable, int size)
     * 将iterable划分为给定大小的不可修改的子列表（最终的可迭代可能更小）.
     */
    @Test
    public void partition() {
        Iterable<Integer> source = Collections.emptySet();
        Iterable<List<Integer>> partitions = Iterables.partition(source, 1);
        System.out.println(partitions);                         // []
        System.out.println(Iterables.isEmpty(partitions));      // true

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        partitions = Iterables.partition(list, 2);
        System.out.println(partitions);                         // [[1, 2], [3, 4], [5]]
        System.out.println(Iterables.size(partitions));         // 3
    }

    /**
     * paddedPartition(Iterable<T> iterable, int size)
     * 将iterable划分为给定大小的不可修改的子列表，如果需要，使用空值填充最终的iterable.
     */
    @Test
    public void paddedPartition() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Iterable<List<Integer>> partitions = Iterables.paddedPartition(list, 2);
        System.out.println(partitions);                         // [[1, 2], [3, 4], [5, null]]
        System.out.println(Iterables.getLast(partitions));      // [5, null]
        System.out.println(Iterables.size(partitions));         // 3

    }

    /**
     * addAll(Collection<T> addTo, Iterable<? extends T> elementsToAdd)
     * 将iterable中的所有元素添加到集合中。
     */
    @Test
    public void addAll() {
        List<String> alreadyThere = Lists.newArrayList("already", "there");
        List<String> freshlyAdded = Lists.newArrayList("freshly", "added");

        boolean changed = Iterables.addAll(alreadyThere, freshlyAdded);
        System.out.println(changed);        // true
        System.out.println(alreadyThere);   // [already, there, freshly, added]
        System.out.println(freshlyAdded);   // [freshly, added]
    }

    /**
     * elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2)
     * 确定两个迭代是否包含相同顺序的相等元素.
     */
    @Test
    public void elementsEqual(){
        Iterable<?> a;
        Iterable<?> b;

        // A few elements.
        a = asList(4, 8, 15, 16, 23, 42);
        b = asList(4, 8, 15, 16, 23, 42);
        System.out.println(Iterables.elementsEqual(a, b));  // true

        // An element differs.
        a = asList(4, 8, 15, 12, 23, 42);
        b = asList(4, 8, 15, 16, 23, 42);
        System.out.println(Iterables.elementsEqual(a, b));  // false

        // null versus non-null.
        a = asList(4, 8, 15, null, 23, 42);
        b = asList(4, 8, 15, 16, 23, 42);
        System.out.println(Iterables.elementsEqual(a, b));  // false
        System.out.println(Iterables.elementsEqual(b, a));  // false

        // Different lengths.
        a = asList(4, 8, 15, 16, 23);
        b = asList(4, 8, 15, 16, 23, 42);
        System.out.println(Iterables.elementsEqual(a, b));  // false
        System.out.println(Iterables.elementsEqual(a, b));  // false
    }

    /**
     * limit(Iterable<T> iterable, int limitSize)
     * 返回包含其第一个limitSize元素的iterable视图.
     */
    @Test
    public void limit() {
        Iterable<String> iterable = Lists.newArrayList("foo", "bar", "baz");
        Iterable<String> limited = Iterables.limit(iterable, 2);
        System.out.println(limited);        // [foo, bar]
    }

    /**
     * skip(Iterable<T> iterable, int numberToSkip)
     * 返回跳过其第一个numberToSkip元素的iterable视图.
     */
    @Test
    public void skip() {
        Collection<String> set = ImmutableSet.of("a", "b", "c", "d", "e");
        System.out.println(Iterables.skip(set, 2));     // [c, d, e]
        System.out.println(Iterables.skip(set, 20));
    }

    /**
     * get(Iterable<T> iterable, int position)
     * 返回指定位置的元素.
     *
     * get(Iterable<? extends T> iterable, int position, T defaultValue)
     * 返回指定位置的元素，没有使用默认值.
     */
    @Test
    public void get() {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");
        System.out.println(Iterables.get(list, 1, "d"));    // b
        System.out.println(Iterables.get(list, 5, "d"));    // d
    }

    /**
     * getFirst(Iterable<? extends T> iterable, T defaultValue)
     * 返回 iterable 的第一个元素，没有使用默认值.
     *
     * getLast(Iterable<? extends T> iterable, T defaultValue)
     * 返回 iterable 的最后一个元素，没有使用默认值.
     *
     * getLast(Iterable<T> iterable)
     * 返回iterable的最后一个元素.
     */
    @Test
    public void getFirst_getLast() {
        Iterable<String> iterable = Collections.emptyList();
        System.out.println(Iterables.getFirst(iterable,null));      // null
        System.out.println(Iterables.getFirst(iterable, "bar"));    // bar
        System.out.println();

        iterable = asList("foo", "bar");
        System.out.println(Iterables.getFirst(iterable,null));      // foo
        System.out.println(Iterables.getLast(iterable));                        // bar
        System.out.println(Iterables.getLast(iterable,"xxx"));      // bar
    }

    /**
     * unmodifiableIterable(Iterable<? extends T> iterable)
     * 返回不可修改视图.
     */
    @Test
    public void unmodifiableIterable() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        Iterable<String> iterable = Iterables.unmodifiableIterable(list);
        Iterable<String> iterable2 = Iterables.unmodifiableIterable(iterable);

        System.out.println(iterable);       // [a, b, c]
        System.out.println(iterable2);      // [a, b, c]
    }

    /**
     * frequency(Iterable<?> iterable, @Nullable Object element)
     * 返回指定iterable中与指定对象相等的元素数.
     */
    @Test
    public void frequency() {
        Multiset<String> multiset = ImmutableMultiset.of("a", "b", "a", "c", "b", "a");
        Set<String> set = Sets.newHashSet("a", "b", "c");
        List<String> list = Lists.newArrayList("a", "b", "a", "c", "b", "a");

        System.out.println(Iterables.frequency(multiset, "a"));     // 3
        System.out.println(Iterables.frequency(multiset, "b"));     // 2
        System.out.println(Iterables.frequency(multiset, "d"));     // 0
        System.out.println(Iterables.frequency(multiset, 1.2));     // 0
        System.out.println(Iterables.frequency(multiset, null));    // 0
    }


    /**
     * removeAll(Iterable<?> removeFrom, Collection<?> elementsToRemove)
     * 删除属于提供的集合的每个元素
     */
    @Test
    public void removeAll() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "e");

        System.out.println(Iterables.removeAll(list, Lists.newArrayList("b", "d", "f")));     // true
        System.out.println(list);       // [a, c, e]

        System.out.println();
        System.out.println(Iterables.removeAll(list, Lists.newArrayList("x", "y", "z")));     // false
        System.out.println(list);       // [a, c, e]
    }

    /**
     * retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain)
     * 删除不属于提供的集合的每个元素.
     */
    @Test
    public void retainAll() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "e");
        System.out.println(Iterables.retainAll(list, Lists.newArrayList("b", "d", "f")));     // true
        System.out.println(list);       // [b, d]
        System.out.println(Iterables.retainAll(list, Lists.newArrayList("b", "e", "d")));     // false
        System.out.println(list);       // [b, d]
    }

    /**
     * removeIf(Iterable<T> removeFrom, Predicate<? super T> predicate)
     * 删除满足提供的predicate的每个元素.
     */
    @Test
    public void removeIf() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "e");

        boolean removeIf = Iterables.removeIf(list, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return input.equals("b") || input.equals("s");
            }
        });

        System.out.println(removeIf);   // true
    }
}
