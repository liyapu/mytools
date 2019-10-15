package com.lyp.learn.guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.lyp.learn.guava.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 *  不可变集合
 *  Guava提供了对JDK里标准集合类里的immutable版本的简单方便的实现，以及Guava自己的一些专门集合类的immutable实现。
 *  当你不希望修改一个集合类,或者想做一个常量集合类的时候，使用immutable集合类就是一个最佳的编程实践。
 *
 *  使用示例：
 *      示例1：
 *      public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red","yellow","green","purple");
 *
 *      示例2：
 *          public class Foo {
 *              Set<Bar> bars;
 *
 *              Foo(Set<Bar> bars) {
 *                  this.bars = ImmutableSet.copyOf(bars); // defensive copy! 防御性复制
 *              }
 *           }
 *
 * 为什么要使用不可变集合 ？
 *   不可变对象有很多优点，包括：
 *   1、当对象被不可信的库调用时，不可变形式是安全的；
 *   2、不可变对象被多个线程调用时，不存在竞态条件问题
 *   3、不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 *   4、不可变对象因为有固定不变，可以作为常量来安全使用
 *
 *  创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本。
 *
 *  JDK也提供了Collections.unmodifiableXXX方法把集合包装为不可变形式，但我们认为不够好：
 *   1、笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；
 *   2、不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；
 *   当Collections创建的不可变集合的wrapper类改变的时候，不可变集合也会改变，而Guava的Immutable集合保证确实是不可变的。
 *   3、低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。
 *
 * 如果你没有修改某个集合的需求，或者希望某个集合保持不变时，把它防御性地拷贝到不可变集合是个很好的实践。
 *
 * 重要提示：所有Guava不可变集合的实现都不接受null值。
 * 我们对Google内部的代码库做过详细研究，发现只有5%的情况需要在集合中允许null元素，剩下的95%场景都是遇到null值就快速失败。
 * 如果你需要在不可变集合中使用null，请使用JDK中的Collections.unmodifiableXXX方法。
 *
 *
 * 细节：关联可变集合和不可变集合
 *     可变集合接口	        属于JDK还是Guava	                不可变版本
 *      Collection	            JDK	                    ImmutableCollection
 *      List	                JDK	                        ImmutableList
 *      Set	                    JDK	                        ImmutableSet
 *      SortedSet/NavigableSet	JDK	                       ImmutableSortedSet
 *      Map	                    JDK	                        ImmutableMap
 *      SortedMap	            JDK	                        ImmutableSortedMap
 *
 *      Multiset	           Guava	                    ImmutableMultiset
 *      SortedMultiset         Guava	                    ImmutableSortedMultiset
 *      Multimap	           Guava	                    ImmutableMultimap
 *      ListMultimap	       Guava	                    ImmutableListMultimap
 *      SetMultimap	           Guava	                    ImmutableSetMultimap
 *      BiMap	               Guava	                    ImmutableBiMap
 *      ClassToInstanceMap     Guava	                    ImmutableClassToInstanceMap
 *      Table	               Guava	                    ImmutableTable
 */
public class ImmutableTest {

    /**
     * 不可变集合可以用如下多种方式创建：
     */
    @Test
    public void testCreateImmutable(){
        //1、of方法
        ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");
        System.out.println(immutableSet1);

        //2、copyOf方法
        ImmutableSet<String> immutableSet2 = ImmutableSet.copyOf(immutableSet1);
        System.out.println(immutableSet2);

        //3、Builder工具
        List<Person>  personList = Arrays.asList(new Person("张三",23),new Person("李四",14));
        ImmutableSet<Person> immutableSet3 = ImmutableSet.<Person>builder()
                                            .addAll(personList)
                                            .add(new Person("王五",15))
                                            .build();
        System.out.println(immutableSet3);

    }

    /**
     * 不可变集合，不可修改
     */
    @Test
    public void testImmutableList(){
        ImmutableList<String> immutableList = ImmutableList.of("a","b","c","d");
        System.out.println(immutableList);

        //java.lang.UnsupportedOperationException
        //immutableList.add("aa");
    }

    @Test
    public void testImmutableMap(){
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("k1", "v1", "k2", "v2");
        System.out.println(immutableMap);
    }

    /**
     * 对有序不可变集合来说，排序是在构造集合的时候完成的
     * 会在构造时就把元素排序为a, b, c, d
     */
    @Test
    public void testImmutableSortedSet(){
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println(immutableSortedSet);
    }

    /**
     * 比想象中更智能的copyOf
     * 请注意，ImmutableXXX.copyOf方法会尝试在安全的时候避免做拷贝——实际的实现细节不详，但通常来说是很智能的，比如：
     *
     * 在这段代码中，ImmutableList.copyOf(foobar)会智能地直接返回foobar.asList(),它是一个ImmutableSet的常量时间复杂度的List视图。
     * 作为一种探索，ImmutableXXX.copyOf(ImmutableCollection)会试图对如下情况避免线性时间拷贝：
     *  1、在常量时间内使用底层数据结构是可能的——例如，ImmutableSet.copyOf(ImmutableList)就不能在常量时间内完成。
     *  2、不会造成内存泄露——例如，你有个很大的不可变集合ImmutableList<String> hugeList， ImmutableList.copyOf(hugeList.subList(0, 10))就会显式地拷贝，以免不必要地持有hugeList的引用。
     *  3、不改变语义——所以ImmutableSet.copyOf(myImmutableSortedSet)会显式地拷贝，因为和基于比较器的ImmutableSortedSet相比，ImmutableSet对hashCode()和equals有不同语义。
     * 在可能的情况下避免线性拷贝，可以最大限度地减少防御性编程风格所带来的性能开销。
     *
     */
    @Test
    public void testCopyOf(){
        ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");
        thingamajig(foobar);
    }
    void thingamajig(Collection<String> collection) {
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(collection);
        System.out.println(defensiveCopy);
    }

    /**
     * asList视图
     * 所有不可变集合都有一个asList()方法提供ImmutableList视图，来帮助你用列表形式方便地读取集合元素。
     * 例如，你可以使用sortedSet.asList().get(k)从ImmutableSortedSet中读取第k个最小元素。
     *
     * asList()返回的ImmutableList通常是——并不总是——开销稳定的视图实现，而不是简单地把元素拷贝进List。
     * 也就是说，asList返回的列表视图通常比一般的列表平均性能更好，比如，在底层集合支持的情况下，它总是使用高效的contains方法。
     */
    @Test
    public void testAsList(){
        // 无序的
        ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");
        System.out.println(immutableSet1);
        String str = immutableSet1.asList().get(1);
        System.out.println(str);

        //有序的
        System.out.println();
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println(immutableSortedSet);
        String str2 = immutableSortedSet.asList().get(2);
        System.out.println(str2);

    }

    /**
     * Guava中不可变对象和Collections工具类的unmodifiableSet/List/Map/etc的区别：
     * 当Collections创建的不可变集合的wrapper类改变的时候，不可变集合也会改变，而Guava的Immutable集合保证确实是不可变的。
     */
    @Test
    public void testCollectionsWrapper(){
        //错误初始化 Arrays.asList(),asList返回的是Arrays的内部类ArrayList,没有实现 add操作
        //执行 strList.add("ddd"); 会抛出java.lang.UnsupportedOperationException 异常
//        List<String> strList = Arrays.asList("a","b","c");
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");

        List<String> unmodifiableList = Collections.unmodifiableList(strList);
        System.out.println(unmodifiableList);

        //不可变集合，不支持添加操作 java.lang.UnsupportedOperationException
        //unmodifiableList.add("d");

        //原集合修改，不可变集合也跟着修改了
        System.out.println();
        strList.add("dddd");
        System.out.println(strList);
        System.out.println(unmodifiableList);
        /*
        * 通过运行结果我们可以看出：虽然unmodifiableList不可以直接添加元素，
        * 但是我的list是可以添加元素的，而list的改变也会使unmodifiableList改变。
        * 所以说Collections.unmodifiableList实现的不是真正的不可变集合。
        */
    }

    @Test
    public void testGuavaImmutable(){
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");

        ImmutableList<String> immutableList = ImmutableList.copyOf(strList);
        System.out.println(immutableList);;


        System.out.println();
        strList.add("dddd");
        System.out.println(strList);
        System.out.println(immutableList);

    }
}
