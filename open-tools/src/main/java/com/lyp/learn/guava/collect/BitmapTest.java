package com.lyp.learn.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 09:35
 *
 * 在 Java 集合类库中有个 Map，它的特点是存放的键（Key）是唯一的，而值（Value）可以不唯一，
 * 如果我们需要键（Key）和值（Value）都唯一，该怎么实现？
 * 这就是今天要谈的BiMap结构，它允许我们可以通过特定的 value 获取 key 值。
 *
 * 在过去，如果需要将Map结构中的键值对反转（也就是 key->value 转变成 value->key），这时候我们需要定义两个Map数据结构来存储。
 * 现在使用一个BiMap就可以了
 *
 * BiMap<K, V> 是 Map<K, V> 类型的数据类型（因为 BiMap 实现了 java.util.Map 接口，
 * 注意和 Multimap 的区别，Multimap 没实现 Map 接口）。
 *
 * 它的特点是它的 value 和它 key 一样也是不可重复的，换句话说它的 key 和 value 是等价的。
 * 如果你往 BiMap 的 value 里面放了重复的元素，就会得到 IllegalArgumentException
 *
 * BiMap的常用实现有：
 *      HashBiMap：key 集合与 value 集合都有 HashMap 实现
 *      EnumBiMap：key 与 value 都必须是 enum 类型
 *      ImmutableBiMap：不可修改的 BiMap
 */
public class BitmapTest {

    /**
     * 基本操作
     * put
     * putAll
     * get
     * getOrDefault
     */
    @Test
    public void testPutGet(){
        BiMap<String, String> upperToSmall = HashBiMap.create();
        upperToSmall.put("A", "a");
        upperToSmall.put("B", "b");
        upperToSmall.put("C", "c");

        System.out.println(upperToSmall.get("B"));
        System.out.println(upperToSmall.get("X"));
        System.out.println(upperToSmall.getOrDefault("Y","yyy"));

        System.out.println();
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");

        upperToSmall.putAll(map);
        System.out.println(upperToSmall);

    }

    /**
     * values
     * 返回此映射中包含的值的Collection视图.
     */
    @Test
    public void testValues(){
        BiMap<String, String> upperToSmall = HashBiMap.create();
        upperToSmall.put("A", "a");
        upperToSmall.put("B", "b");
        upperToSmall.put("C", "c");

        Set<String> values = upperToSmall.values();
        values.stream().forEach(System.out::println);
    }

    /**
     * 放入重复的key 和 value
     * BiMap 强制其value的唯一性，如果发现违规则会抛出 IllegalArgumentException。
     * 若想放已经存在的 值，需要使用forcePut：强制在BiMap中放入具有相同value值的键值对.
     */
    @Test
    public void testForcePut(){
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("k1","v1");
        biMap.put("k2","v2");
        biMap.put("k3","v3");
        //下面这句报 java.lang.IllegalArgumentException: value already present: v1 异常
//        biMap.put("k4","v1");
        biMap.forcePut("k4","v1");
        biMap.put("k2","v222");
        System.out.println(biMap.get("k1"));
        System.out.println(biMap.get("k2"));
        System.out.println(biMap.get("k3"));
        System.out.println(biMap.get("k4"));
        System.out.println(biMap);
    }

    /**
     * inverse()
     * 进行键值对的反转，返回BiMap的一种双向映射关系，
     * 所有对最初的BiMap的操作都会影响关联后的BiMap，同样的，对关联后BiMap的操作也影响最初的BiMap.
     *
     * 需要注意的是，inverse 方法返回一个反转后的 BiMap，即 key/value 互相切换的映射。
     * 这个反转的 map 并不是一个新的 map（upperToSmall == upperToSmall.inverse().inverse()），而是一个视图，
     * 这意味着，在这个反转后的map中的任何增删改操作都会影响原来的 map。
     *
     */
    @Test
    public void testVerse1(){
        BiMap<String, String> upperToSmall = HashBiMap.create();
        upperToSmall.put("A", "a");
        upperToSmall.put("B", "b");
        upperToSmall.put("C", "c");

        BiMap<String, String> smallToUpper = upperToSmall.inverse();

        System.out.println(smallToUpper.get("b"));
        System.out.println(smallToUpper.get("x"));
    }

    @Test
    public void testInverse2(){
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("k1","v1");
        biMap.put("k2","v2");
        biMap.put("k3","v3");
        System.out.println(biMap);

        System.out.println("-----------------");
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse);

        System.out.println("-------------------");
        inverse.put("v4","k4");
        inverse.put("k5","v5");
        System.out.println(inverse);
        System.out.println(biMap);
    }


    /**
     * 如果 Map 中存在多个 value 相同的元素会发生什么情况呢？
     * 这时候添加进去的 key 将会覆盖先前加进去的 key。如下所示：
     * k1->v1，k2->v2，k3->v3，k4->v1
     * 如果我们需要反转 key 和 Value 将会变成 v1->k4，v2->k2，v3->k3，这是因为Map的特点 v1->k4 将覆盖先前的 v1->k1。
     */
    @Test
    public void testForcePutInverse(){
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("k1","v1");
        biMap.put("k2","v2");
        biMap.put("k3","v3");
        biMap.forcePut("k4","v1");
        System.out.println(biMap.get("k1"));
        System.out.println(biMap.get("k2"));
        System.out.println(biMap.get("k3"));
        System.out.println(biMap.get("k4"));
        System.out.println(biMap);

        System.out.println("------------------");
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse.get("v1"));
        System.out.println(inverse.get("v2"));
        System.out.println(inverse.get("v3"));
        System.out.println(inverse);
    }

}
