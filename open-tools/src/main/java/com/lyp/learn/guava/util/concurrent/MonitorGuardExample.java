package com.lyp.learn.guava.util.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionExample {
    static final LockCondition lc = new LockCondition();
    static final AtomicInteger COUNT = new AtomicInteger(0);

    public static void main(String[] args) {
        //创建3个生产者
        for(int i = 0; i < 3; i++){
            new Thread(()->{
                while (true){
                    int data = COUNT.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + " offer " + data);
                    lc.offer(data);
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
                    int data = lc.take();
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

    static class LockCondition{
         final LinkedList<Integer> queue = new LinkedList<>();
         final Integer MAX = 10;
         private ReentrantLock lock = new ReentrantLock();
         private Condition FULL_CONDITION = lock.newCondition();
         private Condition EMPTY_CONDITION = lock.newCondition();

        //生产数据
        public void offer(Integer num){
            try {
                lock.lock();
                //这里要使用while
                while (queue.size() > MAX){
                    try {
                        FULL_CONDITION.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(num);
                //这里使用signalAll
                //产生数据了，通知空的
                EMPTY_CONDITION.signalAll();
            }finally {
                lock.unlock();
            }
        }

        //拿走，消费数据
        public int take(){
            try {
                lock.lock();
                while(queue.isEmpty()){
                    try {
                        EMPTY_CONDITION.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer first = queue.removeFirst();
                FULL_CONDITION.signalAll();
                return first;
            }finally {
                lock.unlock();
            }
        }

    }
}
