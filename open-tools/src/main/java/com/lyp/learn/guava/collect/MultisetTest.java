package com.lyp.learn.guava.collect;

import com.google.common.collect.*;
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
 *  可以用两种方式看待Multiset：
 *  ·没有元素顺序限制的ArrayList<E>
 *  ·Map<E, Integer>，键为元素，值为计数
 *
 * Multiset不是Map
 * 请注意，Multiset<E>不是Map<E, Integer>，虽然Map可能是某些Multiset实现的一部分。准确来说Multiset是一种Collection类型，并履行了Collection接口相关的契约。
 * 关于Multiset和Map的显著区别还包括：
 * 1、Multiset中的元素计数只能是正数。任何元素的计数都不能为负，也不能是0。elementSet()和entrySet()视图中也不会有这样的元素。
 * 2、multiset.size()返回集合的大小，等同于所有元素计数的总和。对于不重复元素的个数，应使用elementSet().size()方法。（因此，add(E)把multiset.size()增加1）
 * 3、multiset.iterator()会迭代重复元素，因此迭代长度等于multiset.size()。
 * 4、Multiset支持直接增加、减少或设置元素的计数。setCount(elem, 0)等同于移除所有elem。
 * 5、对multiset 中没有的元素，multiset.count(elem)始终返回0。
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

    /**
     * boolean	add(E element) 向其中添加单个元素.
     * int	add(E element, int occurrences) 增加给定元素在Multiset中的计数
     */
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
     * 遍历
     * 返回所有不重复元素的Set<E>，和Map的keySet()类似
     */
    @Test
    public void testElementSet(){
        Multiset<String> multiset = HashMultiset.create(words);
        Set<String> stringSet = multiset.elementSet();
        for(String str : stringSet){
//            System.out.println(str);
            System.out.println(str + " : " + multiset.count(str));
        }
    }

    /**
     * entrySet()
     * 和Map的entrySet类似，返回Set<multiset.entry>，其中包含的Entry支持getElement()和getCount()方法
     */
    @Test
    public void testEntrySet(){
        Multiset<String> multiset = HashMultiset.create(words);
        Set<Multiset.Entry<String>> entries = multiset.entrySet();
        for(Multiset.Entry<String> entry : entries){
            System.out.println(entry.getElement() + ":" + entry.getCount());
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

    /**
     * boolean	remove(@Nullable Object element) 移除一个元素，其count值 会响应减少.
     * int	remove(@Nullable Object element, int occurrences) 减少给定元素在Multiset中的计数
     * boolean	removeAll(Collection<?> c) 去除出现给给定集合参数的所有的元素.
     */
    @Test
    public void testRemove(){
        Multiset<String> multiset = HashMultiset.create(words);
        System.out.println(multiset);

        System.out.println();
        multiset.remove("i");
        System.out.println(multiset);

        System.out.println();
        multiset.remove("like");
        System.out.println(multiset);

        System.out.println();
        multiset.remove("you",2);
        System.out.println(multiset);

        System.out.println();
        multiset.remove("me",10);
        System.out.println(multiset);

    }

    @Test
    public void testRemoveAll(){
        Multiset<String> multiset = HashMultiset.create(words);
        System.out.println(multiset);

        System.out.println();
        List<String> strList = Arrays.asList("i","like","you");

        multiset.removeAll(strList);
        System.out.println(multiset);
    }

    @Test
    public void testContains(){
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        System.out.println(multiset1.contains("a"));
        System.out.println(multiset1.contains("b"));
        System.out.println(multiset1.containsAll(multiset2));
        System.out.println();

        System.out.println(multiset2.containsAll(multiset1));
        System.out.println();

        System.out.println(Multisets.containsOccurrences(multiset1, multiset2));
        System.out.println(Multisets.containsOccurrences(multiset2, multiset1));

    }

    /**
     * copyHighestCountFirst(Multiset)
     * 返回Multiset的不可变拷贝，并将元素按重复出现的次数做降序排列
     */
    @Test
    public void testCopyHighestCountFirst(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a", 3);
        multiset.add("b", 5);
        multiset.add("c", 1);

        ImmutableMultiset highestCountFirst = Multisets.copyHighestCountFirst(multiset);
        System.out.println(highestCountFirst);
        //highestCountFirst，包括它的entrySet和elementSet，按{"b", "a", "c"}排列元素
    }
    /**
     * SortedMultiset是Multiset的变体，增加了针对元素次数的排序功能，接口实现类为TreeMultiset
     * 不过这个SortedMultiset是针对元素进行排序的，而不是元素次数
     */
    @Test
    public void testSortedMultiset(){
        SortedMultiset<Integer> sortedMultiset = TreeMultiset.create();
        sortedMultiset.add(2,50);
        sortedMultiset.add(3,20);
        sortedMultiset.add(4,10);
        sortedMultiset.add(1,30);

        System.out.println(sortedMultiset);
        SortedMultiset<Integer> descendingMultiset = sortedMultiset.descendingMultiset();
        System.out.println(descendingMultiset);
        System.out.println();

        System.out.println(sortedMultiset.firstEntry().getElement());
        SortedMultiset<Integer> subMultiset = sortedMultiset.subMultiset(2, BoundType.CLOSED, 3, BoundType.CLOSED);
        System.out.println(subMultiset);
    }

}
