package com.lyp.learn.dp.pattern.singlepattern5.s5;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liyapu
 * @date 2023-03-08 09:52
 * @description 枚举
 * 最后，我们介绍一种最简单的实现方式，基于枚举类型的单例实现。这种实现方式通过Java枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 * 具体的代码如下所示：
 */
public enum IdGenerator {
    INSTANCE;
    
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
