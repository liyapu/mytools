package com.lyp.learn.dp.pattern.interpreterpattern;

/**
 * 解释器模式的英文翻译是Interpreter Design Pattern。在GoF的《设计模式》一书中，它是这样定义的：
 * Interpreter pattern is used to defines a grammatical representation for a language and provides an interpreter to deal with this grammar.
 * 翻译成中文就是：解释器模式为某个语言定义它的语法（或者叫文法）表示，并定义一个解释器用来处理这个语法。
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
