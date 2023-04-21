package com.lyp.learn.dp.pattern.chainpattern3.demo1;

/**
 * @author liyapu
 * @date 2023-04-20 20:02
 * @description
 */
public class HandlerA extends Handler {

    @Override
    protected boolean doHandle() {
        boolean handled = false;
        //...
        System.out.println("----A- 未处理---");
        return handled;
    }
}