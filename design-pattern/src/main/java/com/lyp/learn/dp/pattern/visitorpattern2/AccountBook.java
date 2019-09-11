package com.lyp.learn.dp.pattern.visitorpattern2;

import java.util.ArrayList;
import java.util.List;

/**
 * 账单类：用于添加账单，和为每一个账单添加访问者
 */
public class AccountBook {
    private List<Bill> listBill = new ArrayList<Bill>();

    // 添加单子
    public void add(Bill bill) {
        listBill.add(bill);
    }

    // 为每个账单添加访问者
    public void show(AccountBookView viewer) {
        for (Bill b : listBill) {
            b.accept(viewer);
        }
    }
}
