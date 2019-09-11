package com.lyp.learn.dp.pattern.Templatemethod2;

/**
 * 咖啡
 */
public class Coffee extends Beverage {
    @Override
    public void brew() {
        System.out.println("开水 冲咖啡");
    }

    @Override
    public void addCoundiments() {
        System.out.println("添加糖和牛奶");
    }

    @Override
    public void hook(){
        System.out.println("咖啡做活动 买一杯送一杯");
    }
}
