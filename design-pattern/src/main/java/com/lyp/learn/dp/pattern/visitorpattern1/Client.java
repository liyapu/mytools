package com.lyp.learn.dp.pattern.visitorpattern1;

/**
 *
 * 应用程序
 */
public class Client {
    public static void main(String[] args) {
        //创建一个访问者
        Visitor visitor = new ConcreteVisitor();

        //创建一个结构对象
        ObjectStructure os = new ObjectStructure();
        os.createElements();
        os.action(visitor);
    }
}
