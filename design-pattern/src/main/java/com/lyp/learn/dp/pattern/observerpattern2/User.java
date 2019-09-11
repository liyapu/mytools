package com.lyp.learn.dp.pattern.observerpattern2;

/**
 * 具体观察者
 */
public class User implements Observer {

    private String name;

    private String message;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read(){
        System.out.println(name + " 收到消息: " + message);
    }
}
