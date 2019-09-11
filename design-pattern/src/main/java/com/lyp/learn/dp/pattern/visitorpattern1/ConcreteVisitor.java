package com.lyp.learn.dp.pattern.visitorpattern1;

/**
 *  具体访问者
 */
public class ConcreteVisitor implements Visitor {
    @Override
    public void visit(ConcreteElementA cea) {
        cea.operation();
    }

    @Override
    public void visit(ConcreteElementB ceb) {
        ceb.operation();
    }
}
