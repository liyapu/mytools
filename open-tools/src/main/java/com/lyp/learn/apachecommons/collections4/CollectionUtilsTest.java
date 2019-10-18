package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
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

    /**
     * 测试集合是否相等（顺序无关）
     */
    @Test
    public void testEqual(){
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};

        List<String> list2 = new ArrayList<String>(){{
            add("b");
            add("a");
            add("c");
        }};

        List<String> list3 = new ArrayList<String>(){{
            add("b");
            add("c");
        }};

        List<String> list4 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        List<String> list5 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        List<String> list6 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("b");
            add("b");
            add("c");
        }};

        System.out.println("is11 : " + CollectionUtils.isEqualCollection(list1,list1));
        System.out.println("is12 : " + CollectionUtils.isEqualCollection(list1,list2));
        System.out.println();

        System.out.println("is13 : " + CollectionUtils.isEqualCollection(list1,list3));
        System.out.println("is14 : " + CollectionUtils.isEqualCollection(list1,list4));
        System.out.println("is15 : " + CollectionUtils.isEqualCollection(list1,list5));

        System.out.println();
        System.out.println("list1 : " + list1);
        System.out.println("list6 : " + list6);
        System.out.println("is16 : " + CollectionUtils.isEqualCollection(list1,list6));
    }

    /**
     * union(A,B)
     * 返回A,B的并集
     *
     * intersection(A,B)
     * 返回A，B的交集
     *
     * subtract(A,B)
     * 在A中不在B的集合
     *
     * disjunction(A,B)
     * 在A中不在B中的 + 在B中不在A中的
     */
    @Test
    public void testOperator(){
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");

        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");
        list2.add("e");
        list2.add("f");

        Collection<String> union = CollectionUtils.union(list1, list2);
        System.out.println(union);

        Collection<String> intersection = CollectionUtils.intersection(list1, list2);
        System.out.println(intersection);

        Collection<String> subtract = CollectionUtils.subtract(list1, list2);
        System.out.println(subtract);

        Collection<String> disjunction = CollectionUtils.disjunction(list1, list2);
        System.out.println(disjunction);

    }
}
