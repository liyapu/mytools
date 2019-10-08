package com.lyp.learn.guava.collect;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Doubles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: liyapu
 * @description:  http://www.ibloger.net/article/3300.html
 * @date 2019-10-08 14:06
 *
 * 排序器 Ordering 是 Guava流畅风格比较器 Comparator 的实现，它可以用来为构建复杂的比较器，以完成集合排序的功能。
 *
 * 从实现上说，Ordering 实例就是一个特殊的 Comparator 实例。
 * Ordering 把很多基于 Comparator 的静态方法（如 Collections.max）包装为自己的实例方法（非静态方法），
 * 并且提供了链式调用方法，来定制和增强现有的比较器。
 *
 * 由于java8 提供了类似的工能，guava官方推荐优先使用 jdk 提供的
 * {@link java.util.stream.Stream Stream} 和{@link java.util.Comparator} ，
 * 其它功能可以通过{@link java.util.Comparators} 类获取
 */
public class OrderingTest {

    /**
     * allEqual 允许有null,认为所有值都相等
     * 返回一个Ordering，所有值的排序地位都是平等的，表明无排序。
     * 将此排序传递给任何稳定排序算法都不会导致元素顺序发生变化。
     */
    @Test
    public void testAllEqual(){
        Ordering<Object> comparator = Ordering.allEqual();
        System.out.println(comparator.compare(null,null));
        System.out.println(comparator.compare(new Object(),new Object()));
        System.out.println(comparator.compare("aaa","bbb"));
        System.out.println(comparator.compare(3,5));
    }


    /**
     * natural 不能有null
     * 对可排序类型做自然排序，如数字按大小，日期按先后排序
     */
    @Test
    public void testNature(){
        Ordering<Integer> comparator = Ordering.natural();
        System.out.println(comparator.compare(3,3));
        System.out.println(comparator.compare(3,5));
        System.out.println(comparator.compare(5,3));
        System.out.println(comparator.compare(1,null));
//        System.out.println(comparator.compare(null,null));
//        System.out.println(comparator.compare(null,null));
    }

    /**
     * List集合 复杂排序示例
     */
    @Test
    public void testComplicatedOrderingExample() {
        Ordering<Iterable<Integer>> example = Ordering.<Integer>natural().nullsFirst().reverse().lexicographical().reverse().nullsLast();

        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList(1);
        List<Integer> list3 = Lists.newArrayList(1, 1);
        List<Integer> list4 = Lists.newArrayList(1, 2);
        List<Integer> list5 = Lists.newArrayList(1, null, 2);
        List<Integer> list6 = Lists.newArrayList(2);

        Integer nullInt = null;
        List<Integer> list7 = Lists.newArrayList(nullInt);
        List<Integer> list8 = Lists.newArrayList(nullInt, nullInt);
        List<List<Integer>> list = Lists.newArrayList(list1, list2, list3, list4, list5, list6, list7, list8, null);

        List<List<Integer>> sorted = example.sortedCopy(list);
        sorted.forEach(System.out::println);
    }

    /**
     * from 把给定的 Comparator 转化为排序器
     */
    @Test
    public void testFrom(){
        // String.CASE_INSENSITIVE_ORDER 按照 ASCII 排序
        Ordering<String> comparator = Ordering.from(String.CASE_INSENSITIVE_ORDER);
        System.out.println(comparator.compare("a","A"));
        System.out.println(comparator.compare("A","a"));
        System.out.println();
        System.out.println(comparator.compare("a","B"));
        System.out.println(comparator.compare("B","a"));
        System.out.println();

        ArrayList<String> list = Lists.newArrayList("tingfeng","ABCDEF", "abcdef", "ABCDEF", "rapido", "chengxumiao");
        List<String> sortedCopy = comparator.sortedCopy(list);
        sortedCopy.forEach(System.out::println);
    }

    /**
     * explicit（ExplicitOrdering）返回一个Ordering，根据它们的传入的顺序比较对象。只能比较参数列表中存在的对象
     * 返回一个Ordering，根据他们出现在给定的列表的顺序比较对象
     */
    @Test
    public void testExplicit(){
        Ordering<Integer> explicit1 = Ordering.explicit(3,5,10,9);
        System.out.println(explicit1.compare(3,3));
        System.out.println(explicit1.compare(3,5));
        System.out.println(explicit1.compare(5,3));
        System.out.println(explicit1.compare(3,10));
        System.out.println(explicit1.compare(3,9));

    }

    @Test
    public void testExplicit2(){
        // explicit:根据传入对象的顺序排序
        Double first = 0.1;
        Double[] second = {0.2, 0.3, 0.5};
        List<Double> numbers = Lists.asList(first, second);

        //排序比较器：根据原始的大小排序
        Ordering<Double> peopleOrdering = new Ordering<Double>() {
            @Override
            public int compare(Double left, Double right) {
                return Doubles.compare(left, right);
            }
        };
        peopleOrdering.reverse().explicit(numbers).sortedCopy(numbers).forEach(System.out::println);
    }

    /**
     * leastOf 有点类似截取集合前几位的概念
     */
    @Test
    public void testLeastOf(){
        List<Integer> result = Ordering.natural().leastOf(Arrays.asList(3, 8,9,0,4, 5, -1), 3);
        System.out.println(result);
    }

    @Test
    public void testMin(){
        Ordering<Comparable> natural = Ordering.natural();
        ArrayList<Integer> list = Lists.newArrayList(4, 7, 9, 22, 44, 66, 33, 5);
        System.out.println(natural.min(5,7));
        System.out.println(natural.min(list));
    }

}
