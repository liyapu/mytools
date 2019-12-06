package com.lyp.learn.base.threads.safe;


import java.util.ArrayList;
import java.util.List;

public class ThreadSafeTest5 {
    //启动线程数
    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        //进行10次测试
        for(int i = 1 ; i <= 10; i++){
            test();
        }

    }

    public static void test(){
        //新建THREAD_COUNT个线程，并启动
        for(int i = 1 ; i <= THREAD_COUNT; i++){
            Thread thread = new CollThread5();
            thread.start();
        }
    }

}



class CollThread5 extends Thread{
    private List<Object> list = new ArrayList<>();

    public CollThread5(){
    }

    @Override
    public void run(){
        for(int i = 1 ; i <= 10000; i++){
            list.add(i);
        }
        System.out.println(list.size());
    }
}