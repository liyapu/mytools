package com.lyp.learn.dp.pattern.statepattern2;

/**
 * 具体频道
 */
public class CCTV1 implements Channel {
    @Override
    public void display() {
        System.out.println("cctv1 人与自然");
    }
}
