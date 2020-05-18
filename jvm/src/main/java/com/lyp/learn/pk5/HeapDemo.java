package com.lyp.learn.pk5;

/**
 *  -Xms10m -Xmx10m
 *
 *  通过给两个 HeapDemo 设置不同的参数，
 *  验证一个java 进程一个 jvm 实例，实例中包含一个 运行时数据区
 *
 *  然后再 java 的 bin 目录下 打开 jvisualvm
 *   /Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home/bin
 *
 *
 */
public class HeapDemo {

    public static void main(String[] args) {
        System.out.println("HeapDemo start.....");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HeapDemo end.....");
    }
}
