package com.lyp.learn.dp.pattern.chainpattern3.demo3;

/**
 * @author liyapu
 * @date 2023-04-20 20:21
 * @description
 */
public abstract class Handler {

    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public final void handle() {
        doHandle();
        if (successor != null) {
            successor.handle();
        }
    }

    protected abstract void doHandle();
}