package com.lyp.learn.dp.pattern.adapterpattern1;

/**
 * 适配器
 *  实现 Target: 让其拥有 request 方法
 *  继承Adaptee: 让其可以调用 super 父类的方法
 */
public class Adapter extends Adaptee implements Target{

    @Override
    public void request() {
        super.specificRequest();
    }
}
