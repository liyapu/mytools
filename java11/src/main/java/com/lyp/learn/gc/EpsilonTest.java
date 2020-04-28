package com.lyp.learn.gc;

import java.util.ArrayList;
import java.util.List;

/**
 *   A NoOp Garbage Collector
 *   JDK上对这个特性的描述是: 开发一个处理内存分配但不实现任何实际内存回收机制的GC, 一旦可用堆内存用完, JVM就会退出.
 *   如果有System.gc()调用, 实际上什么也不会发生(这种场景下和-XX:+DisableExplicitGC效果一样),
 *   因为没有内存回收, 这个实现可能会警告用户尝试强制GC是徒劳.
 *
 *   vm参用法 : -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
 *   指定最小和最大堆内存，减少动态切换
 *   -Xms1024m -Xmx1024m -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC  -XX:+AlwaysPreTouch
 *
 *    如果使用选项-XX:+UseEpsilonGC, 程序很快就因为堆空间不足而退出
 *
 *    使用这个选项的原因 :
 *       提供完全被动的GC实现, 具有有限的分配限制和尽可能低的延迟开销,但代价是内存占用和内存吞吐量.
 *
 *       众所周知, java实现可广泛选择高度可配置的GC实现. 各种可用的收集器最终满足不同的需求,
 *       即使它们的可配置性使它们的功能相交. 有时更容易维护单独的实现, 而不是在现有GC实现上堆积另一个配置选项
 *
 *   主要用途如下 :
 * 	     性能测试(它可以帮助过滤掉GC引起的性能假象)
 * 	     内存压力测试(例如,知道测试用例 应该分配不超过1GB的内存, 我们可以使用-Xmx1g –XX:+UseEpsilonGC, 如果程序有问题, 则程序会崩溃)
 * 	     非常短的JOB任务(对象这种任务, 接受GC清理堆那都是浪费空间)
 * 	    VM接口测试
 *    	Last-drop 延迟&吞吐改进
 *
 *
 */
public class EpsilonTest {
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


class Garbage{
    private double d1 = 1L;
    private double d2 = 2L;

    private byte[] barr = new byte[1024];

    //这个方法时 GC 在清除本对象时，会调用一次
    @Override
    public void finalize(){
        System.out.println(this + " finalize........");
    }
}
