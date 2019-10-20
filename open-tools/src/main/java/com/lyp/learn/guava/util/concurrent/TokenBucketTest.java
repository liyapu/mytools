package com.lyp.learn.guava.util.concurrent;

import java.util.stream.IntStream;

public class TokenBucketTest {
    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket();

        IntStream.rangeClosed(0,200)
                .forEach(i ->new Thread(()->{
                    tokenBucket.buy();
                }).start());
    }
}
