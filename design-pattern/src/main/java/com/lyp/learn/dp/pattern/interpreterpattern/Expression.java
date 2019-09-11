package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 *  抽象表达式
 */
public interface Expression {

    //一定会有解释方法
    int interpreter(Context context);

}
