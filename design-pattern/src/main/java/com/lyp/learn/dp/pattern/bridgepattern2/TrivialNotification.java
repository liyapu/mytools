package com.lyp.learn.dp.pattern.bridgepattern2;

/**
 * @author liyapu
 * @date 2023-03-12 11:04
 * @description
 */
public class TrivialNotification extends Notification {

    public TrivialNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }
    // 与SevereNotification代码结构类似，所以省略...
}