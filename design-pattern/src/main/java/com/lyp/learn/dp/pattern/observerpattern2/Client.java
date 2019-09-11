package com.lyp.learn.dp.pattern.observerpattern2;

/**
 * 应用程序
 */
public class Client {
    public static void main(String[] args) {
        //创建一个主题对象(被观察者)
        ConcreteSubject subject = new ConcreteSubject();
        //创建一个观察者
        Observer zhangSan = new User("张三");
        Observer liSi = new User("李四");
        Observer wangWu = new User("王五");

        //登记观察者
        subject.attach(zhangSan);
        subject.attach(liSi);
        subject.attach(wangWu);
        //被观察者边表状态
        subject.updateMessage("aaaa");
        System.out.println("-------------------");

        //移除观察者李四，他就不能收到消息了
        subject.detach(liSi);
        subject.updateMessage("bbbb");
    }
}
