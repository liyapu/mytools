package com.lyp.learn.pk4;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-03 18:22
 *
 *    -Xms500m  -Xmx500m -XX:+PrintGCDetails  -XX:+HeapDumpOnOutOfMemoryError
 *
 *    find / -name 'java_pid6268.hprof'
 *    在此目录下生成  /Users/liyapu/myGitRepository/mytools
 *
 *     Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 */
public class HeapOOMTest {
    public static void main(String[] args) {
        // 1M
        byte[] bArr = new byte[1024 * 1024];
        List<Object> list = new ArrayList<>();

        while (true){
            list.add(bArr);
        }
    }
}
