package com.lyp.learn.pk6;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *  直接内存溢出
 *     直接内存大小可以通过 MaxDirectMemorySize 设置
 *     如果不指定，默认与堆的最大值 -Xmx参数值一致
 */
public class BufferTest2 {
    // 20M
    private static final int BUFFER = 1024 * 1024 * 20;


    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();
        while (true){
            list.add(ByteBuffer.allocateDirect(BUFFER));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

     }
}
