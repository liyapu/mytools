package com.lyp.learn.base.demo.pk07;

/**
 * Runnable接口应由任何类实现，其实例将由线程执行。 该类必须定义一个无参数的方法，称为run 。
 * 该接口旨在为希望在活动时执行代码的对象提供一个通用协议。
 * 例如， Runnable由Thread类Thread 。 活跃的只是意味着一个线程已经启动，还没有被停止。
 *
 * 另外， Runnable提供了一个类被激活而不是Thread Thread类化的Thread 。
 * 一个实现类Runnable可以在不继承运行Thread实例化一个Thread实例，并在传递本身作为目标。
 * 在大多数情况下， Runnable接口应使用，如果你只打算重写run()方法并没有其他Thread方法。
 * 这是重要的，因为类不应该被子类化，除非程序员打算修改或增强类的基本行为。
 */
@FunctionalInterface
public interface Runnable {
    /**
     * 当实现接口的对象Runnable被用来创建一个线程，
     * 启动线程使对象的run在独立执行的线程中调用的方法。
     *
     * 方法run的一般合同是它可以采取任何行动。
     */
    public abstract void run();
}
