package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.list.TreeList;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 17:26
 *
 * TreeList
 * 实现了优化的快速插入和删除任何索引的列表。
 * 这个列表内部实现利用树结构,确保所有的插入和删除都是O(log n)
 *
 */
public class TreeListTest {

    @Test
    public void test01(){
        List<String> list = new TreeList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("e");

        System.out.println(list);
        list.remove("a");
        System.out.println(list);
    }
}
