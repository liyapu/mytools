package com.lyp.learn.pk8;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-23 15:29
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();

        // 提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行gc
        System.gc();
        // 与 Runtime.getRuntime().gc() 的作用一样，底层就是调用的它

        // 强制调用失去引用的对象的 finalized() 方法
//        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("此对象执行了 finalize 方法");
    }
}













