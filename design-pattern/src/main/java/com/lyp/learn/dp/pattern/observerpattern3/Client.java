package com.lyp.learn.dp.pattern.observerpattern3;

/**
 * @author liyapu
 * @date 2021-08-05 17:59
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        OderStatusChangeObserverForCreateIndex createIndexObserver = new OderStatusChangeObserverForCreateIndex();
        OderStatusChangeObserverForSendMsg sendMsgObserver = new OderStatusChangeObserverForSendMsg();

        OrderStatus.CREATE_SUCCESS.register(createIndexObserver);

        OrderStatus.REFUND_SUCCESS.register(createIndexObserver);
        OrderStatus.REFUND_SUCCESS.register(sendMsgObserver);

        CreateProcessWorkFlow createProcessWorkFlow = new CreateProcessWorkFlow();
        createProcessWorkFlow.createOrder(new Object());

        System.out.println("--------------------");

        RefundProcessWorkFlow refundProcessWorkFlow = new RefundProcessWorkFlow();
        refundProcessWorkFlow.refund(new Object());

    }

}
