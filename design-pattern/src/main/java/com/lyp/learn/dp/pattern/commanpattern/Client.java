package com.lyp.learn.dp.pattern.commanpattern;

/**
 * 组合模式跟我们之前讲的面向对象设计中的“组合关系（通过组合来组装两个类）”，完全是两码事。这里讲的“组合模式”，主要是用来处理树形结构数据。这里的“数据”，你可以简单理解为一组对象集合，待会我们会详细讲解。
 * 正因为其应用场景的特殊性，数据必须能表示成树形结构，这也导致了这种模式在实际的项目开发中并不那么常用。但是，一旦数据满足树形结构，应用这种模式就能发挥很大的作用，能让代码变得非常简洁。
 * 话不多说，让我们正式开始今天的学习吧！
 * 组合模式的原理与实现
 * 在GoF的《设计模式》一书中，组合模式是这样定义的：
 * Compose objects into tree structure to represent part-whole hierarchies.Composite lets client treat individual objects and compositions of objects uniformly.
 * 翻译成中文就是：将一组对象组织（Compose）成树形结构，以表示一种“部分-整体”的层次结构。组合让客户端（在很多设计模式书籍中，“客户端”代指代码的使用者。）可以统一单个对象和组合对象的处理逻辑。
 * <p>
 * 命令模式的英文翻译是Command Design Pattern。在GoF的《设计模式》一书中，它是这么定义的：
 * The command pattern encapsulates a request as an object, thereby letting us parameterize other objects with different requests, queue or log requests, and support undoable operations.
 * 翻译成中文就是下面这样。为了帮助你理解，我对这个翻译稍微做了补充和解释，也一起放在了下面的括号中。
 * 命令模式将请求（命令）封装为一个对象，这样可以使用不同的请求参数化其他对象（将不同请求依赖注入到其他对象），并且能够支持请求（命令）的排队执行、记录日志、撤销等（附加控制）功能。
 * <p>
 * 客户端对象
 */
public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        Command command = new LightOnCommand(light);
        XiaoAi xiaoAi = new XiaoAi(command);

        System.out.println("小爱帮我开一下灯！");
        xiaoAi.doCommand();

        System.out.println();
        command = new LightOffCommand(light);
        xiaoAi.setCommand(command);
        System.out.println("小爱帮我关一下灯!");
        xiaoAi.doCommand();
    }
}
