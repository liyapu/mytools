package com.lyp.learn.dp.pattern.singlepattern5.s1;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liyapu
 * @date 2023-03-08 09:45
 * @description 1.饿汉式
 * 饿汉式的实现方式比较简单。在类加载的时候，instance静态实例就已经创建并初始化好了，所以，instance实例的创建过程是线程安全的。
 * 不过，这样的实现方式不支持延迟加载（在真正用到IdGenerator的时候，再创建实例），从名字中我们也可以看出这一点。具体的代码实现如下所示：
 * <p>
 * <p>
 * <p>
 * 有人觉得这种实现方式不好，因为不支持延迟加载，如果实例占用资源多（比如占用内存多）或初始化耗时长（比如需要加载各种配置文件），
 * 提前初始化实例是一种浪费资源的行为。最好的方法应该在用到的时候再去初始化。不过，我个人并不认同这样的观点。
 * 如果初始化耗时长，那我们最好不要等到真正要用它的时候，才去执行这个耗时长的初始化过程，这会影响到系统的性能
 * （比如，在响应客户端接口请求的时候，做这个初始化操作，会导致此请求的响应时间变长，甚至超时）。采用饿汉式实现方式，将耗时的初始化操作，
 * 提前到程序启动的时候完成，这样就能避免在程序运行的时候，再去初始化导致的性能问题。
 * 如果实例占用资源多，按照fail-fast的设计原则（有问题及早暴露），那我们也希望在程序启动时就将这个实例初始化好。如果资源不够，
 * 就会在程序启动的时候触发报错（比如Java中的 PermGen Space OOM），我们可以立即去修复。这样也能避免在程序运行一段时间后，
 * 突然因为初始化这个实例占用资源过多，导致系统崩溃，影响系统的可用性。
 */
public class IdGenerator {

    // AtomicLong是一个Java并发库中提供的一个原子变量类型,
    // 它将一些线程不安全需要加锁的复合操作封装为了线程安全的原子操作，
    // 比如下面会用到的incrementAndGet().
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
