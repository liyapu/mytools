package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 *  减法解释器
 */
public class Substract extends NonTerminalExpression {

    public Substract(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int interpreter(Context context) {
        return e1.interpreter(context) - e2.interpreter(context);
    }
}
