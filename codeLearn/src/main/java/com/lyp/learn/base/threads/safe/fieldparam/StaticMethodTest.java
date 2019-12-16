package com.lyp.learn.base.threads.safe.fieldparam;

/**
 * @author: liyapu
 * @description:
 * @date 2019-12-14 16:14
 */
public class StaticMethodTest implements Runnable{

    private String name;
    @Override
    public void run() {
        System.out.println(StaticMethodDemo.getHello(name));
    }

    public StaticMethodTest(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        int threadCount = 300;
        //启动尽量多的线程才能很容易的模拟问题
        for(int i = 0; i <= threadCount; i++){
            String name = String.valueOf(i);
            new Thread(new StaticMethodTest(name)).start();

        }
    }
}

class StaticMethodDemo{

    public static String getHello(String name){
        String hello = "hello, ";
        hello  = hello + name;
        return hello;
    }
}
