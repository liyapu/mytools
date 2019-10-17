package com.lyp.learn.dp.pattern.decorator;

public abstract class Decorator implements Component {
    //装饰角色持有构件角色
    private Component component = null;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation(){
        component.operation();
    }


}
