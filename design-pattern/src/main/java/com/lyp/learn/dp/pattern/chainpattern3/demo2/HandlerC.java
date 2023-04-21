package com.lyp.learn.dp.pattern.chainpattern3.demo2;

/**
 * @author liyapu
 * @date 2023-04-20 20:15
 * @description
 */
public class HandlerC implements IHandler {

    @Override
    public boolean handle() {
        boolean handled = true;
        //...
        System.out.println("---CC---处理--");
        return handled;
    }
}
