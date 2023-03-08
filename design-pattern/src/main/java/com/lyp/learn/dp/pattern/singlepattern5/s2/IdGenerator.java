package com.lyp.learn.dp.pattern.singlepattern5.s2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liyapu
 * @date 2023-03-08 09:48
 * @description 懒汉式
 * 有饿汉式，对应的，就有懒汉式。懒汉式相对于饿汉式的优势是支持延迟加载。具体的代码实现如下所示：
 * <p>
 * 不过懒汉式的缺点也很明显，我们给getInstance()这个方法加了一把大锁（synchronzed），导致这个函数的并发度很低。
 * 量化一下的话，并发度是1，也就相当于串行操作了。而这个函数是在单例使用期间，一直会被调用。如果这个单例类偶尔会被用到，
 * 那这种实现方式还可以接受。但是，如果频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，这种实现方式就不可取了。
 */
public class IdGenerator {

    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator instance;

    private IdGenerator() {
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
