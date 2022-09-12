package com.lyp.learn.base.jucatguigu.cf01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liyapu
 * @date 2022-06-12 15:00
 * @description
 */
public class FutureApiDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "task1";
        });

        Thread t1 = new Thread(futureTask1);
        t1.start();

        System.out.println("主线程----" + Thread.currentThread().getName() + " 做事情 1 ..........");
        //可以拿到结果
        //String taskResult = futureTask1.get(6, TimeUnit.SECONDS);

        //阻塞3s,小于子任务的执行时间，会报超时异常，拿不到结果
        String taskResult = futureTask1.get(3, TimeUnit.SECONDS);
        System.out.println("taskResult === " + taskResult);

        //由于上面调用了futureTask1 的get方法，阻塞获取结果，这里会一直阻塞，直到拿到结果，
        // 下面的 代码不会去执行,直到上面拿到结果之后才会执行
        System.out.println("主线程----" + Thread.currentThread().getName() + " 做事情 2 ..........");
    }

    /**
     * 结论：
     * 1.get容易导致阻塞，一般建议放到程序后面，一旦调用不见不散，非要等到结果才会离开，不管你是否计算完成，容易程序阻塞
     * 2. 假如我不愿意等待很长时间，希望过时不候,可以自动离开，可以调用 带超时时间的 get方法
     */
}
