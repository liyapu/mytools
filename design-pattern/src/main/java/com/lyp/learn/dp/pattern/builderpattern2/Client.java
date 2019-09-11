package com.lyp.learn.dp.pattern.builderpattern2;

public class Client {
    public static void main(String[] args) {
        ComputerDirector director = new ComputerDirector();

        ThinkPad thinkPad = director.createThinkPad();
        System.out.println(thinkPad);

        System.out.println("-------------------");

        Dell dell = director.createDell();
        System.out.println(dell);

    }
}
