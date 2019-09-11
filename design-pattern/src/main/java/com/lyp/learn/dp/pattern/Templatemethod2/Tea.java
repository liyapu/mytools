package com.lyp.learn.dp.pattern.Templatemethod2;

/**
 * 茶
 */
public class Tea extends Beverage {
    @Override
    public void brew() {
        System.out.println("开水 泡茶叶");
    }

    @Override
    public void addCoundiments() {
        System.out.println("添加蜂蜜");
    }
}
