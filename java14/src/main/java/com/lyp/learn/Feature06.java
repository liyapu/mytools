package com.lyp.learn;

import java.util.ArrayList;

/**
 *  垃圾收集器的更新：① 弃用ParallelScavenge和SerialOld GC组合
 *                  ② 删除CMS垃圾回收器
 *                  ③ ZGC 可以在macOS和Windows上使用
 *  -XX:+UseParallelGC -XX:-UseParallelOldGC
 *
 *  -XX:-UseParallelOldGC
 *
 *   -XX:+UseConcMarkSweepGC
 *
 *   -XX:+UnlockExperimentalVMOptions -XX:+UseZGC
 *
 * @author shkstart Email:shkstart@126.com
 * @create 2020-04-01 下午 4:02
 */
public class Feature06 {

    public static void main(String[] args) {
        ArrayList<byte[]>  list = new ArrayList<>();

        while(true){
            byte[] arr = new byte[100];
            list.add(arr);
        }
    }

}
