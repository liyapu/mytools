package com.lyp.learn.dp.pattern.Templatemethod1;

/**
 * 活期账户类
 */
public class DemandAccount extends Account{

    public DemandAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    protected String getAccountType() {
        return "活期";
    }

    @Override
    protected double getInterestRate() {
        return 0.003D;
    }
}
