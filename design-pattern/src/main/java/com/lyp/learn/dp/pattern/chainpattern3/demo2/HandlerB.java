package com.lyp.learn.dp.pattern.chainpattern3.demo2;

/**
 * @author liyapu
 * @date 2023-04-20 20:15
 * @description
 */
public class HandlerB implements IHandler {

    @Override
    public boolean handle() {
        boolean handled = false;
        //...
        System.out.println("---BB---未处理--");
        return handled;
    }
}
