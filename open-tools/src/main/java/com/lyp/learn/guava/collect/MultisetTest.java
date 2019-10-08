package com.lyp.learn.guava.collect;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-08 19:15
 *
 *  把重复的元素放入集合
 *
 *  在JDK中，List 和 Set 有一个基本的区别，就是 List 可以包含多个相同对象，且是有顺序的，而 Set 不能有重复，且不保证顺序（有些实现有顺序，例如 LinkedHashSet 和 SortedSet等）
 *  所以 Multiset 占据了 List 和 Set 之间的一个灰色地带：允许重复，但是不保证顺序。
 *  事实上，Multiset 并没有实现 java.util.Set 接口，它更像是一个 Bag。
 *  普通的 Set 就像这样：[car, ship, bike]，而 Multiset 会是这样：[car x 2, ship x 6, bike x 3]
 *
 *
 * 常用的实现了 Multiset 接口的类有：
 * HashMultiset：元素存放于 HashMap
 * LinkedHashMultiset：元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定
 * TreeMultiset：元素被排序存放于TreeMap
 * EnumMultiset：元素必须是 enum 类型
 * ImmutableMultiset：不可修改的 Mutiset
 *
 *
 * Multiset 的各种实现
 * Guava 提供了多种 Multiset 的实现，大致对应 JDK 中 Map 的各种实现：
 *
 * Map	               对应的 Multiset	    是否支持null元素
 * HashMap	            HashMultiset	       是
 * TreeMap	            TreeMultiset	       是（如果comparator支持的话）
 * LinkedHashMap	    LinkedHashMultiset	   是
 * ConcurrentHashMap	ConcurrentHashMultiset	否
 * ImmutableMap	        ImmutableMultiset	    否
 *
 */
public class MultisetTest {
    public static  List<String> words = Arrays.asList("you","me","like","i","apple","me","like","me","you");

    /**
     * count(Object element)
     * 得到某个对象在 Multiset 中出现的次数
     * 跟踪每种对象的数量，所以你可以用来进行数字统计
     *
     * 注意： Multiset 提供 setCount(E, int)方法，可以修改元素 E 在 Multiset 中的次数，
     * 但是不能把元素出现的次数修改为负数和大于 Integer.MAX_VALUE 的值。否则将会抛出异常。
     */

    //java jdk 的写法
    @Test
    public void testCountOfJDK(){
        Map<String,Integer> wordsMap = new HashMap<>();
        for(String word : words){
            Integer count = wordsMap.get(word);
            if(count == null){
                wordsMap.put(word,1);
            }else{
                wordsMap.put(word,count+1);
            }
        }

        System.out.println(wordsMap);
        System.out.println(wordsMap.get("me"));
    }

    @Test
    public void testCount(){
        Multiset multiset = HashMultiset.create();
        for(String word : words){
            multiset.add(word);
        }
        System.out.println(multiset);
        System.out.println(multiset.count("me"));
    }

    /**
     * count(elem) 方法中的 elem 如果没有出现在 Multiset 中，那么它的返回值永远都是0。
     */
    @Test
    public void testCount1(){
        Multiset multiset = HashMultiset.create(words);
        System.out.println(multiset);
        System.out.println(multiset.count("me"));

        System.out.println(multiset.count("none"));
    }


    /**
     * multiset.size() 返回这个集合的大小，相当于在 multiset 中元素的出现的总数。
     * 如果想得到 multiset 中不同元素出现的总数，可以利用 elementSet().size() 来实现；
     */
    @Test
    public void testSize(){
        Multiset multiset = HashMultiset.create(words);
        System.out.println(multiset);
        System.out.println(multiset.size());

        System.out.println();
        multiset.setCount("apple",5);
        System.out.println(multiset.size());

        System.out.println();
        System.out.println(multiset.elementSet().size());
    }

    /**
     * iterator() 可以遍历 multiset 中的所有元素，所以 iteration 遍历的次数就等于 multiset.size()
     */
    @Test
    public void testIterator(){
        Multiset<String> multiset = HashMultiset.create(words);
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()){
            String nextValue = iterator.next();
            System.out.println(nextValue);
        }

        System.out.println("-----------");
        multiset.setCount("i",5);
        Iterator<String> iterator2 = multiset.iterator();
        while (iterator2.hasNext()){
            String nextValue2 = iterator2.next();
            System.out.println(nextValue2);
        }
    }

    /**
     * Multiset 支持添加、删除元素，设置元素出现的次数 setCount(elem, 0) 相当于移除elem的所有元素；
     */
    @Test
    public void testOperator(){
        Multiset<String> multiset = HashMultiset.create(words);
        System.out.println(multiset);

        System.out.println();
        multiset.add("red");
        multiset.add("green");
        multiset.add("red");
        System.out.println(multiset);

        System.out.println();
        multiset.setCount("apple",5);
        multiset.setCount("you",0);
        System.out.println(multiset);
    }

}
