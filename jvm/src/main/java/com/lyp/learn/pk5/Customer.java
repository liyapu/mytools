package com.lyp.learn.pk5;

/**
 *  测试对象实例化的过程
 *     ①加载类元信息
 *     ②为对象分配内存
 *     ③处理并发问题
 *     ④属性的默认初始化(零值初始化)
 *     ⑤设置对象头的信息
 *     ⑥属性的显示初始化、代码块中初始化、构造器中初始化
 *
 *
 *  给对象的属性赋值的操作
 *     ① 属性的默认初始化
 *     ② 显示初始化
 *     ③ 代码块初始化
 *     ④ 构造器中初始化
 */
public class Customer {
    int id = 1001;
    String name;
    Account acct;

    {
        name = "代码块赋值";
    }

    public Customer(){
        acct = new Account();
    }
}

class Account{

}
