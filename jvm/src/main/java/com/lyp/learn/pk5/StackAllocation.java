package com.lyp.learn.pk5;

/**
 *  第一次:
 *      -Xms100m -Xmx100m -XX:+PrintGCDetails
 *
 *  第二次:
 *      -Xms100m -Xmx100m -XX:+PrintGCDetails  -XX:-DoEscapeAnalysis
 *
 *   jdk7以及之后，DoEscapeAnalysis 默认是开启的
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        //查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为: " + (end - start) + "ms");

        //为方便查看堆内存中对象个数，线程 sleep
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc(){
        // 为发生逃逸
        User user = new User();
    }


    static class User{

    }
}
