package com.lyp.learn.dp.pattern.strategypattern3;

/**
 * @author liyapu
 * @date 2022-08-24 14:11
 * @description 策略接口类
 */
public interface Strategy {

    /**
     * 做运算
     */
    int doOperation(int num1, int num2);
}
