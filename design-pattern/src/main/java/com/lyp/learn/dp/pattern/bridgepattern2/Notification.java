package com.lyp.learn.dp.pattern.bridgepattern2;

/**
 * @author liyapu
 * @date 2023-03-12 11:01
 * @description
 */
public abstract class Notification {

    protected MsgSender msgSender;

    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);
}
