package com.lyp.learn.base.jucatguigu.cf01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liyapu
 * @date 2022-06-12 15:00
 * @description
 */
public class FutureApiDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "task1";
        });

        Thread t1 = new Thread(futureTask1);
        t1.start();

        System.out.println("主线程----" + Thread.currentThread().getName() + " 做事情 1 ..........");
        String taskResult = futureTask1.get();
        System.out.println("taskResult === " + taskResult);

        //由于上面调用了futureTask1 的get方法，阻塞获取结果，这里会一直阻塞，直到拿到结果，所以一般都会把 get方法放到最后再 调用
        // 下面的 代码不会去执行,直到上面拿到结果之后才会执行
        System.out.println("主线程----" + Thread.currentThread().getName() + " 做事情 2 ..........");
    }

}
