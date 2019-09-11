package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 *  除法解释器
 */
public class Division extends NonTerminalExpression {

    public Division(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int interpreter(Context context) {
        return e1.interpreter(context) / e2.interpreter(context);
    }
}
