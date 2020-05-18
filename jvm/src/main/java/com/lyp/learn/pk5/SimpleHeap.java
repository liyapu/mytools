package com.lyp.learn.pk5;

/**
 * https://www.jianshu.com/p/772b7f6d188e
 *
 *   一、打印GC日志  PrintGCDetails
 *        -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 *        GC日志：[GC (Allocation Failure) [PSYoungGen: 32978K->1512K(38400K)] 32978K->1520K(125952K), 0.0020363 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *       可以看出新生代 YoungGen 分配失败，32978K->1512K(38400K) 表示 GC 前已使用->GC后已使用（该区域总容量），[]之外表示Java堆GC前已使用->GC后已使用（Java堆总容量），最后为 GC 耗时
 *
 *   二、
 *     -XX:+PrintHeapAtGC的含义是打印GC前后的详细堆栈信息
 *    将它配置在VM options中
 *
 *  三、
 *     -XX:+TraceClassLoading的作用是监控类的加载
 *     将它配置在VM options中
 *
 */
public class SimpleHeap {
    private  int id;
    public SimpleHeap(int id){
        this.id = id;
    }

    public void show(){
        System.out.println("My ID is " + id);
    }
    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(10);
        SimpleHeap s2 = new SimpleHeap(20);

        int [] arr1 = new int[10];
        Object[] arr2 = new Object[5];
    }
}
