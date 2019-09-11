package com.lyp.learn.dp.pattern.statepattern2;

/**
 * 具体频道
 */
public class CCTV3 implements Channel {
    @Override
    public void display() {
        System.out.println("cctv3 中国在线");
    }
}
