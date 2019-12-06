
package com.lyp.learn.base.executors.pk01;

import com.lyp.learn.base.threads.pk01.Runnable;

import java.util.concurrent.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService可以调度命令在给定的延迟之后运行，或定期执行。
 * schedule方法创建具有各种延迟的任务，并返回可用于取消或检查执行的任务对象。
 * scheduleAtFixedRate和scheduleWithFixedDelay方法创建并执行定期运行的任务，直到取消。
 *
 * 使用Executor.execute(Runnable)和ExecutorService submit方法提交的命令以请求的延迟为零进行调度。
 * 在schedule方法中也允许零和负延迟（但不是周期），并被视为立即执行的请求。
 *
 * 所有schedule方法接受相对延迟和周期作为参数，而不是绝对时间或日期。
 * 这是一个简单的事情变换表示为绝对时间Date至所需的形式。
 * 例如，要在一定的未来date ，
 * 您可以使用： schedule(task, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
 * 然而，请注意，由于网络时间同步协议，时钟漂移或其他因素，相对延迟的到期不需要与任务启用的当前Date重合。
 *
 * Executors类为此包中提供的ScheduledExecutorService实现提供了方便的工厂方法。
 *
 * 用法示例
 * 这是一个类，其方法是将ScheduledExecutorService设置为每10秒钟发出一个小时的蜂鸣声：
 *
 * import static java.util.concurrent.TimeUnit.*;
 * class BeeperControl {
 *   private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
 *
 *   public void beepForAnHour() {
 *
 *     final Runnable beeper = new Runnable() {
 *       public void run() {
 *          System.out.println("beep");
 *       }
 *     }
 *
 *     final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
 *     scheduler.schedule(new Runnable() {
 *       public void run() {
 *          beeperHandle.cancel(true);
 *        }
 *     }, 60 * 60, SECONDS);
 *   }
 *
 * }
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface ScheduledExecutorService extends ExecutorService {

    /**
     * 创建并执行在给定延迟后启用的单次操作。
     *
     * @param command 要执行的任务
     * @param delay  从现在开始延迟执行的时间
     * @param unit 延时参数的时间单位
     * @return 表示任务等待完成，并且其的ScheduledFuture get()方法将返回 null完成后
     * @throws RejectedExecutionException 如果任务无法安排执行
     * @throws NullPointerException 如果命令为空
     */
    public ScheduledFuture<?> schedule(Runnable command, long delay, java.util.concurrent.TimeUnit unit);

    /**
     * 创建并执行在给定延迟后启用的ScheduledFuture。
     * @param callable  执行的功能
     * @param delay 从现在开始延迟执行的时间
     * @param unit  延迟参数的时间单位
     * @param <V> 可调用结果的类型
     * @return 一个可用于提取结果或取消的ScheduledFuture
     * @throws RejectedExecutionException 如果任务无法安排执行
     * @throws NullPointerException if callable is null
     */
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, java.util.concurrent.TimeUnit unit);

    /**
     * 创建并执行在给定的初始延迟之后，随后以给定的时间段首先启用的周期性动作;
     * 那就是执行将在initialDelay之后开始，然后initialDelay+period ，然后是initialDelay + 2 * period ，等等。
     * 如果任务的执行遇到异常，则后续的执行被抑制。 否则，任务将仅通过取消或终止执行人终止。
     * 如果任务执行时间比其周期长，则后续执行可能会迟到，但不会同时执行。
     *
     * @param command 要执行的任务
     * @param initialDelay 延迟第一次执行的时间
     * @param period 连续执行之间的时期
     * @param unit initialDelay和period参数的时间单位
     * @return 一个ScheduledFuture代表待完成的任务，其 get()方法将在取消时抛出异常
     *@throws RejectedExecutionException - 如果任务无法安排执行
     *@throws  NullPointerException - 如果命令为空
     *@throws IllegalArgumentException - 如果周期小于或等于零
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  java.util.concurrent.TimeUnit unit);

    /**
     * 创建并执行在给定的初始延迟之后首先启用的定期动作，随后在一个执行的终止和下一个执行的开始之间给定的延迟。
     * 如果任务的执行遇到异常，则后续的执行被抑制。 否则，任务将仅通过取消或终止执行人终止。
     *
     * @param command - 要执行的任务
     * @param initialDelay - 延迟第一次执行的时间
     * @param delay - 一个执行终止与下一个执行的开始之间的延迟
     * @param unit - initialDelay和delay参数的时间单位
     * @return 一个ScheduledFuture代表待完成的任务，其 get()方法将在取消时抛出异常
     * @throws RejectedExecutionException - 如果任务不能安排执行
     * @throws NullPointerException - 如果命令为空
     * @throws IllegalArgumentException - 如果延迟小于或等于零
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit);

}
