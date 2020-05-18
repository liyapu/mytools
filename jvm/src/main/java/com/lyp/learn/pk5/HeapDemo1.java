package com.lyp.learn.pk5;

/**
 *  -Xms20m -Xmx20m
 *
 *  通过给两个 HeapDemo 设置不同的参数，
 *  验证一个java 进程一个 jvm 实例，实例中包含一个 运行时数据区
 */
public class HeapDemo1 {

    public static void main(String[] args) {
        System.out.println("HeapDemo1 start.....");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HeapDemo1 end.....");
    }
}
