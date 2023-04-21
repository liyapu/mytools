package com.lyp.learn.dp.pattern.chainpattern3.demo1;

/**
 * @author liyapu
 * @date 2023-04-20 20:02
 * @description
 */
public class HandlerB extends Handler {

    @Override
    protected boolean doHandle() {
        boolean handled = true;
        //...
        System.out.println("----B---处理-");
        return handled;
    }
}