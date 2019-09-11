package com.lyp.learn.dp.pattern.visitorpattern1;

/**
 * 抽象访问者
 */
public interface Visitor {
    //可以访问哪些元素
    void visit(ConcreteElementA cea);

    void visit(ConcreteElementB ceb);
}
