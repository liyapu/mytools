package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-20 11:26
 */
public class RandomUtilsTest {

    @Test
    public void testNextBoolean(){
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.nextBoolean());
        }
    }

    /**
     *  [包含，不包含)
     *  [1,100)
     */
    @Test
    public void testNextInt(){
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < 10000; i++) {
            int nextInt = RandomUtils.nextInt(1, 100);
            treeSet.add(nextInt);
//            System.out.println(nextInt);
        }

        System.out.println("treeSet 大小 = " + treeSet.size());
        treeSet.forEach(System.out::println);
    }

    @Test
    public void testNextInt2(){
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < 10000; i++) {
            int nextInt = RandomUtils.nextInt();
            treeSet.add(nextInt);
//            System.out.println(nextInt);
        }

        System.out.println("treeSet 大小 = " + treeSet.size());
        treeSet.forEach(System.out::println);
    }

    @Test
    public void testNextLong(){
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            long nextLong = RandomUtils.nextLong(1, 100);
            treeSet.add(nextLong);
        }

        System.out.println("treeSet 大小 = " + treeSet.size());
        treeSet.forEach(System.out::println);
    }

    @Test
    public void testNextLong2(){
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            long nextLong = RandomUtils.nextLong();
            treeSet.add(nextLong);
        }

        System.out.println("treeSet 大小 = " + treeSet.size());
        treeSet.forEach(System.out::println);
    }
}
