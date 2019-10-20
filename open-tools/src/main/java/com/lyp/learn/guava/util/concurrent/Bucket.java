package com.lyp.learn.guava.util.concurrent;

import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 漏桶算法
 */
public class Bucket {
    private  final ConcurrentLinkedQueue<Integer> container = new ConcurrentLinkedQueue<>();
    //桶中最多能有多少个
    private static final int BUCKET_LIMIT = 100;

    //1秒处理10个
    private  final RateLimiter limiter = RateLimiter.create(100);

    private  Monitor offerMonitor = new Monitor();
    private  Monitor takeMonitor = new Monitor();

    /**
     * 放数据
     * @param data
     */
    public void submit(Integer data){
        if(offerMonitor.enterIf(offerMonitor.newGuard(() -> container.size() < BUCKET_LIMIT))){
            try {
                System.out.println(Thread.currentThread().getName() + " receive data :" + data + " , size is:" + container.size());
                container.offer(data);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                offerMonitor.leave();
            }
        }else{
            //桶已经满了，此处进行降级处理 1. 保存到数据库，消息队列 2. 抛出异常，让它下次再提交
            throw  new IllegalStateException("bucket is full");
        }
    }

    /**
     * 消费数据
     */
    public void take(){
        if(takeMonitor.enterIf(takeMonitor.newGuard(() -> !container.isEmpty()))){
            try {
                Integer data = container.poll();
                System.out.println(Thread.currentThread().getName() + " take data--------------- :" + data);
            } finally {
                takeMonitor.leave();
            }
        }
    }
}
