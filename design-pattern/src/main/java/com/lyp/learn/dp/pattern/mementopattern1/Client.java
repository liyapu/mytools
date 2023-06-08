package com.lyp.learn.dp.pattern.mementopattern1;

/**
 * 备忘录模式，也叫快照（Snapshot）模式，英文翻译是Memento Design Pattern。在GoF的《设计模式》一书中，备忘录模式是这么定义的：
 * Captures and externalizes an object’s internal state so that it can be restored later, all without violating encapsulation.
 * 翻译成中文就是：在不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态。
 * <p>
 * 应用程序
 */
public class Client {
    public static void main(String[] args) {
        //定义发起人
        Originator originator = new Originator();
        originator.setState("a");
        originator.dispaly();
        System.out.println();

        //定义负责人
        Caretaker caretaker = new Caretaker();

        //创建一个备忘录
        caretaker.setMemento(originator.createMemento());
        //恢复一个备忘录
        originator.restoreMemento(caretaker.getMemento());
        originator.dispaly();





    }
}
