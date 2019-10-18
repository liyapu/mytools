package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 13:44
 *
 * Bag 包，袋子
 *
 * Bag继承自Collection接口，定义了一个集合，该集合会记录对象在集合中出现的次数。
 *
 * 场景：比如我们需要具体知道每个元素出现的次数的时候，并且实现快速去重，使用Bag会非常便捷
 */
public class BagTest {

    /**
     * 假设你有一个包，包含{a, a, b, c}。
     * 调用getCount(a)方法将返回2，
     * 调用uniqueset()方法将返回{a, b, c}的set集合。
     *
     * HashBag使用HashMap作为数据存储，是一个标准的Bag实现。
     */
    @Test
    public void testHashBag(){
        Bag<String> bag = new HashBag<>();
        bag.add("a");
        bag.add("a");
        bag.add("b");
        bag.add("c");
        //一次性放置多个元素
        bag.add("d", 3);

        System.out.println(bag);
        System.out.println();

        int aTimes = bag.getCount("a");
        System.out.println(aTimes);
        System.out.println(bag.getCount("b"));
        System.out.println(bag.getCount("c"));
        System.out.println(bag.getCount("d"));

        System.out.println();
        System.out.println(bag.size());
        System.out.println(bag.uniqueSet().size());

        System.out.println();
        Set<String> uniqueSet = bag.uniqueSet();
        uniqueSet.stream()
                .forEach(System.out::println);
    }

    /**
     * remove(Object object) 移除所有出现的object对象，不管它有几次
     */
    @Test
    public void testRemove(){
        Bag<String> bag = new HashBag<>();
        bag.add("a");
        bag.add("a");
        bag.add("b");
        bag.add("c");
        bag.add("d", 3);
        bag.add("e", 5);
        bag.add("f", 5);
        bag.add("g", 5);

        System.out.println(bag);

        bag.remove("b");
        bag.remove("a");
        bag.remove("d",3);
        bag.remove("e",10);
        bag.remove("f",3);
        bag.remove("g");

        System.out.println(bag);
    }


    /**
     * TreeBag使用TreeMap作为数据存储，用法与HashBag类似，只是TreeBag会使用自然顺序对元素进行排序。
     */
    @Test
    public void testTreeBag(){
        Bag<String> bag = new TreeBag<>();
        bag.add("a");
        bag.add("c");
        bag.add("b");
        //一次性放置多个元素
        bag.add("d", 3);
        bag.add("a");

        System.out.println(bag);
        System.out.println();

        int aTimes = bag.getCount("a");
        System.out.println(aTimes);
        System.out.println(bag.getCount("b"));
        System.out.println(bag.getCount("c"));
        System.out.println(bag.getCount("d"));

        System.out.println();
        System.out.println(bag.size());
        System.out.println(bag.uniqueSet().size());

        System.out.println();
        Set<String> uniqueSet = bag.uniqueSet();
        uniqueSet.stream()
                .forEach(System.out::println);
    }
}
