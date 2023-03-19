package com.lyp.learn.dp.pattern.facadepattern;

/**
 * 门面类
 * 秘书类
 * <p>
 * 门面模式，也叫外观模式，英文全称是Facade Design Pattern。在GoF的《设计模式》一书中，门面模式是这样定义的：
 * Provide a unified interface to a set of interfaces in a subsystem. Facade Pattern defines a higher-level interface that makes the subsystem easier to use.
 * 翻译成中文就是：门面模式为子系统提供一组统一的接口，定义一组高层接口让子系统更易
 * <p>
 * 这个定义很简洁，我再进一步解释一下。
 * 假设有一个系统A，提供了a、b、c、d四个接口。系统B完成某个业务功能，需要调用A系统的a、b、d接口。利用门面模式，我们提供一个包裹a、b、d接口调用的门面接口x，给系统B直接使用。
 */
public class Secretary {
    //机场类
    private Airport  airport = new Airport();
    //司机类
    private Chauffer chauffer = new Chauffer();
    //酒店类
    private Restaurant restaurant = new Restaurant();
    //宾馆类
    private Hotel hotel = new Hotel();

    /**
     * 安排出差
     * @param from
     * @param to
     * @param days
     */
    public void trip(String from,String to,int days){
        airport.bookTicket(from,to);
        hotel.bookRoom(days);
        chauffer.driver("机场");
    }

    /**
     * 安排饭店
     * @param num
     */
    public void repast(int num){
        restaurant.reserve(num);
        chauffer.driver("酒店");
    }
}
