package com.lyp.learn.dp.pattern.visitorpattern2;

/**
 *  访问者接口
 */
public interface AccountBookView {
    // 查看消费的单子
    void view(ConsumerBill consumerBill);

    // 查看收入单子
    void view(IncomeBill incomeBill);
}
