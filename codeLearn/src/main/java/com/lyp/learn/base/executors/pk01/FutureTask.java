package com.lyp.learn.base.executors.pk01;//package com.lyp.learn.demo.pk13;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.LockSupport;
//
///**
// * 可取消的异步计算。
// * 该类提供了一个Future的基本实现 ，具有启动和取消计算的方法，查询计算是否完整，并检索计算结果。
// * 结果只能在计算完成后才能检索;如果计算尚未完成，则get方法将阻止。
// * 一旦计算完成，则无法重新启动或取消计算（除非使用runAndReset()调用计算 ）。
// *
// * A FutureTask可用于包装Callable或Runnable对象。 因为FutureTask实现Runnable ，
// * 一个FutureTask可以提交到一个Executor执行。
// *
// * 除了作为独立类之外，此类还提供了protected功能，在创建自定义任务类时可能很有用。
// *
// * @param <V> The result type returned by this FutureTask's {@code get} methods
// */
//public class FutureTask<V> implements RunnableFuture<V> {
//    /*
//     * Revision notes: This differs from previous versions of this
//     * class that relied on AbstractQueuedSynchronizer, mainly to
//     * avoid surprising users about retaining interrupt status during
//     * cancellation races. Sync control in the current design relies
//     * on a "state" field updated via CAS to track completion, along
//     * with a simple Treiber stack to hold waiting threads.
//     *
//     * Style note: As usual, we bypass overhead of using
//     * AtomicXFieldUpdaters and instead directly use Unsafe intrinsics.
//     */
//
//    /**
//     * FutureTask中使用state表示任务状态，state值变更的由CAS操作保证原子性。
//     *
//     * FutureTask对象初始化时，在构造器中把state置为为NEW，之后状态的变更依据具体执行情况来定。
//     *
//     * 例如任务执行正常结束前，state会被设置成COMPLETING，代表任务即将完成，
//     * 接下来很快就会被设置为NARMAL或者EXCEPTIONAL，
//     * 这取决于调用Runnable中的call()方法是否抛出了异常。有异常则后者，反之前者。
//     *
//     * 任务提交后、任务结束前取消任务，那么有可能变为CANCELLED或者INTERRUPTED。
//     * 在调用cancel方法时，如果传入false表示不中断线程，state会被置为CANCELLED，
//     * 反之state先被变为INTERRUPTING，后变为INTERRUPTED
//     *
//     * Possible state transitions:
//     * NEW -> COMPLETING -> NORMAL
//     * NEW -> COMPLETING -> EXCEPTIONAL
//     * NEW -> CANCELLED
//     * NEW -> INTERRUPTING -> INTERRUPTED
//     *
//     * 总结下，FutureTask的状态流转过程，可以出现以下四种情况：
//     * 1. 任务正常执行并返回。 NEW -> COMPLETING -> NORMAL
//     * 2. 执行中出现异常。NEW -> COMPLETING -> EXCEPTIONAL
//     * 3. 任务执行过程中被取消，并且不响应中断。NEW -> CANCELLED
//     * 4.任务执行过程中被取消，并且响应中断。 NEW -> INTERRUPTING -> INTERRUPTED　
//     */
//    private volatile int state;
//    private static final int NEW          = 0;
//    private static final int COMPLETING   = 1;
//    private static final int NORMAL       = 2;
//    private static final int EXCEPTIONAL  = 3;
//    private static final int CANCELLED    = 4;
//    private static final int INTERRUPTING = 5;
//    private static final int INTERRUPTED  = 6;
//
//    /** 被提交的任务 */
//    private Callable<V> callable;
//    /** 任务执行结果或者任务异常 */
//    private Object outcome; // non-volatile, protected by state reads/writes
//    /** 执行任务的线程 */
//    private volatile Thread runner;
//    /** 等待节点，关联等待线程 */
//    private volatile WaitNode waiters;
//
//    /**
//     * 返回计算结果，或者抛出异常
//     * @param s completed state value
//     */
//    @SuppressWarnings("unchecked")
//    private V report(int s) throws ExecutionException {
//        Object x = outcome;
//        if (s == NORMAL)
//            return (V)x;
//        if (s >= CANCELLED)
//            throw new CancellationException();
//        throw new ExecutionException((Throwable)x);
//    }
//
//    /**
//     * 创建一个 FutureTask ，它将在运行时执行给定的 Callable 。
//     * @param  callable 可调用任务
//     * @throws NullPointerException 如果可调用为null
//     */
//    public FutureTask(Callable<V> callable) {
//        if (callable == null)
//            throw new NullPointerException();
//        this.callable = callable;
//        this.state = NEW;       // ensure visibility of callable
//    }
//
//    /**
//     * 创建一个 FutureTask ，
//     * 将在运行时执行给定的 Runnable ，并安排 get将在成功完成后返回给定的结果。
//     *
//     * @param runnable 可运行的任务
//     * @param result 成功完成后返回的结果。 如果您不需要特定的结果，请考虑使用以下形式的结构：
//     *              Future<?> f = new FutureTask<Void>(runnable, null)
//     * @throws NullPointerException 如果runnable为null
//     */
//    public FutureTask(Runnable runnable, V result) {
//        this.callable = Executors.callable(runnable, result);
//        this.state = NEW;       // ensure visibility of callable
//    }
//
//    /**
//     * 如果此任务在正常完成之前被取消，则返回 true 。
//     * @return
//     */
//    public boolean isCancelled() {
//        return state >= CANCELLED;
//    }
//
//    /**
//     * 返回true如果任务已完成。
//     * 完成可能是由于正常终止，异常或取消 - 在所有这些情况下，此方法将返回true 。
//     * @return
//     */
//    public boolean isDone() {
//        return state != NEW;
//    }
//
//    /**
//     * 尝试取消执行此任务。
//     * 如果任务已经完成，已经被取消或由于某种其他原因而无法取消，则此尝试将失败。
//     * 如果成功，并且当cancel时此任务尚未启动，则此任务不应运行。
//     * 如果任务已经开始，
//     * 那么mayInterruptIfRunning参数确定是否执行此任务的线程应该以试图停止任务被中断。
//     *
//     * 此方法返回后，后续调用Future.isDone()将始终返回true 。
//     * 随后电话Future.isCancelled()总是返回true如果此方法返回true 。
//     * @param mayInterruptIfRunning
//     * @return
//     */
//    public boolean cancel(boolean mayInterruptIfRunning) {
//        if (!(state == NEW &&
//              UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
//                  mayInterruptIfRunning ? INTERRUPTING : CANCELLED)))
//            return false;
//        try {    // in case call to interrupt throws exception
//            if (mayInterruptIfRunning) {
//                try {
//                    Thread t = runner;
//                    if (t != null)
//                        t.interrupt();
//                } finally { // final state
//                    UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
//                }
//            }
//        } finally {
//            finishCompletion();
//        }
//        return true;
//    }
//
//    /**
//     * 等待计算完成，然后检索其结果。
//     * @throws CancellationException {@inheritDoc}
//     */
//    public V get() throws InterruptedException, ExecutionException {
//        int s = state;
//        if (s <= COMPLETING)
//            s = awaitDone(false, 0L);
//        return report(s);
//    }
//
//    /**
//     * 如果需要等待最多在给定的时间计算完成，然后检索其结果（如果可用）。
//     * @throws CancellationException {@inheritDoc}
//     */
//    public V get(long timeout, TimeUnit unit)
//        throws InterruptedException, ExecutionException, TimeoutException {
//        if (unit == null)
//            throw new NullPointerException();
//        int s = state;
//        if (s <= COMPLETING &&
//            (s = awaitDone(true, unit.toNanos(timeout))) <= COMPLETING)
//            throw new TimeoutException();
//        return report(s);
//    }
//
//    /**
//     * 当此任务转换到状态isDone （无论是正常还是通过取消）时调用的受保护方法。
//     * 默认实现什么都不做。
//     * 子类可以覆盖此方法来调用完成回调或执行簿记。
//     * 请注意，您可以在执行此方法之前查询状态，以确定此任务是否已被取消。
//     */
//    protected void done() { }
//
//    /**
//     * 将此未来的结果设置为给定值，除非此未来已被设置或已被取消。
//     * 该方法在成功完成计算后由run()方法内部调用。
//     *
//     * @param v the value
//     */
//    protected void set(V v) {
//        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
//            outcome = v;
//            UNSAFE.putOrderedInt(this, stateOffset, NORMAL); // final state
//            finishCompletion();
//        }
//    }
//
//    /**
//     * Causes this future to report an {@link ExecutionException}
//     * with the given throwable as its cause, unless this future has
//     * already been set or has been cancelled.
//     *
//     * <p>This method is invoked internally by the {@link #run} method
//     * upon failure of the computation.
//     *
//     * @param t the cause of failure
//     */
//    protected void setException(Throwable t) {
//        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
//            outcome = t;
//            UNSAFE.putOrderedInt(this, stateOffset, EXCEPTIONAL); // final state
//            finishCompletion();
//        }
//    }
//
//    /**
//     *说明：
//     * run()中会执行Callable对象的call()方法，
//     *  并且最终将结果保存到result中，并通过set(result)将result保存。
//     * 之后调用FutureTask的get()方法，返回的就是通过set(result)保存的值。
//     */
//    public void run() {
//        if (state != NEW ||
//                !UNSAFE.compareAndSwapObject(this, runnerOffset,
//                        null, Thread.currentThread()))
//            return;
//        try {
//            // 将callable对象赋值给c。
//            Callable<V> c = callable;
//            if (c != null && state == NEW) {
//                V result;
//                boolean ran;
//                try {
//                    // 执行Callable的call()方法，并保存结果到result中。
//                    result = c.call();
//                    ran = true;
//                } catch (Throwable ex) {
//                    result = null;
//                    ran = false;
//                    setException(ex);
//                }
//                // 如果运行成功，则将result保存
//                if (ran)
//                    set(result);
//            }
//        } finally {
//            runner = null;
//            // 设置“state状态标记”
//            int s = state;
//            if (s >= INTERRUPTING)
//                handlePossibleCancellationInterrupt(s);
//        }
//    }
//
//
//    /**
//     * 执行计算而不设置其结果，然后将此future重置为初始状态，
//     * 如果计算遇到异常或被取消，则不执行此操作。
//     * 这被设计用于内在地执行多次的任务。
//     *
//     * @return true如果成功运行和重置
//     */
//    protected boolean runAndReset() {
//        if (state != NEW ||
//            !UNSAFE.compareAndSwapObject(this, runnerOffset,
//                                         null, Thread.currentThread()))
//            return false;
//        boolean ran = false;
//        int s = state;
//        try {
//            Callable<V> c = callable;
//            if (c != null && s == NEW) {
//                try {
//                    c.call(); // don't set result
//                    ran = true;
//                } catch (Throwable ex) {
//                    setException(ex);
//                }
//            }
//        } finally {
//            // runner must be non-null until state is settled to
//            // prevent concurrent calls to run()
//            runner = null;
//            // state must be re-read after nulling runner to prevent
//            // leaked interrupts
//            s = state;
//            if (s >= INTERRUPTING)
//                handlePossibleCancellationInterrupt(s);
//        }
//        return ran && s == NEW;
//    }
//
//    /**
//     * Ensures that any interrupt from a possible cancel(true) is only
//     * delivered to a task while in run or runAndReset.
//     */
//    private void handlePossibleCancellationInterrupt(int s) {
//        // It is possible for our interrupter to stall before getting a
//        // chance to interrupt us.  Let's spin-wait patiently.
//        if (s == INTERRUPTING)
//            while (state == INTERRUPTING)
//                Thread.yield(); // wait out pending interrupt
//
//        // assert state == INTERRUPTED;
//
//        // We want to clear any interrupt we may have received from
//        // cancel(true).  However, it is permissible to use interrupts
//        // as an independent mechanism for a task to communicate with
//        // its caller, and there is no way to clear only the
//        // cancellation interrupt.
//        //
//        // Thread.interrupted();
//    }
//
//    /**
//     * Simple linked list nodes to record waiting threads in a Treiber
//     * stack.  See other classes such as Phaser and SynchronousQueue
//     * for more detailed explanation.
//     */
//    static final class WaitNode {
//        volatile Thread thread;
//        volatile WaitNode next;
//        WaitNode() { thread = Thread.currentThread(); }
//    }
//
//    /**
//     * Removes and signals all waiting threads, invokes done(), and
//     * nulls out callable.
//     */
//    private void finishCompletion() {
//        // assert state > COMPLETING;
//        for (WaitNode q; (q = waiters) != null;) {
//            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
//                for (;;) {
//                    Thread t = q.thread;
//                    if (t != null) {
//                        q.thread = null;
//                        LockSupport.unpark(t);
//                    }
//                    WaitNode next = q.next;
//                    if (next == null)
//                        break;
//                    q.next = null; // unlink to help gc
//                    q = next;
//                }
//                break;
//            }
//        }
//
//        done();
//
//        callable = null;        // to reduce footprint
//    }
//
//    /**
//     * Awaits completion or aborts on interrupt or timeout.
//     *
//     * @param timed true if use timed waits
//     * @param nanos time to wait, if timed
//     * @return state upon completion
//     */
//    private int awaitDone(boolean timed, long nanos)
//        throws InterruptedException {
//        final long deadline = timed ? System.nanoTime() + nanos : 0L;
//        WaitNode q = null;
//        boolean queued = false;
//        for (;;) {
//            if (Thread.interrupted()) {
//                removeWaiter(q);
//                throw new InterruptedException();
//            }
//
//            int s = state;
//            if (s > COMPLETING) {
//                if (q != null)
//                    q.thread = null;
//                return s;
//            }
//            else if (s == COMPLETING) // cannot time out yet
//                Thread.yield();
//            else if (q == null)
//                q = new WaitNode();
//            else if (!queued)
//                queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
//                                                     q.next = waiters, q);
//            else if (timed) {
//                nanos = deadline - System.nanoTime();
//                if (nanos <= 0L) {
//                    removeWaiter(q);
//                    return state;
//                }
//                LockSupport.parkNanos(this, nanos);
//            }
//            else
//                LockSupport.park(this);
//        }
//    }
//
//    /**
//     * Tries to unlink a timed-out or interrupted wait node to avoid
//     * accumulating garbage.  Internal nodes are simply unspliced
//     * without CAS since it is harmless if they are traversed anyway
//     * by releasers.  To avoid effects of unsplicing from already
//     * removed nodes, the list is retraversed in case of an apparent
//     * race.  This is slow when there are a lot of nodes, but we don't
//     * expect lists to be long enough to outweigh higher-overhead
//     * schemes.
//     */
//    private void removeWaiter(WaitNode node) {
//        if (node != null) {
//            node.thread = null;
//            retry:
//            for (;;) {          // restart on removeWaiter race
//                for (WaitNode pred = null, q = waiters, s; q != null; q = s) {
//                    s = q.next;
//                    if (q.thread != null)
//                        pred = q;
//                    else if (pred != null) {
//                        pred.next = s;
//                        if (pred.thread == null) // check for race
//                            continue retry;
//                    }
//                    else if (!UNSAFE.compareAndSwapObject(this, waitersOffset,
//                                                          q, s))
//                        continue retry;
//                }
//                break;
//            }
//        }
//    }
//
//    // Unsafe mechanics
//    private static final sun.misc.Unsafe UNSAFE;
//    private static final long stateOffset;
//    private static final long runnerOffset;
//    private static final long waitersOffset;
//    static {
//        try {
//            UNSAFE = sun.misc.Unsafe.getUnsafe();
//            Class<?> k = FutureTask.class;
//            stateOffset = UNSAFE.objectFieldOffset
//                (k.getDeclaredField("state"));
//            runnerOffset = UNSAFE.objectFieldOffset
//                (k.getDeclaredField("runner"));
//            waitersOffset = UNSAFE.objectFieldOffset
//                (k.getDeclaredField("waiters"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//}
