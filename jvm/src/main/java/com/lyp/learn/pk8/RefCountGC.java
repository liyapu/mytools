package com.lyp.learn.pk8;

/**
 *  -Xms20m -Xmx20m -XX:+PrintGCDetails
 *
 */
public class RefCountGC {

    // 这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[1024 * 1024 * 5]; //5MB

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        //显示的执行垃圾回收行为
        // 这里发生GC,obj1和obj2能否被回收?
        // 循环引用，这里看日志，也可以回收，证明java 使用的不是 引用计数算法
        System.gc();
    }


}


















