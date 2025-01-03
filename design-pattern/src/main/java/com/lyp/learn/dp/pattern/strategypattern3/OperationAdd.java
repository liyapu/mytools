package com.lyp.learn.dp.pattern.strategypattern3;

/**
 * @author liyapu
 * @date 2022-08-24 14:11
 * @description 加法策略
 */
public class OperationAdd implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
