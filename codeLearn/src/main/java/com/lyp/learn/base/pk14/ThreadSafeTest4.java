package com.lyp.learn.base.pk14;


import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class ThreadSafeTest4 {
    //启动线程数
    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        //进行10次测试
        for(int i = 1 ; i <= 10; i++){
            test(i);
        }

    }

    public static void test(int times){
        //使用 Vector 实现存储
        Vector<Object> vector = new Vector<>();
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        //新建THREAD_COUNT个线程，并启动
        for(int i = 1 ; i <= THREAD_COUNT; i++){
            Thread thread = new CollThread4(vector,countDownLatch);
            thread.start();
        }
        // 主线程等待所有子线程执行完成，再向下执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(times + " ===== " + vector.size());
    }

}



class CollThread4 extends Thread{
    private Vector<Object> vector;
    private CountDownLatch latch;

    public CollThread4(Vector<Object> vector,CountDownLatch latch){
        this.vector = vector;
        this.latch = latch;
    }

    @Override
    public void run(){
        for(int i = 1 ; i <= 100; i++){
            //使用vector ,自己保证线程安全
            vector.add(i);
        }
        latch.countDown();
    }
}