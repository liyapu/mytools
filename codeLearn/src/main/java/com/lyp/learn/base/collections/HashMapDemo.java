package com.lyp.learn.base.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

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
}
