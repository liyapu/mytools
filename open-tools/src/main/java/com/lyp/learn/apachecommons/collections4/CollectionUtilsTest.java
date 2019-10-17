package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 20:44
 */
public class CollectionUtilsTest {

    /**
     * 判断集合是否为空
     */
    @Test
    public void testIsEmpty(){
        List<Integer> a = new ArrayList<>();
        List<Integer> b = null;
        List<Integer> c= new ArrayList<>();
        c.add(5);
        c.add(6);

        //判断集合是否为空
        System.out.println(CollectionUtils.isEmpty(a));
        System.out.println(CollectionUtils.isEmpty(b));
        System.out.println(CollectionUtils.isEmpty(c));
        System.out.println();

        //判断集合是否不为空
        System.out.println(CollectionUtils.isNotEmpty(a));
        System.out.println(CollectionUtils.isNotEmpty(b));
        System.out.println(CollectionUtils.isNotEmpty(c));
    }

    @Test
    public void testOperator(){
        //两个集合间的操作
        List<Integer> e = new ArrayList<>();
        e.add(2);
        e.add(1);
        List<Integer> f = new ArrayList<>();
        f.add(1);
        f.add(2);
        List<Integer> g = new ArrayList<>();
        g.add(12);

        //比较两集合值
        System.out.println(CollectionUtils.isEqualCollection(e,f));
        System.out.println(CollectionUtils.isEqualCollection(e,g));
        System.out.println();

        List<Integer> h = new ArrayList<>();
        h.add(1);
        h.add(2);
        h.add(3);
        List<Integer> i = new ArrayList<>();
        i.add(3);
        i.add(3);
        i.add(4);
        i.add(5);

        //并集
        System.out.println(CollectionUtils.union(i,h));
        //交集
        System.out.println(CollectionUtils.intersection(i,h));
        //交集的补集
        System.out.println(CollectionUtils.disjunction(i,h));
        //i与h的差
        System.out.println(CollectionUtils.subtract(h,i));
        System.out.println(CollectionUtils.subtract(i,h));
    }
}
