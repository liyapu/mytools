package com.lyp.learn.pk4;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-03 17:51
 *
 *     VMOptions : 配置如下，可以看到打印值，证明生效了
 *          -Xms500m  -Xmx3g
 *          -Xms500m  -Xmx3g -XX:+PrintGCDetails
 *
 *          -Xmx:maxMemory = 2863661056 字节
 *          -Xmx:maxMemory = 2731 MB
 *
 *         -Xms:totalMemory = 502792192 字节
 *         -Xms:totalMemory = 479 MB
 */
public class HeapSizeTest {
    public static void main(String[] args) {
        //获取处理器数,逻辑上的 比如 4核8线程 的返回 8
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回 Java 虚拟机的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("availableProcessors = " + availableProcessors);

        System.out.println("-Xmx:maxMemory = " + maxMemory + " 字节");
        System.out.println("-Xmx:maxMemory = " + (maxMemory/1024/1024) + " MB");
        System.out.println();

        System.out.println("-Xms:totalMemory = " + totalMemory + " 字节");
        System.out.println("-Xms:totalMemory = " + (totalMemory/1024/1024) + " MB");
    }
}
















