package com.lyp.learn.dp.pattern.observerpattern5;

import java.util.concurrent.Executor;

/**
 * @author liyapu
 * @date 2023-03-19 11:54
 * @description 有了EventBus，AsyncEventBus的实现就非常简单了。
 * 为了实现异步非阻塞的观察者模式，它就不能再继续使用MoreExecutors.directExecutor()了，而是需要在构造函数中，由调用者注入线程池。
 * <p>
 * 至此，我们用了不到200行代码，就实现了一个还算凑活能用的EventBus，从功能上来讲，它跟Google Guava EventBus几乎一样。
 * 不过，如果去查看Google Guava EventBus的源码，你会发现，在实现细节方面，相比我们现在的实现，它其实做了很多优化，
 * 比如优化了在注册表中查找消息可匹配函数的算法。如果有时间的话，建议你去读一下它的源码。
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}