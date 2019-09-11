package com.lyp.learn.dp.pattern.visitorpattern1;

/**
 *  具体元素A
 */
public class ConcreteElementA extends Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    //业务逻辑方法
    public void operation(){
        System.out.println("访问元素A");
    }
}
