package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 *  终结符表达式
 */
public class TerminalExpression implements Expression {

    public TerminalExpression(){
    }

    @Override
    public int interpreter(Context context) {
        return context.get(this);
    }
}
