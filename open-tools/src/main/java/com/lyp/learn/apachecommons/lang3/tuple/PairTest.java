package com.lyp.learn.apachecommons.lang3.tuple;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 20:20
 *
 * 配对(Pair提供了一种方便方式来处理简单的键值关联，当我们想从方法返回两个值时特别有用。
 */
public class PairTest {

    @Test
    public void test01(){
        Pair<String,String> pair = MutablePair.of("A","a");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());

        System.out.println();
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
    }

    @Test
    public void test02(){
        Pair<String,String> pair = MutablePair.of("A","a");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());

        System.out.println();
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
    }

    /**
     * 多个返回值
     */
    @Test
    public void test03(){
        Pair<String, Integer> pair = methodReturn();
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
    }

    public Pair<String,Integer> methodReturn(){
        Pair<String,Integer> pair = ImmutablePair.of("10个人",6);
        return pair;
    }


    @Test
    public void test011(){
        Pair<Integer, Integer> pair = Pair.of(1, 10); //同ImmutablePair.of(1, 10)
        Integer left = pair.getLeft();
        Integer right = pair.getRight();
        System.out.println(left);
        System.out.println(right);


        pair = MutablePair.of(1, 10);
        left = pair.getLeft();
        right = pair.getRight();
        ((MutablePair<Integer, Integer>) pair).setLeft(100);
        ((MutablePair<Integer, Integer>) pair).setRight(200);
        System.out.println(left);
        System.out.println(right);
        pair.setValue(200);

    }

}
