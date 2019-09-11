package com.lyp.learn.dp.pattern.commanpattern;

/**
 * 客户端对象
 */
public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        Command command = new LightOnCommand(light);
        XiaoAi xiaoAi = new XiaoAi(command);

        System.out.println("小爱帮我开一下灯！");
        xiaoAi.doCommand();

        System.out.println();
        command = new LightOffCommand(light);
        xiaoAi.setCommand(command);
        System.out.println("小爱帮我关一下灯!");
        xiaoAi.doCommand();
    }
}
