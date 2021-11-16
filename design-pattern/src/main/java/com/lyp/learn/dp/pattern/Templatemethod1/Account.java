package com.lyp.learn.dp.pattern.Templatemethod1;

/**
 * 抽象模板，抽象账户类
 */
public abstract class Account {
    //账号
    private String accountNumber;
    
    public Account(String accountNumber){
        this.accountNumber = accountNumber;
    }

    /**
     * 模板方法，计算账号利息
     * @return
     */
    public double calculateInterest(){
        String accountType = getAccountType();
        double interestRate = getInterestRate();
        double account = getAccount(accountType,accountNumber);
        return account * interestRate;
    }

    //基本方法，确定账户类型，留给子类实现
    protected abstract String getAccountType();

    //基本方法，确定利息，留个子类去实现
    protected abstract double getInterestRate();

    //基本方法，根据账号类型和账号确定账户金额
    protected  double getAccount(String accountType, String accountNumber){
        //访问数据库....
        return 1000;
    }
}
