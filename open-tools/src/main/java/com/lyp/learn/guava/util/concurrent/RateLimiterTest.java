package com.lyp.learn.guava.util.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class RateLimiterTest {
    //两秒一个
    private final static RateLimiter limiter = RateLimiter.create(0.5D);

    //同一个时刻最多可以有3个访问
    private final static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("count = " + count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
//        IntStream.rangeClosed(1,10)
//                .forEach(i -> executorService.submit(RateLimiterTest::testLimiter));

        IntStream.rangeClosed(1,10)
                .forEach(i -> executorService.submit(RateLimiterTest::testSemaphore));

        executorService.shutdown();
    }
    private static void testLimiter(){
        System.out.println(Thread.currentThread() + " waiting " + limiter.acquire());
    }


    /**
     * 首先通过RateLimiter.create(1);创建一个限流器，参数代表每秒生成的令牌数，
     * 通过limiter.acquire(i) 来以阻塞的方式获取令牌，
     *
     * 当然也可以通过tryAcquire(int permits, long timeout, TimeUnit unit)来设置等待超时时间的方式获取令牌，
     * 如果超timeout为0，则代表非阻塞，获取不到立即返回。
     */
    @Test
    public void testAcquire() {
        //1秒1个
        RateLimiter limiter = RateLimiter.create(1D);
        for (int i = 1; i <= 10; i++) {
            double waitTime = limiter.acquire(i);
            System.out.println("curTime:" + System.currentTimeMillis() + " acq :" + i + " waitTime :" + waitTime);
        }
    }

    /**
     * RateLimiter还有个tryAcquire方法，如果令牌够会立即返回true，否则立即返回false
     */
    @Test
    public void testTryAcquire(){
        RateLimiter limiter = RateLimiter.create(1D);
        for(int i = 1; i <= 100 ;i++){
            boolean tryAcquire = limiter.tryAcquire();
            System.out.println("curTime：" + LocalDateTime.now() + ", tryAcquire = " + tryAcquire);
        }
    }

    @Test
    public void testTryAcquire2(){
        RateLimiter limiter = RateLimiter.create(1D);
        for(int i = 1; i <= 100 ;i++){
            boolean tryAcquire = limiter.tryAcquire(i);
            System.out.println("curTime：" + LocalDateTime.now() + ", tryAcquire = " + tryAcquire);
        }
    }


    /**
     * 测试信号量
     */
    public static void testSemaphore() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is coming do something  ...");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " leaves");
        }
    }
}
