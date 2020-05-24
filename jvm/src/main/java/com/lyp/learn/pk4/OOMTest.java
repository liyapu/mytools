package com.lyp.learn.pk4;

import java.util.ArrayList;
import java.util.List;

/**
 *  VM option 设置为  -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 *   Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class OOMTest {
    public static void main(String[] args) {
        List<Picture> list = new ArrayList<>();
        // 1M
        int length = 1024 * 1024;

        //每个对象 1m, 当 i 为 循环 18时，就报 java.lang.OutOfMemoryError: Java heap space
//        for (int i = 0; i < 18; i++) {
        for (int i = 0; i < 18; i++) {
            list.add(new Picture(length));
        }
        System.out.println(list);
    }
}


class Picture{
    private byte[] pixels;

    public Picture(int length){
        this.pixels = new byte[length];
    }
}