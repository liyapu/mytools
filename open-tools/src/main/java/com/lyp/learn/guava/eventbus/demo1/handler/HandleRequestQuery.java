package com.lyp.learn.guava.eventbus.demo1.handler;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.lyp.learn.guava.eventbus.demo1.events.Response;

public class HandleRequestQuery {

    private EventBus eventBus;

    public HandleRequestQuery(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void realQueryOrderNo(String orderNo){
        System.out.println("HandleRequestQuery realQueryOrderNo orderNo : " + orderNo);

        Response response = new Response(orderNo,"success query result:name=张三，age=18");
        eventBus.post(response);
    }
}
