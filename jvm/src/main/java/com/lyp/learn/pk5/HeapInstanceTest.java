package com.lyp.learn.pk5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  -Xms600m  -Xmx600m
 *
 *  然后打开 jvisualvm ，安装 Visual GC 插件，查看堆内存的变化
 *
 *  Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 *  Java heap space 老年代的空间不够用了，堆空间oom
 *
 */
public class HeapInstanceTest {
    // 200kb 随机
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        List<HeapInstanceTest> list = new ArrayList<>();
        while (true){
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
