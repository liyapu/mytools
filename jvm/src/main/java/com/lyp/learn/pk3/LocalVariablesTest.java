package com.lyp.learn.pk3;

import java.time.LocalDateTime;

/**
 * @author: liyapu
 * @description:  局部变量表
 * @date 2020-05-02 11:36
 *
 *          LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      27     0  args   [Ljava/lang/String;
 *             8      19     1  test   Lcom/lyp/learn/pk3/LocalVariablesTest;
 *            11      16     2   num   I
 *            14      13     3   str   Ljava/lang/String;
 *
 *        Signature 中的 L 表示 引用类型
 *
 *        如果当前帧是由构造方法或者实例方法创建的，
 *        那么该对象引用this将会存放在index为0的slot处，其余的参数按照参数表顺序继续排列
 *
 */
public class LocalVariablesTest {

    private static  int count = 100;

    public LocalVariablesTest(){
        //构造方法中可以使用 this
        System.out.println(this.count);
    }

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        String str = "hello";
        method1();
        test.method2();
        System.out.println("main 方法结束");
    }

    public  static int method1(){
        int sum = 8;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //静态方法中，不可以使用this
        // 因为 this 变量不存在于当前方法的栈帧的局部变量表中
        //System.out.println(this.count);

        return sum;
    }

    public void method2(){
        String s = "hello";
        int i = 9;
        boolean b ;
        Double d = 11.1D;
        Long l = 66L;
        // 实例方法中，可以使用 this
        System.out.println(this.count);

    }
}
