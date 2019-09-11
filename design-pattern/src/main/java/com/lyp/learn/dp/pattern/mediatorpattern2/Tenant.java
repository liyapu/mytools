package com.lyp.learn.dp.pattern.mediatorpattern2;

/**
 * 具体同事 (房客)
 */
public class Tenant extends Person{

    Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    public void action(String message) {
        mediator.constact(message,this);
    }

    public void getMessage(String message){
        System.out.println("租房者:" + name +",获得信息：" + message);
    }
}
