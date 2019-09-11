package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();

        TerminalExpression a = new TerminalExpression();
        TerminalExpression b = new TerminalExpression();
        TerminalExpression c = new TerminalExpression();

        context.add(a,10);
        context.add(b,20);
        context.add(c,30);

        System.out.println(new Substract(new Multiply(new Plus(a,b),c), c).interpreter(context));

    }
}
