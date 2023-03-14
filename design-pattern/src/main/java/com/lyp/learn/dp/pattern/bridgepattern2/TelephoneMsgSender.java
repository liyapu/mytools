package com.lyp.learn.dp.pattern.bridgepattern2;

import java.util.List;

/**
 * @author liyapu
 * @date 2023-03-12 10:57
 * @description
 */
public class TelephoneMsgSender implements MsgSender {

    private List telephones;

    public TelephoneMsgSender(List telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        //...
    }
}
