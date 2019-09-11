package com.lyp.learn.dp.rule;

class Someone{

    public void call(Friend friend){
        friend.forward();
    }
}

class Friend{
    //朋友持有陌生人对象
    Stranger stranger = new Stranger();

    public void forward(){
        System.out.println("friend 中间转述.....");
        stranger.strangerMethods();
    }

    public void friendMethod(){
        System.out.println("friend 自己的方法");
    }
}

class Stranger{
    public void strangerMethods(){
        System.out.println("Stranger的方法.....");
    }
}


public class TestLoD {
    public static void main(String[] args) {
        Friend friend = new Friend();
        Someone someone = new Someone();
        someone.call(friend);

    }
}
