package com.lyp.learn.interfaces;

import org.junit.jupiter.api.Test;

/**
 * 如果一个类使用相同的函数签名从多个地方（比如另一个类或接口）继承了方法，通过三条 规则可以进行判断。
 *
 * 1.类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 * 2.如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口，
 *   即如果B继承了A，那么B就比A更加具体。
 * 3. 最后，如果还是无法判断，这时程序员需要显式覆盖超类方法，继承了多个接口的类必须通过显式覆写存在歧义的方法。
 *   一般来说我们会定义一个默认方法，
 *   然后在其中显式选择超类方法，并使用 InterfaceName.super.methodName()的方式手动调用需要的接口默认方法。
 */

public class DefaultMethodDemo {

    /**
     * 继承接口的 默认方法
     */
    @Test
    public void testClassA(){
        A a = new A();
        a.eat();
    }

    /**
     * 重写 接口中的 默认方法
     */
    @Test
    public void testClassAA(){
        AA aa = new AA();
        aa.eat();
    }

    /**
     * 实现多个接口，其中有冲突的 默认方法
     * 显示重写，然后指明调用哪个接口的 默认方法
     */
    @Test
    public void testClassAB(){
        new ClassAB().eat();
    }

    /**
     * 实现一个接口
     * 继承一个父类，父类实现的接口中的默认方法(并且，父类没有重写此默认方法)与本类实现的接口中的默认方法有冲突
     * 显示重写，然后指明调用哪个接口的 默认方法
     */
    @Test
    public void testClassAAA(){
        AAA aaa = new AAA();
        aaa.eat();
    }


    /**
     * 实现一个接口
     * 继承一个父类，父类重写了父类实现接口的默认方法
     * 父类中的实现的默认方法被调用，类的优先级高于 接口
     */
    @Test
    public void testClassAAAA(){
        AAAA aaaa = new AAAA();
        aaaa.eat();
    }

}

class A implements InterfaceA {

}

class AA implements InterfaceA{
    @Override
    public void eat() {
        System.out.println("class AA eat");
    }
}

class ClassAB implements InterfaceA, InterfaceB {

    @Override
    public void eat() {
        InterfaceB.super.eat();
    }
}

class AAA extends A implements InterfaceB{
    @Override
    public void eat() {
        super.eat();
        InterfaceB.super.eat();
    }
}


class AAAA extends AA implements InterfaceB{

}