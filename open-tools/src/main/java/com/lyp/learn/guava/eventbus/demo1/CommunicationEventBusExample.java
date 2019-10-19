package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.handler.HandleRequestQuery;
import com.lyp.learn.guava.eventbus.demo1.service.QueryService;

public class CommunicationEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        String orderNo = " 201188999";

        //注册handler
        HandleRequestQuery handleRequestQuery = new HandleRequestQuery(eventBus);

        QueryService queryService = new QueryService(eventBus);
        queryService.queryOrderNo(orderNo);

    }
}
