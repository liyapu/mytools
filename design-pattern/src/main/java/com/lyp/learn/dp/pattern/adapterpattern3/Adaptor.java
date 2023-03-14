package com.lyp.learn.dp.pattern.adapterpattern3;

import com.lyp.learn.dp.pattern.adapterpattern2.Adaptee;
import com.lyp.learn.dp.pattern.adapterpattern2.ITarget;

/**
 * @author liyapu
 * @date 2023-03-13 09:35
 * @description 对象适配器：基于组合
 */
public class Adaptor implements ITarget {

    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa(); //委托给Adaptee
    }

    @Override
    public void f2() {
        //...重新实现f2()...
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}