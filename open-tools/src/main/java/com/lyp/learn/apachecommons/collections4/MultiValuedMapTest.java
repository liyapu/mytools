package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 15:19
 *
 * 多值Map
 *
 * 一个key可对应多个值，内部的数据结构逻辑交给它去维护。
 * 我们平时使用的Map<String,List<Long>>这种数据结构，就可以被这种代替，使用起来非常方便
 */
public class MultiValuedMapTest {

    /**
     * ArrayListValuedHashMap
     * 见名之意，values采用ArrayList来存储
     */
    @Test
    public void testArrayListValuedHashMap(){
        MultiValuedMap<String,Integer> multiValuedMap = new ArrayListValuedHashMap<>();
        multiValuedMap.put("c",5);
        multiValuedMap.put("c",2);
        multiValuedMap.put("c",1);
        multiValuedMap.put("c",8);

        multiValuedMap.put("b",6);
        multiValuedMap.put("b",2);
        multiValuedMap.put("b",5);

        multiValuedMap.put("a",6);

        multiValuedMap.put("d",2);
        multiValuedMap.put("d",4);

        System.out.println(multiValuedMap);
        System.out.println(multiValuedMap.size());
        System.out.println(multiValuedMap.keys().size());
        System.out.println(multiValuedMap.keySet().size());
        System.out.println(multiValuedMap.get("b"));
        System.out.println(multiValuedMap.get("x"));

        System.out.println();
        System.out.println("----------asMap-----------");
        Map<String, Collection<Integer>> asMap = multiValuedMap.asMap();
        System.out.println(asMap);

        System.out.println("----------entries-----------");
        Collection<Map.Entry<String, Integer>> entries = multiValuedMap.entries();
        for(Map.Entry<String,Integer> entry : entries){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("----------keys-----------");
        MultiSet<String> keys = multiValuedMap.keys();
        System.out.println(keys);

        System.out.println("----------keySet-----------");
        Set<String> keySet = multiValuedMap.keySet();
        System.out.println(keySet);

        System.out.println("----------values-----------");
        Collection<Integer> values = multiValuedMap.values();
        System.out.println(values);
    }

    @Test
    public void testContains(){
        MultiValuedMap<String,Integer> multiValuedMap = new ArrayListValuedHashMap<>();
        multiValuedMap.put("b",6);
        multiValuedMap.put("b",2);
        multiValuedMap.put("b",5);

        multiValuedMap.put("a",6);

        System.out.println(multiValuedMap.containsKey("b"));
        System.out.println(multiValuedMap.containsKey("x"));

        System.out.println();
        System.out.println(multiValuedMap.containsValue(2));
        System.out.println(multiValuedMap.containsValue(1000));

        System.out.println();
        System.out.println(multiValuedMap.containsMapping("b",2));
        System.out.println(multiValuedMap.containsMapping("b",200));

    }

    @Test
    public void testRemove(){
        MultiValuedMap<String,Integer> multiValuedMap = new ArrayListValuedHashMap<>();
        multiValuedMap.put("b",6);
        multiValuedMap.put("b",2);
        multiValuedMap.put("b",5);

        multiValuedMap.put("a",6);

        multiValuedMap.put("c",5);
        multiValuedMap.put("c",2);
        multiValuedMap.put("c",1);

        System.out.println(multiValuedMap);

        System.out.println(multiValuedMap.remove("a"));
        System.out.println(multiValuedMap.remove("c"));
        System.out.println(multiValuedMap.removeMapping("b",2));
        System.out.println(multiValuedMap.removeMapping("b",200));

        System.out.println(multiValuedMap);
    }
}
