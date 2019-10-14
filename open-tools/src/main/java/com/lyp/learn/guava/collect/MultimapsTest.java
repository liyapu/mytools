package com.lyp.learn.guava.collect;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import org.junit.jupiter.api.Test;

/**
 * 提供作用于或生成 Multimap 的静态方法。
 */
public class MultimapsTest {
    @Test
    public void asMap() {
        ListMultimap<String, String> listMultimap = LinkedListMultimap.create();
        listMultimap.put("foo", "A");
        listMultimap.put("foo", "B");
        listMultimap.put("foo", "C");

        // 遍历
        listMultimap.forEach((k, v) -> System.out.println("k：" + k + ", v：" + v));

        System.out.println(listMultimap);
    }
}
