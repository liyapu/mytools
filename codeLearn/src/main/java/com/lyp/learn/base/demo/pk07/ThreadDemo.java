package com.lyp.learn.base.demo.pk07;

public class ThreadDemo {
    public static void main(String[] args) {

        System.out.println(java.lang.Thread.MAX_PRIORITY);
        System.out.println(java.lang.Thread.NORM_PRIORITY);
        System.out.println(java.lang.Thread.MIN_PRIORITY);

        //获取当前线程的名字
        System.out.println(java.lang.Thread.currentThread().getName());
        //获取当前线程id
        System.out.println(java.lang.Thread.currentThread().getId());
        //获取当前线程状态
        System.out.println(java.lang.Thread.currentThread().getState());
        System.out.println(java.lang.Thread.currentThread().getThreadGroup());
    }
}
