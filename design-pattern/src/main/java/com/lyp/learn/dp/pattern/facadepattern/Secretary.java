package com.lyp.learn.dp.pattern.facadepattern;

/**
 * 门面类
 * 秘书类
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
