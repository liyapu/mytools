package com.lyp.learn.base.collections;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("ABCDEa123abc", "11");
        hashMap.put("ABCDFB123abc", "1111");
        hashMap.put("a", "aaa");
        hashMap.put("a", "aaa11");
        hashMap.putIfAbsent("a", "aaa22");
        hashMap.put("b", "bbb");
        hashMap.put("c", "ccc");
        hashMap.put("d", "ddd");
        Set<String> set = hashMap.keySet();
        //set.add("e");
        //set.add("f");
        set.remove("d");
        System.out.println("---");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * computeIfAbsent() 方法
     * computeIfAbsent() 方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。
     * hashmap.computeIfAbsent(K key, Function remappingFunction)
     * 如果 key 对应的 value 不存在，则使用获取 remappingFunction 重新计算后的值，并保存为该 key 的 value，否则返回 value。
     */
    @Test
    public void testComputeIfAbsent01() {
        // 创建一个 HashMap
        HashMap<String, Integer> priceMap = new HashMap<>();

        // 往HashMap中添加映射关系
        priceMap.put("Shoes", 180);
        priceMap.put("Bag", 300);
        priceMap.put("Pant", 150);
        System.out.println("HashMap: " + priceMap);

        // Shoes中的映射关系已经存在
        // Shoes并没有计算新值
        int shoePrice = priceMap.computeIfAbsent("Shoes", (key) -> 280);
        System.out.println("Price of Shoes: " + shoePrice);

        // 输出更新后的 HashMap
        System.out.println("Updated HashMap: " + priceMap);
    }

    @Test
    public void testComputeIfAbsent02() {
        // 创建一个 HashMap
        HashMap<String, Integer> priceMap = new HashMap<>();

        // 往HashMap中添加映射项
        priceMap.put("Shoes", 200);
        priceMap.put("Bag", 300);
        priceMap.put("Pant", 150);
        System.out.println("HashMap: " + priceMap);

        // 计算 Shirt 的值
        int shirtPrice = priceMap.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + priceMap);
    }

    /**
     * 编程中经常遇到这种数据结构，判断一个map中是否存在这个key，
     * 如果存在则处理value的数据，
     * 如果不存在，则创建一个满足value要求的数据结构放到value中。
     * 以前常用的方法如下：
     */
    @Test
    public void testComputeIfAbsent03() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");

        hashMap.put("china", set);

        // 判断map中是否存在，如果存在则添加元素到set中，如果不存在则新建set添加到hashMap中
        if (hashMap.containsKey("china")) {
            hashMap.get("china").add("liSi");
        } else {
            Set<String> setTmp = new HashSet<>();
            setTmp.add("liSi");
            hashMap.put("china", setTmp);
        }
        System.out.println(hashMap);
    }

    /**
     * 官方非常的贴心，为了满足广大用户的要求，加入了computeIfAbsent() 这个api,
     * 使用后以上代码变成了下面的形式：
     */
    @Test
    public void testComputeIfAbsent04() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");

        hashMap.put("china", set);
        // after JDK1.8
        hashMap.computeIfAbsent("china", key -> getValues(key)).add("liSi");
        System.out.println(hashMap);
    }

    @Test
    public void testComputeIfAbsent0411() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");

        hashMap.put("china", set);
        // after JDK1.8
        Set<String> chinaSet = hashMap.computeIfAbsent("china", key -> getValues(key));
        chinaSet.add("liSi");

        //key为空，则返回一个新set集合，并和 qin键关联起来
        Set<String> qinSet = hashMap.computeIfAbsent("qin", key -> Sets.newHashSet());
        qinSet.add("qinshihuang");

        System.out.println(hashMap);
    }

    public static HashSet getValues(String key) {
        System.out.println("---getValues---");
        return new HashSet();
    }

    @Test
    public void testComputeIfAbsent05() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");

        hashMap.put("china", set);
        // after JDK1.8
        hashMap.computeIfAbsent("qin", key -> getValues(key)).add("liSi");
        System.out.println(hashMap);
    }

    @Test
    public void testComputeIfAbsent06() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");

        hashMap.put("china", set);

        Set<String> set2 = hashMap.getOrDefault("china", new HashSet<>());
        set2.add("lisi");

        Set<String> set3 = hashMap.getOrDefault("qin", new HashSet<>());
        set3.add("qqq");
        hashMap.put("qin", set3);

        System.out.println(hashMap);
    }

    @Test
    public void testComputeIfAbsent07() {
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        Set<String> set = new HashSet<>();
        set.add("zhangSan");
        hashMap.put("china", set);

        Set<String> set2 = hashMap.computeIfAbsent("china", key -> new HashSet<>());
        set2.add("lisi");

        Set<String> set3 = hashMap.computeIfAbsent("qin", key -> new HashSet<>());
        set3.add("qqq");

        hashMap.computeIfAbsent("han", key -> new HashSet<>()).add("hhh");

        System.out.println(hashMap);
    }


}
