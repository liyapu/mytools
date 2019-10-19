package com.lyp.learn.guava.eventbus.demo1.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.lyp.learn.guava.eventbus.demo1.events.Response;

public class QueryService {

    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        //注册QueryService
        eventBus.register(this);
    }


    public void queryOrderNo(String oderNo){
        System.out.println("QueryService queryOrderNo : " + oderNo);
        //自己不查，委托给eventBus查
        eventBus.post(oderNo);
    }

    @Subscribe
    public void getOrderResponse(Response response){
        System.out.println("QueryService getOrderResponse response :"  + response);
    }
}
