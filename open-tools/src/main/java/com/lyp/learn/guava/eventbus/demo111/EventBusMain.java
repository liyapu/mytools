package com.lyp.learn.guava.eventbus.demo111;

import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-16 15:03
 */
public class EventBusMain {
    //我们声明了两个Bus总线，用来分别存放消息体：
    static final EventBus LINE_1St = new EventBus("first");
    static AsyncEventBus LINE_2Ed = new AsyncEventBus("second", new Executor() {
        @Override
        public void execute(Runnable command) {
            try {
                Thread.sleep(10000L);
                command.run();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    });

    public static void main(String[] args) {
        LINE_1St.register(new EventListener());
        LINE_2Ed.register(new EventListener());

        int cpuNums = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cpuNums * 1);

        //然后声明3个线程，分别往同步总线里面放一个消息；延迟5秒往同步线程里面放一个无效消息；然后向延迟10秒的异步线程里面放一条消息；
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                PromoEvent promoEvent = genPromoEvent();
                LINE_1St.post(promoEvent);
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                LINE_2Ed.post(genDimensionEvent());
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    LINE_1St.post(111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
    }

    static PromoEvent genPromoEvent() {
        PromoEvent event = new PromoEvent(1, Lists.newArrayList(1L, 2L, 3L), System.currentTimeMillis(), 0L);
        return event;
    }

    static DimensionEvent genDimensionEvent() {
        DimensionEvent event = new DimensionEvent(100, Lists.newArrayList(100L, 200L, 300L), System.currentTimeMillis(), 0L);
        return event;
    }
}
