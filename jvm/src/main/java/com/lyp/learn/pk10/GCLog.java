package com.lyp.learn.pk10;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  -Xms60m -Xmx60m -XX:+PrintGCDetails -Xloggc:./gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dump.hprof
 */
public class GCLog {
    public static void main(String[] args) {
        List<byte[]> list =new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            byte[] arr = new byte[500 * 1024];
            list.add(arr);
            System.out.println(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
