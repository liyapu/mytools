package com.lyp.learn.dp.pattern.strategypattern2;

/**
 * 具体策略A
 */
public class ConcreteStrategyA implements Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("-----ConcreteStrategyA------");
    }
}
