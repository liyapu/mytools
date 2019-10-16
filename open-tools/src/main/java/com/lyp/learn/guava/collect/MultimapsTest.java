package com.lyp.learn.guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * 提供作用于或生成 Multimap 的静态方法。
 */
public class MultimapsTest {

    /**
     * index
     * 作为Maps.uniqueIndex的兄弟方法，Multimaps.index(Iterable, Function)通常针对的场景是：
     * 有一组对象，它们有共同的特定属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
     *
     * 比方说，我们想把字符串按长度分组。
     */
    @Test
    public void testIndex(){
        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };

        ImmutableListMultimap<Integer, String> digitsByLength= Multimaps.index(digits, lengthFunction);
        System.out.println(digitsByLength.get(3));
        System.out.println();

        System.out.println(digitsByLength);
    }

    /**
     * 鉴于Multimap可以把多个键映射到同一个值（译者注：实际上这是任何map都有的特性），也可以把一个键映射到多个值，
     * 反转Multimap也会很有用。Guava 提供了invertFrom(Multimap toInvert,Multimap dest)做这个操作，并且你可以自由选择反转后的Multimap实现。
     *
     * 注：如果你使用的是ImmutableMultimap，考虑改用ImmutableMultimap.inverse()做反转。
     */
    @Test
    public void testInvertFrom(){
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));

        System.out.println(multimap);
        System.out.println();

        //注意我们选择的实现，因为选了TreeMultimap，得到的反转结果是有序的
        TreeMultimap<Integer, String> integerStringTreeMultimap = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(integerStringTreeMultimap);

        System.out.println();
        LinkedListMultimap<Integer, String> integerStringLinkedListMultimap = Multimaps.invertFrom(multimap, LinkedListMultimap.create());
        System.out.println(integerStringLinkedListMultimap);

        System.out.println();
        HashMultimap<Integer, String> integerStringHashMultimap = Multimaps.invertFrom(multimap, HashMultimap.create());
        System.out.println(integerStringHashMultimap);
    }


    /**
     * forMap
     * 想在Map对象上使用Multimap的方法吗？
     * forMap(Map)把Map包装成SetMultimap。这个方法特别有用，
     *
     * 例如，与Multimaps.invertFrom结合使用，可以把多对一的Map反转为一对多的Multimap。
     */
    @Test
    public void testForMap(){
        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        System.out.println(map);

        SetMultimap<String, Integer> multimap = Multimaps.forMap(map);
        System.out.println(multimap);

        Multimap<Integer, String> inverse = Multimaps.invertFrom(multimap, HashMultimap.create());
        System.out.println(inverse);
    }

    @Test
    public void test111() {
        ListMultimap<String, String> listMultimap = LinkedListMultimap.create();
        listMultimap.put("foo", "A");
        listMultimap.put("foo", "B");
        listMultimap.put("foo", "C");

        // 遍历
        listMultimap.forEach((k, v) -> System.out.println("k：" + k + ", v：" + v));

        System.out.println(listMultimap);
    }
}
