package com.lyp.learn.guava.util.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MonitorGuardExample {
    static final MonitorGuard mg = new MonitorGuard();
    static final AtomicInteger COUNT = new AtomicInteger(0);

    public static void main(String[] args) {
        //创建3个生产者
        for(int i = 0; i < 3; i++){
            new Thread(()->{
                while (true){
                    int data = COUNT.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " offer " + data);
                    mg.offer(data);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        //创建2个生产者
        for(int i = 0; i < 2; i++){
            new Thread(()->{
                while (true){
                    int data = mg.take();
                    System.out.println(Thread.currentThread().getName() + " take " + data);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    static class MonitorGuard{
         final LinkedList<Integer> queue = new LinkedList<>();
         final Integer MAX = 10;

         private Monitor monitor = new Monitor();
         private Monitor.Guard canOffer = monitor.newGuard(() -> queue.size() < MAX);
         private Monitor.Guard canTake = monitor.newGuard(() -> !queue.isEmpty());

        //生产数据
        public void offer(Integer num){
            try {
                monitor.enterWhen(canOffer);
                queue.addLast(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
        }

        //拿走，消费数据
        public int take(){
            try {
                monitor.enterWhen(canTake);
                Integer first = queue.removeFirst();
                return first;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                monitor.leave();
            }
        }

    }
}
