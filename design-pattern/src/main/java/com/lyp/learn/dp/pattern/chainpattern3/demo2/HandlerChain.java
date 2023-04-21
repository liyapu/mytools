package com.lyp.learn.dp.pattern.chainpattern3.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyapu
 * @date 2023-04-20 20:16
 * @description HandlerChain类用数组而非链表来保存所有的处理器，
 * 并且需要在HandlerChain的handle()函数中，依次调用每个处理器的handle()函数。
 */
public class HandlerChain {

    private List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }


}