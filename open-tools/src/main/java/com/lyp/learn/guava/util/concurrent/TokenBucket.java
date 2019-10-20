package com.lyp.learn.guava.util.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 令牌桶
 */
public class TokenBucket {
    private AtomicInteger phoneNumbers = new AtomicInteger(0);
    private final  static int DEFAULT_LIMIT =100;
    private RateLimiter rateLimiter = RateLimiter.create(10);
    private final int saleLimit;

    public TokenBucket(){
        this(DEFAULT_LIMIT);
    }

    public TokenBucket(int limit){
        this.saleLimit = limit;
    }

    public int buy(){

        //等待，获取不到就算了 等待20秒，一秒10个，总共可以卖出200个，但是只有100个可卖
        boolean success = rateLimiter.tryAcquire(20, TimeUnit.SECONDS);
        if(success){
            if(phoneNumbers.get() > saleLimit){
                throw new IllegalStateException(" phone sale dong.please wait next time");
            }
            int phoneOrderNo = phoneNumbers.getAndIncrement();
            System.out.println(Thread.currentThread().getName() + " user get phone no: " + phoneOrderNo);
            return phoneOrderNo;
        }else{
            throw  new IllegalStateException("no get phone num");
        }
    }


}
