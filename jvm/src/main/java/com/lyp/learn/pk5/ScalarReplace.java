package com.lyp.learn.pk5;

/**
 *  EliminateAllocations 标量替换，默认是开启的
 *
 *  第一次:
 *    -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails  -XX:-EliminateAllocations
 *
 *  第二次:
 *    -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails  -XX:+EliminateAllocations
 */
public class ScalarReplace {

    public static class User{
        public int id;
        public String name;
    }

    public static void alloc(){
        // user 未发生逃逸
        User user = new User();
        user.id = 5;
        user.name = "aaaaaaaaaaaa";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间 " + (end - start) + " ms");
    }

}
