package com.lyp.learn.base.demo.pk13;

import java.util.concurrent.RejectedExecutionException;

/**
 * 执行提交的对象Runnable任务。
 * 该接口提供了一种将任务提交从每个任务的运行机制分解的方式，包括线程使用，调度等的Executor
 * 通常使用Executor而不是显式创建线程。
 * 例如，不是为一组任务调用new Thread(new(RunnableTask())).start() ，您可以使用：
 *      Executor executor = anExecutor;
 *      executor.execute(new RunnableTask1());
 *      executor.execute(new RunnableTask2());
 *       ...
 *
 * 但是， Executor接口并不严格要求执行是异步的。
 * 在最简单的情况下，执行程序可以立即在调用者的线程中运行提交的任务：
 *    class DirectExecutor implements Executor {
 *          public void execute(Runnable r) {
 *              r.run();
 *          }
 *     }
 * 更典型的是，除了调用者的线程之外，任务在一些线程中执行。
 * 下面的执行器为每个任务生成一个新的线程。
 *    class ThreadPerTaskExecutor implements Executor {
 *          public void execute(Runnable r) {
 *              new Thread(r).start();
 *          }
 *     }
 * 许多Executor实施对如何和何时安排任务施加某种限制。
 *
 * 下面的执行者将任务的提交序列化到第二个执行者，说明复合执行器。
 * class SerialExecutor implements Executor {
 *   final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
 *   final Executor executor;
 *   Runnable active;
 *
 *   SerialExecutor(Executor executor) {
 *     this.executor = executor;
 *   }
 *
 *   public synchronized void execute(final Runnable r) {
 *     tasks.offer(new Runnable() {
 *       public void run() {
 *         try {
 *           r.run();
 *         } finally {
 *           scheduleNext();
 *         }
 *       }
 *     });
 *     if (active == null) {
 *       scheduleNext();
 *     }
 *   }
 *
 *   protected synchronized void scheduleNext() {
 *     if ((active = tasks.poll()) != null) {
 *       executor.execute(active);
 *     }
 *   }
 * }
 *
 * 该包中提供的ExecutorService实现了Executor ，这是一个更广泛的接口。
 * ThreadPoolExecutor类提供了一个可扩展的线程池实现。
 * Executors类为这些执行人员提供了方便的工厂方法。
 *
 * 内存一致性效果：操作在一个线程提交之前Runnable对象到Executor happen-before其执行开始，也许在另一个线程。
 */
public interface Executor {

    /**
     * 在将来的某个时间执行给定的命令。
     * 该命令可以在一个新线程，一个合并的线程中或在调用线程中执行，由Executor实现。
     *
     * @param command 可运行的任务
     * @throws RejectedExecutionException 如果此任务不能被接受执行
     * @throws NullPointerException 如果命令为空
     */
    void execute(Runnable command);
}
