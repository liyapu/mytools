package com.lyp.learn.dp.pattern.strategypattern2;

/**
 * 具体策略B
 */
public class ConcreteStrategyB implements Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("-----ConcreteStrategyB------");
    }
}
