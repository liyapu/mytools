package com.lyp.learn.dp.pattern.bridgepattern2;

/**
 * @author liyapu
 * @date 2023-03-12 10:58
 * @description
 */
public class EmailMsgSender implements MsgSender {

    @Override
    public void send(String message) {
        System.out.printf("EmailMsgSender ---" + message);
    }
    // 与TelephoneMsgSender代码结构类似，所以省略...
}