package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.set.ListOrderedSet;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 17:19
 *
 * ListOrderedSet
 *   去重的List
 *   保证顺序的Set
 */
public class ListOrderedSetTest {

    @Test
    public void test01(){
        ListOrderedSet<String> listOrderedSet = new ListOrderedSet<>();
        listOrderedSet.add("e");
        listOrderedSet.add("a");
        listOrderedSet.add("f");
        listOrderedSet.add("a");
        listOrderedSet.add("c");
        listOrderedSet.add("b");
        listOrderedSet.add("e");

        System.out.println(listOrderedSet);
    }
}
