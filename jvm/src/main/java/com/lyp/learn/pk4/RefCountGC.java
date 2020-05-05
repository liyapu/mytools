package com.lyp.learn.pk4;

/**
 * @author: liyapu
 * @description:  GC算法 之 引用计数法
 * @date 2020-05-03 18:56
 *
 *    -Xms10m  -Xmx10m -XX:+PrintGCDetails
 */
public class RefCountGC {

    // 2M
    private byte[] bytes = new byte[2* 1024*1024];
    Object instance = null;

    public static void main(String[] args) {
        RefCountGC objA = new RefCountGC();
        RefCountGC objB = new RefCountGC();

        // 循环引用，空间释放不掉，可以看到  老年代的使用率不为 0
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
