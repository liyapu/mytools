package com.lyp.learn.dp.pattern.strategypattern3;

/**
 * @author liyapu
 * @date 2022-08-24 14:13
 * @description 使用了某种策略的类
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
