package com.lyp.learn.dp.pattern.visitorpattern2;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        // 创建消费和收入单子
        Bill consumerBill = new ConsumerBill("消费", 3000);
        Bill incomeBill = new IncomeBill("收入", 5000);
        Bill consumerBill2 = new ConsumerBill("消费", 4000);
        Bill incomeBill2 = new IncomeBill("收入", 8000);
        Bill incomeBill3 = new IncomeBill("收入", 6000);
        Bill incomeBill4 = new IncomeBill("收入", 1000);
        Bill incomeBill5 = new IncomeBill("收入", 2000);
        // 添加单子
        AccountBook accountBook = new AccountBook();
        accountBook.add(consumerBill);
        accountBook.add(incomeBill);
        accountBook.add(consumerBill2);
        accountBook.add(incomeBill2);
        accountBook.add(incomeBill3);
        accountBook.add(incomeBill4);
        accountBook.add(incomeBill5);
        // 创建访问者
        Boss boss = new Boss();
        CPA cpa = new CPA();

        // 接受访问者
        accountBook.show(cpa);
        System.out.println();
        System.out.println("--------");


        // boss查看总收入和总消费
        accountBook.show(boss);
        boss.getTotalConsumer();
        boss.getTotalIncome();

    }
}
