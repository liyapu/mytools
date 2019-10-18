package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 20:22
 *
 * List 的帮助类
 */
public class ListUtilsTest {

    /**
     * EmptyIfNull
     * list为null,返回空集合
     */
    @Test
    public void testEmptyIfNull(){
        List nullList = null;
        List<String> emptyList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");

        if(null == nullList || nullList.size() == 0){
            System.out.println("1 :" + Collections.emptyList());
        }
        if(null == emptyList || emptyList.size() == 0){
            System.out.println("2 :" + Collections.emptyList());
        }
        if(CollectionUtils.isEmpty(nullList)){
            System.out.println("3 :" +Collections.emptyList());
        }

        System.out.println("-----------");
        //为null时，才给 空集合
        //为空集合时，原样返回
        System.out.println(ListUtils.emptyIfNull(nullList));
        System.out.println(ListUtils.emptyIfNull(emptyList));
        System.out.println(ListUtils.emptyIfNull(strList));
    }

    @Test
    public void testDefaultIfNull(){
        List nullList = null;
        List<String> emptyList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");

        List<String> defaultList = new ArrayList<>();
        defaultList.add("default value");

        List defaultListDefault = ListUtils.defaultIfNull(nullList, defaultList);
        System.out.println(defaultListDefault);

        List<String> defaultListDefault2 = ListUtils.defaultIfNull(emptyList, defaultList);
        System.out.println(defaultListDefault2);

        List<String> defaultListDefault3 = ListUtils.defaultIfNull(strList, defaultList);
        System.out.println(defaultListDefault3);
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
     * sum(A,B)
     * 返回A,B的并集，并且去重
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

        Collection<String> union = ListUtils.union(list1, list2);
        System.out.println(union);

        Collection<String> intersection = ListUtils.intersection(list1, list2);
        System.out.println(intersection);

        Collection<String> subtract = ListUtils.subtract(list1, list2);
        System.out.println(subtract);

        List<String> sum = ListUtils.sum(list1, list2);
        System.out.println(sum);
    }

    @Test
    public void testPartition(){
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        list1.add("e");
        list1.add("f");
        list1.add("g");
        list1.add("h");

        List<List<String>> partitionList = ListUtils.partition(list1, 3);
        partitionList.stream()
                .forEach(System.out::println);
    }

    /**
     * hashCodeForList
     * 给List吧它的HashCode计算出来
     */
    @Test
    public void testHashCodeForList(){
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        int hashCode = ListUtils.hashCodeForList(list1);
        System.out.println(hashCode);
        System.out.println(Objects.hashCode(list1));
    }

    /**
     * fixedSizeList
     * 返回固定大小不可修改长度的list,但元素值可以修改
     *
     * 修改原始列表，会影响 fixedSizeList
     */
    @Test
    public void testFixedSizeList(){
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        List<String> fixedSizeList = ListUtils.fixedSizeList(list1);
        System.out.println("list1 :" + list1);
        System.out.println("fixedSizeList :" + fixedSizeList);
        System.out.println();

        //修改原始 list1
        list1.add("d");
        list1.add("e");
        list1.remove("a");
        System.out.println("list1 :" + list1);
        System.out.println("fixedSizeList :" + fixedSizeList);

        //修改fixedSizeList
        //不可新增
        //java.lang.UnsupportedOperationException: List is fixed size
//        fixedSizeList.add("ff");
        //不可删除
//        java.lang.UnsupportedOperationException: List is fixed size
//        fixedSizeList.remove("d");
        //可以指定位置修改
        fixedSizeList.set(2,"ccccc");

    }
}
