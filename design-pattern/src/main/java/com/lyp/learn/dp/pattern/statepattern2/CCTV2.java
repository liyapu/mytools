package com.lyp.learn.dp.pattern.statepattern2;

/**
 * 具体频道
 */
public class CCTV2 implements Channel {
    @Override
    public void display() {
        System.out.println("cctv2 新闻联播");
    }
}
