package com.lyp.learn.guava.base;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * StopWatch 用来计算经过的时间（精确到纳秒）
 *
 */
public class StopwatchTest {

    @Test
    public void test01() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(stopwatch.stop());
    }

    @Test
    public void test02() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(stopwatch.stop());
    }

    /**
     *
     * reset
     * 重复利用
     */
    @Test
    public void test03() throws InterruptedException {
        //创建自动start的计时器
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("code execute spend time " + stopwatch.stop());

        stopwatch.reset();
        stopwatch.start();
        TimeUnit.MILLISECONDS.sleep(50);
        System.out.println("process spend time " + stopwatch.stop());
    }


    @Test
    public void test2() throws Exception {
        // 创建stopwatch并开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1980);

        // 以秒打印从计时开始至现在的所用时间，向下取整
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 停止计时
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 再次计时
        stopwatch.start();
        Thread.sleep(100);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 2

        // 重置并开始
        stopwatch.reset().start();
        Thread.sleep(1030);

        // 检查是否运行
        System.out.println(stopwatch.isRunning()); // true
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1034
        System.out.println(millis);

        // 打印
        System.out.println(stopwatch.toString()); // 1.034 s
    }
}
