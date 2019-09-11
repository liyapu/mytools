package com.lyp.learn.dp.pattern.visitorpattern2;

/**
 * 老板类：访问者是老板，主要查看总支出和总收入
 */
public class Boss implements AccountBookView {
    private double totalConsumer;
    private double totalIncome;

    // 查看消费的单子
    @Override
    public void view(ConsumerBill consumerBill) {
        totalConsumer = totalConsumer + consumerBill.getAmount();
    }

    // 查看收入单子
    @Override
    public void view(IncomeBill incomeBill) {
        totalIncome = totalIncome + incomeBill.getAmount();
    }

    public void getTotalConsumer() {
        System.out.println("老板一共消费了" + totalConsumer);
    }

    public void getTotalIncome() {
        System.out.println("老板一共收入了" + totalIncome);
    }
}
