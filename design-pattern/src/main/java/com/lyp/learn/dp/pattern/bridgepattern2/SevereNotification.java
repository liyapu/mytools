package com.lyp.learn.dp.pattern.bridgepattern2;

/**
 * @author liyapu
 * @date 2023-03-12 11:02
 * @description
 */
public class SevereNotification extends Notification {

    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}