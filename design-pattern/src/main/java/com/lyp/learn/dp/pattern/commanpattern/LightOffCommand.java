package com.lyp.learn.dp.pattern.commanpattern;

/**
 * 具体的命令对象ConcreteCommand：（关灯命令）
 */
public class LightOffCommand implements Command{

    private Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOff();
    }
}
