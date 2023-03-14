package com.lyp.learn.dp.pattern.bridgepattern2;

/**
 * @author liyapu
 * @date 2023-03-12 11:03
 * @description
 */
public class UrgencyNotification extends Notification {

    public UrgencyNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }
    // 与SevereNotification代码结构类似，所以省略...
}