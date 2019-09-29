package com.lyp.learn.guava.base;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

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
}
