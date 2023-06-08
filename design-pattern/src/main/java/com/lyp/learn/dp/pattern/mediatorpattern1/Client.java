package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 * 中介模式的英文翻译是Mediator Design Pattern。在GoF中的《设计模式》一书中，它是这样定义的：
 * Mediator pattern defines a separate (mediator) object that encapsulates the interaction between a set of objects and the objects delegate their interaction to a mediator object instead of interacting with each other directly.
 * 翻译成中文就是：中介模式定义了一个单独的（中介）对象，来封装一组对象之间的交互。将这组对象之间的交互委派给与中介对象交互，来避免对象之间的直接交互。
 * <p>
 * 实际上，中介模式的设计思想跟中间层很像，通过引入中介这个中间层，将一组对象之间的交互关系（或者说依赖关系）从多对多（网状关系）转换为一对多（星状关系）。
 * 原来一个对象要跟n个对象交互，现在只需要跟一个中介对象交互，从而最小化对象之间的交互关系，降低了代码的复杂度，提高了代码的可读性和可维护性。
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        mediator.createConcreteMediator();

        Colleague colleague1 = new ConcreteColleague1(mediator);
        mediator.colleagueChanged(colleague1);
    }
}
