package com.lyp.learn.pk5;

/**
 *  测试: 大对象直接进入老年代
 *  -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */
public class YongOldAreaTest {
    public static void main(String[] args) {
        // 20m
        byte[] buffer = new byte[1024 * 1024 * 20];
    }
}
