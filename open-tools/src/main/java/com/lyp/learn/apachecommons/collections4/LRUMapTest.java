package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.map.LRUMap;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 16:28
 *
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的页面置换算法，选择最近最久未使用的页面予以淘汰
 *
 * LRUMap
 * 底层是LRU算法
 * LRU算法的设计原则是：如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。
 * 也就是说，当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
 *
 *
 */
public class LRUMapTest {

    @Test
    public void test01(){
        LRUMap<String,Integer> lruMap = new LRUMap<>(3);

        System.out.println(lruMap);
        System.out.println(lruMap.size());
        System.out.println(lruMap.maxSize());
        System.out.println(lruMap.isFull());
        System.out.println();

        lruMap.put("a",1);
        lruMap.put("b",2);
        lruMap.put("c",3);

        System.out.println(lruMap);
        System.out.println(lruMap.size());
        System.out.println(lruMap.maxSize());
        System.out.println(lruMap.isFull());
        System.out.println();

        //虽然满了 但还是可以往里面塞数据,会根据lru算法移除以前的key
        lruMap.put("d",4);
        System.out.println(lruMap);

        //使用 key b
        Integer b = lruMap.get("b");
        System.out.println(b);
        System.out.println(lruMap);

        lruMap.put("e",5);
        System.out.println(lruMap);

        System.out.println(lruMap.get("a"));
    }
}
