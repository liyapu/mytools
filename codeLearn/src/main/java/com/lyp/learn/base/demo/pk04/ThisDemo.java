package com.lyp.learn.base.demo.pk04;

public class ThisDemo {

    String str = "这是成员变量";

    public void fun(String str){
        System.out.println(str);
        System.out.println(this.str);
        this.str = str;
        System.out.println(this.str);
    }

    public void play(String msg){
        System.out.println(msg);
        System.out.println(str);
    }


    public static void main(String[] args) {
        ThisDemo td = new ThisDemo();
        td.fun("这是局部变量");
        System.out.println("------------");
        td.play("局部消息");
    }


}
