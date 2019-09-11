package com.lyp.learn.dp.pattern.strategypattern2;

/**
 * 环境策略类
 */
public class Context {
    private Strategy strategy;

    //设置具体策略角色，动态指定具体策略角色
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        strategy.algorithmInterface();
    }
}
