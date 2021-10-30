package com.lyp.learn.cf;

import java.util.concurrent.CompletableFuture;

/**
 * @author liyapu
 * @date 2021-10-30 14:43
 * @desc
 *     使用CompletableFuture
 *     https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 */
public class CompletableFuture01 {

    /**
     * 使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
     *
     * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
     */
    public static void main(String[] args) {
        CompletableFuture01 cf01 = new CompletableFuture01();
        cf01.test01();
    }

    /**
     * CompletableFuture已经被提交给默认的线程池执行了，我们需要定义的是CompletableFuture完成时和异常时需要回调的实例。完成时，CompletableFuture会调用Consumer对象：
     *
     * 可见CompletableFuture的优点是：
     *    异步任务结束时，会自动回调某个对象的方法；
     *    异步任务出错时，会自动回调某个对象的方法；
     *    主线程设置好回调后，不再关心异步任务的执行。
     */
    public void test01()  {
        //创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(this::fetchPrice);

        //如果执行成功
        cf.thenAccept((result) -> {
            System.out.println("price = " + result);
        });

        //如果执行异常
        cf.exceptionally((e) ->{
            System.out.printf("发送异常了");
            e.printStackTrace();
            return null;
        });

        //主线程不要立刻结束，否则 CompletableFuture 默认使用的线程池会立刻关闭
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    Double fetchPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Math.random() < 0.5){
            throw new RuntimeException("fetch price err");
        }
        return 10 + Math.random() * 100;
    }
}
