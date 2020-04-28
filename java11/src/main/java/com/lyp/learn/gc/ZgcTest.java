package com.lyp.learn.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-28 18:23
 *  ZGC
 *
 *     因为ZGC还处于实验阶段, 所以需要通过JVM参数来解锁这个特性
 *     -Xms1024m -Xmx1024m -XX:+UnlockExperimentalVMOptions -XX:+ZGC
 */
public class ZgcTest {

    public static void main(String[] args) {
        List<Garbage> list = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        while (flag){
            list.add(new Garbage());
            if(count++ == 500){
                list.clear();
                count = 0;
            }
        }
    }
}
