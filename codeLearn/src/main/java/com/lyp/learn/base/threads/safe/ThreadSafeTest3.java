package com.lyp.learn.base.threads.safe;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadSafeTest3 {
    //启动线程数
    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        //进行10次测试
        for(int i = 1 ; i <= 10; i++){
            test(i);
        }

    }

    public static void test(int times){
        //使用 ArrayList 实现存储
        List<Object> list = new ArrayList<>();
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        //新建THREAD_COUNT个线程，并启动
        for(int i = 1 ; i <= THREAD_COUNT; i++){
            Thread thread = new CollThread3(list,countDownLatch);
            thread.start();
        }
        // 主线程等待所有子线程执行完成，再向下执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(times + " ===== " + list.size());
    }

}



class CollThread3 extends Thread{
    private List<Object> list;
    private CountDownLatch latch;

    public CollThread3(List<Object> list,CountDownLatch latch){
        this.list = list;
        this.latch = latch;
    }

    @Override
    public void run(){
        for(int i = 1 ; i <= 100; i++){
            //synchronized ，使用 list对象锁
            synchronized (list){
                list.add(i);
            }
        }
        latch.countDown();
    }
}