package com.lyp.learn.dp.pattern.mediatorpattern2;

/**
 *  具体同事(房东)
 */
public class HouseOwner extends Person {
    HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 与中介者联系
    @Override
    public void action(String message) {
        mediator.constact(message,this);
    }

    public void getMessage(String message){
        System.out.println("房东 ; " + name + ",获取信息: "+ message);
    }
}
