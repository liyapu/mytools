package com.lyp.learn.base.pk02;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-10 22:38
 */
public class BooleanOperator {
    public static void main(String[] args) {
        boolean trueValue = true; //声明boolean变量trueValue,并赋值为true
        boolean falseValue = false;//声明boolean变量falseValue,并赋值为false

        System.out.println("!trueValue 是 " + (!trueValue));
        System.out.println("!falseValue 是 " + (!falseValue));
        System.out.println("---------------------------------");//输出分割线
        System.out.println("trueValue && trueValue 是 " + (trueValue && trueValue));
        System.out.println("trueValue && falseValue 是 " + (trueValue && falseValue));
        System.out.println("falseValue && trueValue 是 " + (falseValue && trueValue));
        System.out.println("falseValue && falseValue 是 " + (falseValue && falseValue));
        System.out.println("---------------------------------");//输出分割线
        System.out.println("trueValue || trueValue 是 " + (trueValue || trueValue));
        System.out.println("trueValue || falseValue 是 " + (trueValue || falseValue));
        System.out.println("falseValue || trueValue 是 " + (falseValue || trueValue));
        System.out.println("falseValue || falseValue 是 " + (falseValue || falseValue));
    }
}
