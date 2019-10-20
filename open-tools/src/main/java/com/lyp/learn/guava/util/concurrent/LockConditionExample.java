package com.lyp.learn.guava.util.concurrent;

import org.apache.commons.math3.analysis.function.Max;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedExample {
    static final Synchronized sync = new Synchronized();
    static final AtomicInteger COUNT = new AtomicInteger(0);

    public static void main(String[] args) {
        //创建3个生产者
        for(int i = 0; i < 3; i++){
            new Thread(()->{
                while (true){
                    int data = COUNT.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " offer " + data);
                    sync.offer(data);
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
                    int data = sync.take();
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

    static class Synchronized{
         final LinkedList<Integer> queue = new LinkedList<>();
         final Integer MAX = 10;
        //生产数据
        public void offer(Integer num){
            synchronized (queue){
                //这里要使用while
                while (queue.size() > MAX){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(num);
                queue.notifyAll();
            }
        }

        //拿走，消费数据
        public int take(){
            synchronized (queue){
                while (queue.isEmpty()){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer first = queue.removeFirst();
                queue.notifyAll();
                return first;
            }
        }

    }
}
