package com.lyp.learn.dp.pattern.visitorpattern2;

/**
 * 会计类：访问者是会计，主要记录每笔单子
 */
public class CPA implements AccountBookView {
    int consumeCount = 0;
    int incomeCount = 0;

    // 查看消费的单子
    @Override
    public void view(ConsumerBill consumerBill) {
        consumeCount++;
        if (consumerBill.getItem().equals("消费")) {
            System.out.println("第" + consumeCount + "个单子消费了：" + consumerBill.getAmount());
        }
    }

    // 查看收入单子
    @Override
    public void view(IncomeBill incomeBill) {
        incomeCount++;
        if (incomeBill.getItem().equals("收入")) {
            System.out.println("第" + incomeCount + "个单子收入了：" + incomeBill.getAmount());
        }
    }

}
