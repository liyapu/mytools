package com.lyp.learn.guava.eventbus.demo111;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author: liyapu
 * @description: 事件监听
 * @date 2019-10-16 15:02
 */
public class EventListener {
    private PromoEvent lastPromoMessage = null;
    private DimensionEvent lastDimensionMessage = null;
    private DeadEvent deadEvent = null;

    @Subscribe
    public void listen(PromoEvent event) {
        lastPromoMessage = event;
        System.out.println("~~~~~~~~~~PromoEvent~~~~~~~~~~~~~");
        System.out.println(event.toString());
    }

    @Subscribe
    public void listen(DimensionEvent event) {
        lastDimensionMessage = event;
        System.out.println("----------DimensionEvent---------");
        System.out.println(event.toString());
    }

    @Subscribe
    public void listen(DeadEvent event) {
        deadEvent = event;
        System.out.println("===========DeadEvent=============");
        System.out.println(event.toString());
    }

    public PromoEvent getLastPromoMessage() {
        return lastPromoMessage;
    }

    public DimensionEvent getLastDimensionMessage() {
        return lastDimensionMessage;
    }

    public DeadEvent getDeadEvent() {
        return deadEvent;
    }
}
