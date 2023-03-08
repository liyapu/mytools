package com.lyp.learn.dp.pattern.singlepattern5.s6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liyapu
 * @date 2023-03-08 14:17
 * @description 如何实现线程唯一的单例？
 * 刚刚我们讲了单例类对象是进程唯一的，一个进程只能有一个单例对象。那如何实现一个线程唯一的单例呢？
 * 我们先来看一下，什么是线程唯一的单例，以及“线程唯一”和“进程唯一”的区别。
 * “进程唯一”指的是进程内唯一，进程间不唯一。类比一下，“线程唯一”指的是线程内唯一，线程间可以不唯一。实际上，“进程唯一”还代表了线程内、线程间都唯一，这也是“进程唯一”和“线程唯一”的区别之处。这段话听起来有点像绕口令，我举个例子来解释一下。
 * 假设IdGenerator是一个线程唯一的单例类。在线程A内，我们可以创建一个单例对象a。因为线程内唯一，在线程A内就不能再创建新的IdGenerator对象了，而线程间可以不唯一，所以，在另外一个线程B内，我们还可以重新创建一个新的单例对象b。
 * 尽管概念理解起来比较复杂，但线程唯一单例的代码实现很简单，如下所示。在代码中，我们通过一个HashMap来存储对象，其中key是线程ID，value是对象。这样我们就可以做到，不同的线程对应不同的对象，同一个线程只能对应一个对象。
 * 实际上，Java语言本身提供了ThreadLocal工具类，可以更加轻松地实现线程唯一单例。不过，ThreadLocal底层实现原理也是基于下面代码中所示的HashMap。
 */
public class IdGenerator {

    private AtomicLong id = new AtomicLong(0);

    private static final ConcurrentHashMap<Long, IdGenerator> instances = new ConcurrentHashMap<>();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new IdGenerator());
        return instances.get(currentThreadId);
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
