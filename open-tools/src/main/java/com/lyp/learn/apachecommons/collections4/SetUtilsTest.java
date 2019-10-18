package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 19:39
 */
public class SetUtilsTest {

    /**
     * 是否为空
     */
    @Test
    public void testEmpty(){
        Set<String> nullSet = null;
        Set<String> emptySet = new HashSet<>();
        Set<String> strSet = new HashSet<>();
        strSet.add("a");
        strSet.add("b");

        System.out.println(CollectionUtils.isEmpty(nullSet));
        System.out.println(CollectionUtils.isEmpty(emptySet));
        System.out.println(CollectionUtils.isEmpty(strSet));
    }


    /**
     * union(A,B)
     * 返回A,B的并集
     *
     * intersection(A,B)
     * 返回A,B的交集
     *
     * difference(A,B)
     * 返回在A中不在B中的元素
     *
     * disjunction(A,B)
     * 返回在A中不在B的元素 + 在B中不在A中的元素
     *
     */
    @Test
    public void testCompute(){
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
            add("f");
        }};

        SetUtils.SetView<String> union = SetUtils.union(set1, set2);
        System.out.println(union);
        System.out.println(union.toSet());
        System.out.println();

        SetUtils.SetView<String> intersection = SetUtils.intersection(set1, set2);
        System.out.println(intersection);

        SetUtils.SetView<String> difference = SetUtils.difference(set1, set2);
        System.out.println(difference);

        SetUtils.SetView<String> disjunction = SetUtils.disjunction(set1, set2);
        System.out.println(disjunction);
    }

    /**
     * 两个集合元素都一样时才相等，顺序无关
     */
    @Test
    public void testEqualSet(){
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};

        Set<String> set2 = new HashSet<String>(){{
            add("b");
            add("c");
            add("a");
        }};

        Set<String> set3 = new HashSet<String>(){{
            add("a");
            add("b");
        }};

        Set<String> set4 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        Set<String> set5 = new HashSet<String>(){{
            add("c");
            add("f");
        }};

        boolean is11 = SetUtils.isEqualSet(set1, set1);
        System.out.println("is11 :" + is11);

        boolean is12 = SetUtils.isEqualSet(set1, set2);
        System.out.println("is12 :" + is12);
        System.out.println();

        System.out.println("is13 :" + SetUtils.isEqualSet(set1,set3));
        System.out.println("is14 :" + SetUtils.isEqualSet(set1,set4));
        System.out.println("is15 :" + SetUtils.isEqualSet(set1,set5));
    }
}
