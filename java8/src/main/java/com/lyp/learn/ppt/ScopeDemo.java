package com.lyp.learn.ppt;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 作用域
 */
public class ScopeDemo {
   final String name = "java language ";
   String message = " info";

    @Test
    public void test1(){
        int num = 1;
        Consumer<String> consumer1 = (param) -> System.out.println(param + name + message + num);
        consumer1.accept("hello ");

        message = "information";
        System.out.println(message);

        //Error:从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
        //num = 100;

        //Error:已在方法 test1()中定义了变量 num
        //Variable 'num' is already defined in the score
        //Consumer<Integer> consumer2 = (num) -> System.out.println(num);
    }

    /**
     * 匿名内部类是一个类，也就是说它自己引入了一个作用域，你可以在里面定义变量。
     *
     * lambda表达式没有自己的作用域，它不会从超类（supertype）中继承任何变量名，也不会引入一个新的作用域。
     * lambda表达式基于词法作用域，
     * 也就是说lambda表达式函数体里面的变量和它外部环境的变量具有相同的语义（也包括lambda表达式的形式参数）
     */
    @Test
    public void test2(){
        int num = 1;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int num = 100;  //可以定义这个变量
                System.out.println("匿名内部类 num :" + num);
            }
        };
        new Thread(r1).start();


        Runnable r2 = () -> {
           // int num = 1000; //Variable 'num' is already defined in the score
            System.out.println(num);
        };
        new Thread(r2).start();
    }

    /**
     * 不同的作用域规则对于this和super关键字有不同的效果，
     *
     * 在匿名类中this表示匿名类对象本身的引用，super表示匿名类的父类。
     *
     * 在lambda表达式this和super关键字意思和外部域中this和super的意思一样，
     * this一般是包含它的那个对象，super表示包含它的类的父类。
     */

    @Test
    public void test3(){
        new HelloAnonymous().r1.run();
        new HelloAnonymous().r2.run();
    }

    public class HelloAnonymous {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(toString());
            }
        };

        public String toString(){
            return "Hello, world anonymous";
        }
    }

    @Test
    public void test4(){
        new HelloLambda().r1.run();
        new HelloLambda().r2.run();
    }
    public class HelloLambda {
        Runnable r1 = () -> { System.out.println(this); };
        Runnable r2 = () -> { System.out.println(toString()); };

        public String toString(){
            return "Hello, world lambda";
        }
    }
}
