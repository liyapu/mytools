package com.lyp.learn.dp.pattern.mementopattern1;

/**
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
