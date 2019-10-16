package com.lyp.learn.guava.eventbus;

/**
 * eventbus允许组件之间的发布-订阅式通信，而无需要显式地彼此注册的组件（并因此了解彼此）。
 * 它是专门设计为使用显式注册。它不是一个通用的发布订阅系统，也不是有意的用于进程间通信。
 *
 * 观察者模式其实就是生产者消费者的一个变种，就是一边有变化，然后有一个中介，也就是观察者去告诉消费者说：我说哥们啊，他们那边又变了，咱也跟着变吧！
 * 然后观察者要么就是一个抽象类或者一个接口，里面有个update方法，需要每个处理的实例去实现，然后变化的那方持有这些实例，然后挨个去通知。
 * 所以你也看到了，这个持有操作其实就是很不优雅的操作，所以我们用EventBus来看下这个地方到底是怎么实现的优雅的
 *
 *
 * 注解有俩个：AllowConcurrentEvents和Subscribe，
 * @AllowConcurrentEvents 代表使用线程安全方式获得通知
 * @Subscribe 代表这是一个订阅者，这俩注解和一起使用
 *
 * EventBus也就是观察者角色类有两个 EventBus和AsyncEventBus，前面是同步消息，后面支持异步消息。
 * DeadEvent是指没人关心的消息，可以做一下特殊处理，这个还是很有用，你可以获得有哪些消息根本没人消费过。
 * Subscriber订阅者对象，具体是哪个EventBus、哪个Listener、哪个方法 3者共同决定一个Subcriber
 * Dispatcher分发消息给上面那个（那些）Subscriber
 * SubscriberExceptionContext 订阅者抛出的异常上下文
 * SubscriberExceptionHandler 接口，处理订阅者抛出的异常
 * SubscriberRegistry 订阅动作，处理订阅者注册到一个EventBus的动作
 */
public interface info {
}
