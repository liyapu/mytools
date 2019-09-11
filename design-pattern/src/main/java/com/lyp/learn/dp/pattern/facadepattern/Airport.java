package com.lyp.learn.dp.pattern.facadepattern;

/**
 * 机场类
 */
public class Airport {
    public void bookTicket(String from,String to){
        System.out.println("订购一张从 " + from + " 到 " + to + " 的机票" );
    }
}
