package com.lyp.learn.dp.pattern.Templatemethod2;

/**
 * 饮料模板基类
 * 抽象类
 */
public abstract class Beverage {

    /**
     * 制作饮料流程
     * 定义为 final，防止子类重写
     * 注意事项：为防止恶意操作，一般模板方法都加上 final 关键词。
     */
    public final void create() {
        boilWater();//把水煮沸
        brew();//用沸水冲泡...
        pourInCup();//把...倒进杯子
        addCoundiments();//加调味品...
        hook();
    }

    /**
     * 公共方法，默认实现
     */
    public void boilWater() {
        System.out.println("煮开水");
    }

    public abstract void brew();


    public void pourInCup() {
        System.out.println("倒进杯子");
    }

    public abstract void addCoundiments();

    /**
     * 在模版模式中使用挂钩(hook)
     * 存在一个空实现的方法，我们称这种方法为”hook”。
     * 子类可以视情况来决定是否要覆盖它。
     * 假如我们搞活动，喝一杯咖啡送一杯，修改咖啡(Coffee)类
     */
    protected void hook(){}

}
