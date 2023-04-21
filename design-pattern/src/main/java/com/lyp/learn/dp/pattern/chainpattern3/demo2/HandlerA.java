package com.lyp.learn.dp.pattern.chainpattern3.demo2;

/**
 * @author liyapu
 * @date 2023-04-20 20:15
 * @description
 */
public class HandlerA implements IHandler {

    @Override
    public boolean handle() {
        boolean handled = false;
        //...
        System.out.println("---AA----未处理-");
        return handled;
    }
}
