package com.lyp.learn.dp.pattern.Templatemethod1;

/**
 * 定期账户类
 */
public class FixedAccount extends Account {

    public FixedAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    protected String getAccountType() {
        return "定期";
    }

    @Override
    protected double getInterestRate() {
        return 0.015;
    }
}
