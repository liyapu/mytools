package com.lyp.learn.dp.pattern.proxypattern2;

/**
 * @author liyapu
 * @date 2023-03-11 21:49
 * @description 实际上，Spring AOP底层的实现原理就是基于动态代理。用户配置好需要给哪些类创建代理，并定义好在执行原始类的业务代码前后执行哪些附加功能。
 * Spring为这些类创建动态代理对象，并在JVM中替代原始类对象。原本在代码中执行的原始类的方法，被换作执行代理类的方法，也就实现了给原始类添加附加功能的目的。
 */
public class Client {

    public static void main(String[] args) {
        //MetricsCollectorProxy使用举例
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());

    }

}
