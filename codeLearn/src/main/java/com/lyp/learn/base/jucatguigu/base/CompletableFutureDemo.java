package com.lyp.learn.base.jucatguigu.base;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liyapu
 * @date 2022-06-03 17:17
 * @description
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask<String> futureTask = new FutureTask<>(new MyThread02());

        Thread t1 = new Thread(futureTask);
        t1.start();

        //获取异步结果
        //String result = futureTask.get();

        //指定超时时间，获取异步结果
        //下面设置超时时间，此处会抛异常，下面的代码就不执行了
        String result = futureTask.get(2, TimeUnit.SECONDS);
        System.out.println("从futureTask 获取异步结果 result = " + result);
    }
}

//class MyThread01 implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//}

class MyThread02 implements Callable<String> {

    /**
     * 有返回值，会抛出异常
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println(" myThread02 call come in .....");
        //设置超时时间
        //Thread.sleep(5000);
        return "hello world";
    }
}


