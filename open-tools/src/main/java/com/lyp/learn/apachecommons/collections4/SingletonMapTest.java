package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.map.SingletonMap;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 16:50
 *
 * 单值 map
 */
public class SingletonMapTest {

    @Test
    public void test01(){
        SingletonMap<String, String> singletonMap = new SingletonMap<>();

        System.out.println(singletonMap);
        //size已经是1了
        System.out.println(singletonMap.size());
        System.out.println(singletonMap.maxSize());

        //哪怕一个都没有 也不能设置值
        //map.put("one","one"); //Cannot put new key/value pair - Map is fixed size singleton

        //虽然不能再放key 但可以改值
        singletonMap.setValue("xiang");
        System.out.println(singletonMap);

        //一般建议在构造的时候，就给key和value赋值  如下：
        singletonMap = new SingletonMap<>("fang","shixiang");
        System.out.println(singletonMap);
    }
}
