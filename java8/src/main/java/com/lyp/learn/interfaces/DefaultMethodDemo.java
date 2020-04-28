package com.lyp.learn.interfaces;

import org.junit.jupiter.api.Test;

/**
 *  Java 8中，你可以为接口添加静态方法和默认方法。从技术角度来说，这是完全合法的，
 *  只是它看起来违反了接口作为一个抽象定义的理念。
 *
 *   静态方法：
 *      使用 static 关键字修饰。可以通过接口直接调用静态方法，并执行其方法体。
 *      我们经常在相互一起使用的类中使用静态方法。你可以在标准库中找到像Collection/Collections或者Path/Paths这样成对的接口和类。
 *
 *   默认方法：
 *      默认方法使用 default 关键字修饰。可以通过实现类对象来调用。
 *      我们在已有的接口中提供新方法的同时，还保持了与旧版本代码的兼容性。
 *      比如：java 8 API中对Collection、List、Comparator等接口提供了丰富的默认方法。
 *
 *
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