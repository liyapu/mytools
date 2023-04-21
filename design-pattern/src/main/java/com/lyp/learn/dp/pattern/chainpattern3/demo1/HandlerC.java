package com.lyp.learn.dp.pattern.chainpattern3.demo1;

/**
 * @author liyapu
 * @date 2023-04-20 20:02
 * @description
 */
public class HandlerC extends Handler {

    @Override
    protected boolean doHandle() {
        boolean handled = false;
        //...
        System.out.println("----C--未处理--");
        return handled;
    }
}