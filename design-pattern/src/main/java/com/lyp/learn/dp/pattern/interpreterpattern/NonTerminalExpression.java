package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 * 非终结符表达式
 */
public abstract class NonTerminalExpression implements Expression {
    Expression e1,e2;

    public NonTerminalExpression(Expression e1,Expression e2){
        this.e1 = e1;
        this.e2 = e2;
    }
}
