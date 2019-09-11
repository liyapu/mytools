package com.lyp.learn.dp.pattern.Templatemethod1;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        Account account1 = new DemandAccount("20113333");
        System.out.println(account1.getAccountType() + " 账户利息 : " + account1.calculateInterest());
        System.out.println();

        Account account2 = new FixedAccount("2011555566");
        System.out.println(account2.getAccountType() + " 账户利息 : " + account2.calculateInterest());
    }
}
