package com.lyp.learn.dp.pattern.singlepattern5.s4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liyapu
 * @date 2023-03-08 09:51
 * @description 静态内部类
 * 我们再来看一种比双重检测更加简单的实现方法，那就是利用Java的静态内部类。它有点类似饿汉式，但又能做到了延迟加载。具体是怎么做到的呢？
 * 我们先来看它的代码实现。
 * <p>
 * SingletonHolder 是一个静态内部类，当外部类IdGenerator被加载的时候，并不会创建SingletonHolder实例对象。只有当调用getInstance()方法时，
 * SingletonHolder才会被加载，这个时候才会创建instance。instance的唯一性、创建过程的线程安全性，都由JVM来保证。
 * 所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 */
public class IdGenerator {

    private AtomicLong id = new AtomicLong(0);

    private IdGenerator() {
    }

    private static class SingletonHolder {

        private static final IdGenerator instance = new IdGenerator();
    }

    public static IdGenerator getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
