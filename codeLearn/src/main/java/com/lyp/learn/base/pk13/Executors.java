package com.lyp.learn.base.pk13;//package com.lyp.learn.demo.pk13;
//
//import sun.security.util.SecurityConstants;
//
//import java.security.*;
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.AbstractExecutorService;
//import java.util.concurrent.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 工厂和工具方法Executor ， ExecutorService ， ScheduledExecutorService ，
// * ThreadFactory和Callable在此包中定义的类。
// * 该类支持以下几种方法：
// *      创建并返回一个ExecutorService设置的常用的配置设置的方法。
// *      创建并返回一个ScheduledExecutorService的方法， 其中设置了常用的配置设置。
// *      创建并返回“包装”ExecutorService的方法，通过使实现特定的方法无法访问来禁用重新配置。
// *      创建并返回将新创建的线程设置为已知状态的ThreadFactory的方法。
// *      创建并返回一个方法Callable出的其他闭包形式，这样他们就可以在需要的执行方法使用Callable
// */
//public class Executors {
//
//    /**
//     * 创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程。
//     * 在任何时候，最多nThreads线程将处于主动处理任务。
//     * 如果所有线程处于活动状态时都会提交其他任务，则它们将等待队列中直到线程可用。
//     * 如果任何线程由于在关闭之前的执行期间发生故障而终止，则如果需要执行后续任务，则新线程将占用它。
//     * 池中的线程将存在，直到它明确地为shutdown 。
//     *
//     * @param nThreads 池中的线程数
//     * @return 新创建的线程池
//     * @throws IllegalArgumentException 如果是 nThreads <= 0
//     */
//    public static ExecutorService newFixedThreadPool(int nThreads) {
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                                      0L, TimeUnit.MILLISECONDS,
//                                      new LinkedBlockingQueue<Runnable>());
//    }
//
//    /**
//     * 创建一个维护足够的线程以支持给定的并行级别的线程池，并且可以使用多个队列来减少争用。
//     * 并行级别对应于主动参与或可以从事任务处理的最大线程数。
//     * 线程的实际数量可以动态增长和收缩。
//     * 工作窃取池不保证执行提交的任务的顺序。
//     *
//     * @param parallelism  目标并行级别
//     * @return 新创建的线程池
//     * @throws IllegalArgumentException 如果是 parallelism <= 0
//     * @since 1.8
//     */
//    public static ExecutorService newWorkStealingPool(int parallelism) {
//        return new ForkJoinPool
//            (parallelism,
//             ForkJoinPool.defaultForkJoinWorkerThreadFactory,
//             null, true);
//    }
//
//    /**
//     * 使用所有 available processors作为其目标并行级别创建一个工作窃取线程池。
//     * @return 新创建的线程池
//     * @see #newWorkStealingPool(int)
//     * @since 1.8
//     */
//    public static ExecutorService newWorkStealingPool() {
//        return new ForkJoinPool
//            (Runtime.getRuntime().availableProcessors(),
//             ForkJoinPool.defaultForkJoinWorkerThreadFactory,
//             null, true);
//    }
//
//    /**
//     * 创建一个线程池，重用固定数量的线程，从共享无界队列中运行，使用提供的ThreadFactory在需要时创建新线程。
//     * 在任何时候，最多nThreads个线程将处于主动处理任务。
//     * 如果所有线程处于活动状态时都会提交其他任务，则它们将等待队列中直到线程可用。
//     * 如果任何线程由于在关闭之前的执行期间发生故障而终止，则如果需要执行后续任务，则新线程将占用它。
//     * 池中的线程将存在，直到它明确地为shutdown 。
//     *
//     * @param nThreads 池中的线程数
//     * @param threadFactory 工厂在创建新线程时使用
//     * @return 新创建的线程池
//     * @throws NullPointerException 如果threadFactory为null
//     * @throws IllegalArgumentException 如果是 nThreads <= 0
//     */
//    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                                      0L, TimeUnit.MILLISECONDS,
//                                      new LinkedBlockingQueue<Runnable>(),
//                                      threadFactory);
//    }
//
//    /**
//     * 创建一个使用从无界队列运行的单个工作线程的执行程序。
//     * （请注意，如果这个单个线程由于在关闭之前的执行过程中发生故障而终止，则如果需要执行后续任务，则新的线程将占用它。）
//     * 任务保证顺序执行，并且不超过一个任务将被激活在任何给定的时间。
//     * 与其他等效的newFixedThreadPool(1)  ，返回的执行器保证不被重新配置以使用额外的线程。
//     * @return 新创建的单线程执行器
//     */
//    public static ExecutorService newSingleThreadExecutor() {
//        return new FinalizableDelegatedExecutorService
//            (new ThreadPoolExecutor(1, 1,
//                                    0L, TimeUnit.MILLISECONDS,
//                                    new LinkedBlockingQueue<Runnable>()));
//    }
//
//    /**
//     * 创建一个使用单个工作线程运行无界队列的执行程序，并在需要时使用提供的ThreadFactory创建一个新线程。
//     * 与其他等效的newFixedThreadPool(1, threadFactory) 返回的执行器保证不被重新配置以使用额外的线程。
//     * @param threadFactory 工厂在创建新线程时使用
//     *
//     * @return 新创建的单线程执行器
//     * @throws NullPointerException 如果threadFactory为null
//     */
//    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
//        return new FinalizableDelegatedExecutorService
//            (new ThreadPoolExecutor(1, 1,
//                                    0L, TimeUnit.MILLISECONDS,
//                                    new LinkedBlockingQueue<Runnable>(),
//                                    threadFactory));
//    }
//
//    /**
//     * 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程。
//     * 这些池通常会提高执行许多短暂异步任务的程序的性能。
//     * 调用execute将重用以前构造的线程（如果可用）。
//     * 如果没有可用的线程，将创建一个新的线程并将其添加到该池中。
//     * 未使用六十秒的线程将被终止并从缓存中删除。
//     * 因此，长时间保持闲置的池将不会消耗任何资源。
//     * 请注意，可以使用ThreadPoolExecutor构造函数创建具有相似属性但不同详细信息的池（例如，超时参数）。
//     * @return 新创建的线程池
//     */
//    public static ExecutorService newCachedThreadPool() {
//        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                                      60L, TimeUnit.SECONDS,
//                                      new SynchronousQueue<Runnable>());
//    }
//
//    /**
//     * 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程，
//     * 并在需要时使用提供的ThreadFactory创建新线程。
//     * @param threadFactory 创建新线程时使用的工厂
//     * @return 新创建的线程池
//     * @throws NullPointerException if threadFactory is null
//     */
//    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
//        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                                      60L, TimeUnit.SECONDS,
//                                      new SynchronousQueue<Runnable>(),
//                                      threadFactory);
//    }
//
//    /**
//     * 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行。
//     * （请注意，如果这个单个线程由于在关闭之前的执行过程中发生故障而终止，
//     * 则如果需要执行后续任务，则新的线程将占用它。）
//     * 任务保证顺序执行，并且不超过一个任务将被激活在任何给定的时间。
//     * 与其他等效的newScheduledThreadPool(1),返回的执行器保证不被重新配置以使用额外的线程。
//     * @return 创建的预定执行者
//     */
//    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
//        return new DelegatedScheduledExecutorService
//            (new ScheduledThreadPoolExecutor(1));
//    }
//
//    /**
//     * 创建一个单线程执行器，可以调度命令在给定的延迟之后运行，或定期执行。
//     * （请注意，如果这个单个线程由于在关闭之前的执行过程中发生故障而终止，
//     * 则如果需要执行后续任务，则新的线程将占用它。）
//     * 任务保证顺序执行，并且不超过一个任务将被激活在任何给定的时间。
//     * 与其他等效的newScheduledThreadPool(1, threadFactory) ，
//     * 返回的执行器保证不被重新配置以使用额外的线程。
//     * @param threadFactory 创建新线程时使用的工厂
//     * @return 一个新创建的预定执行者
//     * @throws NullPointerException 如果threadFactory为null
//     */
//    public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory) {
//        return new DelegatedScheduledExecutorService
//            (new ScheduledThreadPoolExecutor(1, threadFactory));
//    }
//
//    /**
//     * 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行。
//     * @param corePoolSize 要保留在池中的线程数，即使它们处于空闲状态
//     * @return 一个新创建的线程池
//     * @throws IllegalArgumentException 如果是 corePoolSize < 0
//     */
//    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
//        return new ScheduledThreadPoolExecutor(corePoolSize);
//    }
//
//    /**
//     * 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行。
//     * @param corePoolSize 要保留在池中的线程数，即使它们处于空闲状态
//     * @param threadFactory 执行程序创建新线程时使用的工厂
//     * @return 一个新创建的线程池
//     * @throws IllegalArgumentException 如果是 corePoolSize < 0
//     * @throws NullPointerException  如果threadFactory为null
//     */
//    public static ScheduledExecutorService newScheduledThreadPool(
//            int corePoolSize, ThreadFactory threadFactory) {
//        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
//    }
//
//    /**
//     * 返回一个将所有定义的ExecutorService方法委托给给定执行程序的对象，
//     * 但不会使用任何其他可能使用转换访问的方法。
//     * 这提供了一种安全地“冻结”配置并且不允许调整给定的具体实现的方法。
//     * @param executor 底层实现
//     * @return 一个 ExecutorService例子
//     * @throws NullPointerException  如果执行器为空
//     */
//    public static ExecutorService unconfigurableExecutorService(ExecutorService executor) {
//        if (executor == null)
//            throw new NullPointerException();
//        return new DelegatedExecutorService(executor);
//    }
//
//    /**
//     * 返回一个将所有定义的ScheduledExecutorService方法委托给给定执行程序的对象，
//     * 但不能以其他方式使用转换方式访问。
//     * 这提供了一种安全地“冻结”配置并且不允许调整给定的具体实现的方法。
//     * @param executor 底层实现
//     * @return 一个 ScheduledExecutorService实例
//     * @throws NullPointerException 如果执行者为空
//     */
//    public static ScheduledExecutorService unconfigurableScheduledExecutorService(ScheduledExecutorService executor) {
//        if (executor == null)
//            throw new NullPointerException();
//        return new DelegatedScheduledExecutorService(executor);
//    }
//
//    /**
//     * 返回用于创建新线程的默认线程工厂。
//     * 该工厂在同一个ThreadGroup中创建了Executor使用的所有新线程。
//     * 如果有一个SecurityManager ，它使用组System.getSecurityManager() ，否则该组的线程调用这个defaultThreadFactory方法。
//     * 每个新线程被创建为非守护线程，优先级设置为Thread.NORM_PRIORITY中的较小者，线程组中允许的最大优先级。
//     * 新线程具有可通过访问名字Thread.getName() 池-N-thread-M的 ，其中N是此工厂的序列号，M是此工厂所创建线程的序列号。
//     * @return 线程工厂
//     */
//    public static ThreadFactory defaultThreadFactory() {
//        return new DefaultThreadFactory();
//    }
//
//    /**
//     * 返回一个用于创建与当前线程具有相同权限的新线程的线程工厂。
//     * 该工厂创建与defaultThreadFactory()相同设置的线程 ，
//     * 另外将新线程的AccessControlContext和contextClassLoader设置为与调用此privilegedThreadFactory方法的线程相同。
//     * 可以在AccessController.doPrivileged操作中创建一个新的privilegedThreadFactory ，设置当前线程的访问控制上下文，
//     * 以创建具有该操作中所选权限设置的线程。
//     *
//     * 请注意，虽然在这些线程中运行的任务将具有与当前线程相同的访问控制和类加载器设置，
//     * 但它们不需要具有相同的ThreadLocal或InheritableThreadLocal值。
//     * 如果需要，可以在使用ThreadPoolExecutor.beforeExecute(Thread, Runnable)的
//     * ThreadPoolExecutor子类中运行任何任务之前设置或重置线程本地的特定值。
//     * 此外，如果需要初始化工作线程以与其他指定的线程具有相同的InheritableThreadLocal设置，
//     * 则可以创建一个自定义ThreadFactory，该线程等待和服务请求创建将继承其值的其他请求。
//     *
//     * @return 一个线程工厂
//     * @throws AccessControlException 如果当前访问控制上下文没有获取和设置上下文类加载器的权限
//     */
//    public static ThreadFactory privilegedThreadFactory() {
//        return new PrivilegedThreadFactory();
//    }
//
//    /**
//     * 返回一个Callable对象，当被调用时，它运行给定的任务并返回给定的结果。
//     * 施加需要方法时这可能是有用Callable到其他无结果的动作。
//     * @param task 要运行的任务
//     * @param result  结果返回
//     * @param <T> 结果的类型
//     * @return 可调用对象
//     * @throws NullPointerException 如果任务为空
//     */
//    public static <T> Callable<T> callable(Runnable task, T result) {
//        if (task == null)
//            throw new NullPointerException();
//        return new RunnableAdapter<T>(task, result);
//    }
//
//    /**
//     * 返回一个Callable对象，当被调用时，它运行给定的任务并返回null 。
//     * @param task 要运行的任务
//     * @return 可调用对象
//     * @throws NullPointerException 如果任务为空
//     */
//    public static Callable<Object> callable(Runnable task) {
//        if (task == null)
//            throw new NullPointerException();
//        return new RunnableAdapter<Object>(task, null);
//    }
//
//    /**
//     * 返回一个Callable对象，当被调用时，它运行给定的特权动作并返回其结果。
//     * @param action 运行的特权操作
//     * @return 可调用对象
//     * @throws NullPointerException 如果动作为空
//     */
//    public static Callable<Object> callable(final PrivilegedAction<?> action) {
//        if (action == null)
//            throw new NullPointerException();
//        return new Callable<Object>() {
//            public Object call() { return action.run(); }};
//    }
//
//    /**
//     * 返回一个Callable对象，该对象在被调用时运行给定的特权异常操作并返回其结果。
//     * @param action 运行的特权异常操作
//     * @return 可调用对象
//     * @throws NullPointerException 如果动作为空
//     */
//    public static Callable<Object> callable(final PrivilegedExceptionAction<?> action) {
//        if (action == null)
//            throw new NullPointerException();
//        return new Callable<Object>() {
//            public Object call() throws Exception { return action.run(); }};
//    }
//
//    /**
//     * 返回一个Callable对象，当被调用时，将在当前访问控制上下文中执行给定的callable 。
//     * 通常在AccessController.doPrivileged操作中调用此方法来创建可调用的函数，
//     * 如果可能的话，该方法将在该操作中保留的所选权限设置下执行;
//     * 或者如果不可能，抛出一个关联的AccessControlException 。
//     * @param callable 基础任务
//     * @param <T> 可调用结果的类型
//     * @return 可调用对象
//     * @throws NullPointerException 如果可以调用null
//     */
//    public static <T> Callable<T> privilegedCallable(Callable<T> callable) {
//        if (callable == null)
//            throw new NullPointerException();
//        return new PrivilegedCallable<T>(callable);
//    }
//
//    /**
//     * 返回一个Callable对象，当被调用时，
//     * 将在当前访问控制上下文中执行给定的callable ，当前的上下文类加载器作为上下文类加载器。
//     * 通常在AccessController.doPrivileged操作中调用此方法来创建可调用的应用程序，
//     * 如果可能，在执行该操作的所选权限设置下执行; 或者如果不可能，抛出一个关联的AccessControlException 。
//     *
//     * @param callable 基础任务
//     * @param <T> 可调用结果的类型
//     * @return 可调用对象
//     * @throws NullPointerException  如果可以调用null
//     * @throws AccessControlException 如果当前访问控制上下文没有设置和获取上下文类加载器的权限
//     */
//    public static <T> Callable<T> privilegedCallableUsingCurrentClassLoader(Callable<T> callable) {
//        if (callable == null)
//            throw new NullPointerException();
//        return new PrivilegedCallableUsingCurrentClassLoader<T>(callable);
//    }
//
//    // Non-public classes supporting the public methods
//
//    /**
//     * A callable that runs given task and returns given result
//     */
//    static final class RunnableAdapter<T> implements Callable<T> {
//        final Runnable task;
//        final T result;
//        RunnableAdapter(Runnable task, T result) {
//            this.task = task;
//            this.result = result;
//        }
//        public T call() {
//            task.run();
//            return result;
//        }
//    }
//
//    /**
//     * A callable that runs under established access control settings
//     */
//    static final class PrivilegedCallable<T> implements Callable<T> {
//        private final Callable<T> task;
//        private final AccessControlContext acc;
//
//        PrivilegedCallable(Callable<T> task) {
//            this.task = task;
//            this.acc = AccessController.getContext();
//        }
//
//        public T call() throws Exception {
//            try {
//                return AccessController.doPrivileged(
//                    new PrivilegedExceptionAction<T>() {
//                        public T run() throws Exception {
//                            return task.call();
//                        }
//                    }, acc);
//            } catch (PrivilegedActionException e) {
//                throw e.getException();
//            }
//        }
//    }
//
//    /**
//     * A callable that runs under established access control settings and
//     * current ClassLoader
//     */
//    static final class PrivilegedCallableUsingCurrentClassLoader<T> implements Callable<T> {
//        private final Callable<T> task;
//        private final AccessControlContext acc;
//        private final ClassLoader ccl;
//
//        PrivilegedCallableUsingCurrentClassLoader(Callable<T> task) {
//            SecurityManager sm = System.getSecurityManager();
//            if (sm != null) {
//                // Calls to getContextClassLoader from this class
//                // never trigger a security check, but we check
//                // whether our callers have this permission anyways.
//                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
//
//                // Whether setContextClassLoader turns out to be necessary
//                // or not, we fail fast if permission is not available.
//                sm.checkPermission(new RuntimePermission("setContextClassLoader"));
//            }
//            this.task = task;
//            this.acc = AccessController.getContext();
//            this.ccl = Thread.currentThread().getContextClassLoader();
//        }
//
//        public T call() throws Exception {
//            try {
//                return AccessController.doPrivileged(
//                    new PrivilegedExceptionAction<T>() {
//                        public T run() throws Exception {
//                            Thread t = Thread.currentThread();
//                            ClassLoader cl = t.getContextClassLoader();
//                            if (ccl == cl) {
//                                return task.call();
//                            } else {
//                                t.setContextClassLoader(ccl);
//                                try {
//                                    return task.call();
//                                } finally {
//                                    t.setContextClassLoader(cl);
//                                }
//                            }
//                        }
//                    }, acc);
//            } catch (PrivilegedActionException e) {
//                throw e.getException();
//            }
//        }
//    }
//
//    /**
//     * The default thread factory
//     * 线程池中的ThreadFactory是一个线程工厂，
//     * 线程池创建线程都是通过线程工厂对象(threadFactory)来完成的。
//     *
//     * ThreadFactory的作用就是提供创建线程的功能的线程工厂。
//     *          它是通过newThread()提供创建线程功能的，
//     *          newThread()创建的线程对应的任务是Runnable对象，
//     *          它创建的线程都是“非守护线程”而且“线程优先级都是Thread.NORM_PRIORITY”。
//     */
//    static class DefaultThreadFactory implements ThreadFactory {
//        private static final AtomicInteger poolNumber = new AtomicInteger(1);
//        private final ThreadGroup group;
//        private final AtomicInteger threadNumber = new AtomicInteger(1);
//        private final String namePrefix;
//
//        DefaultThreadFactory() {
//            SecurityManager s = System.getSecurityManager();
//            group = (s != null) ? s.getThreadGroup() :
//                                  Thread.currentThread().getThreadGroup();
//            namePrefix = "pool-" +
//                          poolNumber.getAndIncrement() +
//                         "-thread-";
//        }
//
//        //提供创建线程的API
//        public Thread newThread(Runnable r) {
//            // 线程对应的任务是Runnable对象r
//            Thread t = new Thread(group, r,
//                                  namePrefix + threadNumber.getAndIncrement(),
//                                  0);
//            // 设为“非守护线程”
//            if (t.isDaemon())
//                t.setDaemon(false);
//            // 将优先级设为“Thread.NORM_PRIORITY”
//            if (t.getPriority() != Thread.NORM_PRIORITY)
//                t.setPriority(Thread.NORM_PRIORITY);
//            return t;
//        }
//    }
//
//    /**
//     * Thread factory capturing access control context and class loader
//     */
//    static class PrivilegedThreadFactory extends DefaultThreadFactory {
//        private final AccessControlContext acc;
//        private final ClassLoader ccl;
//
//        PrivilegedThreadFactory() {
//            super();
//            SecurityManager sm = System.getSecurityManager();
//            if (sm != null) {
//                // Calls to getContextClassLoader from this class
//                // never trigger a security check, but we check
//                // whether our callers have this permission anyways.
//                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
//
//                // Fail fast
//                sm.checkPermission(new RuntimePermission("setContextClassLoader"));
//            }
//            this.acc = AccessController.getContext();
//            this.ccl = Thread.currentThread().getContextClassLoader();
//        }
//
//        public Thread newThread(final Runnable r) {
//            return super.newThread(new Runnable() {
//                public void run() {
//                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
//                        public Void run() {
//                            Thread.currentThread().setContextClassLoader(ccl);
//                            r.run();
//                            return null;
//                        }
//                    }, acc);
//                }
//            });
//        }
//    }
//
//    /**
//     * A wrapper class that exposes only the ExecutorService methods
//     * of an ExecutorService implementation.
//     */
//    static class DelegatedExecutorService extends AbstractExecutorService {
//        private final ExecutorService e;
//        DelegatedExecutorService(ExecutorService executor) { e = executor; }
//        public void execute(Runnable command) { e.execute(command); }
//        public void shutdown() { e.shutdown(); }
//        public List<Runnable> shutdownNow() { return e.shutdownNow(); }
//        public boolean isShutdown() { return e.isShutdown(); }
//        public boolean isTerminated() { return e.isTerminated(); }
//        public boolean awaitTermination(long timeout, TimeUnit unit)
//            throws InterruptedException {
//            return e.awaitTermination(timeout, unit);
//        }
//        public Future<?> submit(Runnable task) {
//            return e.submit(task);
//        }
//        public <T> Future<T> submit(Callable<T> task) {
//            return e.submit(task);
//        }
//        public <T> Future<T> submit(Runnable task, T result) {
//            return e.submit(task, result);
//        }
//        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
//            throws InterruptedException {
//            return e.invokeAll(tasks);
//        }
//        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
//                                             long timeout, TimeUnit unit)
//            throws InterruptedException {
//            return e.invokeAll(tasks, timeout, unit);
//        }
//        public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
//            throws InterruptedException, ExecutionException {
//            return e.invokeAny(tasks);
//        }
//        public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
//                               long timeout, TimeUnit unit)
//            throws InterruptedException, ExecutionException, TimeoutException {
//            return e.invokeAny(tasks, timeout, unit);
//        }
//    }
//
//    static class FinalizableDelegatedExecutorService
//        extends DelegatedExecutorService {
//        FinalizableDelegatedExecutorService(ExecutorService executor) {
//            super(executor);
//        }
//        protected void finalize() {
//            super.shutdown();
//        }
//    }
//
//    /**
//     * A wrapper class that exposes only the ScheduledExecutorService
//     * methods of a ScheduledExecutorService implementation.
//     */
//    static class DelegatedScheduledExecutorService
//            extends DelegatedExecutorService
//            implements ScheduledExecutorService {
//        private final ScheduledExecutorService e;
//        DelegatedScheduledExecutorService(ScheduledExecutorService executor) {
//            super(executor);
//            e = executor;
//        }
//        public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
//            return e.schedule(command, delay, unit);
//        }
//        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
//            return e.schedule(callable, delay, unit);
//        }
//        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
//            return e.scheduleAtFixedRate(command, initialDelay, period, unit);
//        }
//        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
//            return e.scheduleWithFixedDelay(command, initialDelay, delay, unit);
//        }
//    }
//
//    /** Cannot instantiate. */
//    private Executors() {}
//}
