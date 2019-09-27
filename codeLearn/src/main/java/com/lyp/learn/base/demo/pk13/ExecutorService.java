//package com.lyp.learn.base.demo.pk13;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Executor;
//import java.util.concurrent.TimeUnit;
//
///**
// * 一个Executor ，提供方法来管理终端和方法，可以产生Future为跟踪一个或多个异步任务执行。
// *
// * 一个ExecutorService可以关闭，这将导致它拒绝新的任务。 提供了两种不同的方法来关闭ExecutorService
// * shutdown()方法将允许先前提交的任务在终止之前执行，
// * 而shutdownNow()方法可以防止等待任务启动并尝试停止当前正在执行的任务。
// * 一旦终止，执行者没有任务正在执行，没有任务正在等待执行，并且不能提交新的任务。
// * 应关闭未使用的ExecutorService以允许资源的回收。
// *
// * 方法submit延伸基方法Executor.execute(Runnable)通过创建并返回一个Future可用于取消执行和/或等待完成
// * 方法invokeAny和invokeAll执行invokeAll执行最常用的形式，执行任务集合，然后等待至少一个或全部完成。
// * （类别ExecutorCompletionService可用于编写这些方法的自定义变体。）
// *
// * Executors类为此包中提供的执行程序服务提供了工厂方法。
// *
// * 用法示例
// * 这是一个网络服务的草图，其中线程池中的线程服务传入请求。
// * 它使用预配置的Executors.newFixedThreadPool(int)工厂方法：
// *
// * class NetworkService implements Runnable {
// *   private final ServerSocket serverSocket;
// *   private final ExecutorService pool;
// *
// *   public NetworkService(int port, int poolSize)
// *       throws IOException {
// *     serverSocket = new ServerSocket(port);
// *     pool = Executors.newFixedThreadPool(poolSize);
// *   }
// *
// *   public void run() { // run the service
// *     try {
// *       for (;;) {
// *         pool.execute(new Handler(serverSocket.accept()));
// *       }
// *     } catch (IOException ex) {
// *       pool.shutdown();
// *     }
// *   }
// * }
// *
// * class Handler implements Runnable {
// *   private final Socket socket;
// *   Handler(Socket socket) { this.socket = socket; }
// *   public void run() {
// *     // read and service request on socket
// *   }
// * }
// *
// * 以下方法ExecutorService两个阶段关闭ExecutorService ，
// * 首先通过调用shutdown拒绝接收任务，然后调用shutdownNow ，
// * 如有必要，可以取消任何延迟任务：
// *
// * void shutdownAndAwaitTermination(ExecutorService pool) {
// *   pool.shutdown(); // Disable new tasks from being submitted
// *   try {
// *     // Wait a while for existing tasks to terminate
// *     if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
// *       pool.shutdownNow(); // Cancel currently executing tasks
// *       // Wait a while for tasks to respond to being cancelled
// *       if (!pool.awaitTermination(60, TimeUnit.SECONDS))
// *           System.err.println("Pool did not terminate");
// *     }
// *   } catch (InterruptedException ie) {
// *     // (Re-)Cancel if current thread also interrupted
// *     pool.shutdownNow();
// *     // Preserve interrupt status
// *     Thread.currentThread().interrupt();
// *   }
// * }
// *
// * 内存一致性效果：
// * 操作在提交之前的螺纹Runnable或Callable任务到ExecutorService happen-before由任务采取的任何行动，
// * 这反过来又发生-之前结果通过检索Future.get() 。
// */
//public interface ExecutorService extends Executor {
//
//    /**
//     * 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
//     * 如果已经关闭，调用没有额外的作用。
//     *
//     * 此方法不等待以前提交的任务完成执行。 使用awaitTermination做到这一点。
//     *
//     * @throws SecurityException 如果安全管理器存在并关闭，
//     *                           则ExecutorService可能会操纵调用者不允许修改的线程，
//     *                           因为它不会保留RuntimePermission ("modifyThread")
//     *                           或安全管理器的checkAccess方法拒绝访问。
//     */
//    void shutdown();
//
//    /**
//     * 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
//     *
//     * 此方法不等待主动执行的任务终止。 使用awaitTermination做到这一点。
//     *
//     * 除了努力尝试停止处理积极执行任务之外，没有任何保证。
//     * 例如，典型的实现将通过Thread.interrupt()取消，所以无法响应中断的任何任务永远不会终止。
//     *
//     *
//     * @return 从未开始执行的任务列表
//     * @throws SecurityException
//     */
//    List<Runnable> shutdownNow();
//
//    /**
//     * 如果此执行程序已关闭，则返回 true。
//     */
//    boolean isShutdown();
//
//    /**
//     * 如果关闭后所有任务都已完成，则返回 true
//     * 请注意， isTerminated从不true ，除非shutdown或shutdownNow首先被调用。
//     */
//    boolean isTerminated();
//
//    /**
//     * 请求关闭、发生超时或者当前线程中断，
//     * 无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行
//     *
//     * @param timeout 等待的最长时间
//     * @param unit 超时参数的时间单位
//     * @return true如果此执行终止，
//     *          false ，如果超时终止前经过
//     * @throws InterruptedException 如果在等待时中断
//     */
//    boolean awaitTermination(long timeout, java.util.concurrent.TimeUnit unit)
//        throws InterruptedException;
//
//    /**
//     * 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。
//     * 提交具有返回值任务以执行，并返回代表任务待处理结果的Future。
//     * 未来的get方法将在成功完成后返回任务的结果。
//     *
//     * 如果您想立即阻止等待任务，您可以使用result = exec.submit(aCallable).get()
//     *
//     * 注意： Executors类包括一组方法，可以将一些其他常见的类似对象的对象，
//     *      例如PrivilegedAction转换为Callable表单，以便它们可以提交。
//     *
//     * @param task  提交的任务
//     * @param <T>  任务结果的类型
//     * @return 一个未来的代表，待完成任务
//     * @throws RejectedExecutionException 如果任务无法安排执行
//     * @throws NullPointerException 如果任务为空
//     */
//    <T> Future<T> submit(java.util.concurrent.Callable<T> task);
//
//    /**
//     * 提交一个可运行的任务执行，并返回一个表示该任务的未来。
//     * 未来的get方法将在成功完成后返回给定的结果。
//     * @param task 要提交的任务
//     * @param result 结果返回
//     * @param <T> 结果的类型
//     * @return 一个未来的代表，待完成任务
//     * @throws RejectedExecutionException  如果任务无法安排执行
//     * @throws NullPointerException 如果任务为空
//     */
//    <T> Future<T> submit(Runnable task, T result);
//
//    /**
//     * 提交一个可运行的任务执行，并返回一个表示该任务的未来。
//     * 未来的get方法将返回null 成功完成时。
//     *
//     * @param task 要提交的任务
//     * @return 一个未来的代表，待完成任务
//     * @throws RejectedExecutionException  如果任务无法安排执行
//     * @throws NullPointerException 如果任务为空
//     */
//    Future<?> submit(Runnable task);
//
//    /**
//     * 执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。
//     * 对于返回列表的每个元素，Future.isDone()都为true
//     * 请注意，完成的任务可能会正常终止或抛出异常。
//     * 如果在此操作正在进行中修改了给定的集合，则此方法的结果是未定义的。
//     *
//     * @param tasks 收集任务
//     * @param <T> 从任务返回的值的类型
//     * @return 表示任务的期货列表，按照给定任务列表的迭代器产生的顺序顺序，每个都已完成
//     * @throws InterruptedException 如果在等待时中断，在这种情况下未完成的任务被取消
//     * @throws NullPointerException  如果任务或其任何元素是 null
//     * @throws RejectedExecutionException 如果任何任务无法安排执行
//     */
//    <T> List<Future<T>> invokeAll(Collection<? extends java.util.concurrent.Callable<T>> tasks)
//        throws InterruptedException;
//
//    /**
//     * 执行给定的任务，当所有任务完成或超时期满时（无论哪个首先发生），返回保持任务状态和结果的 Future 列表。
//     * 对于返回列表的每个元素，Future.isDone()都为true。
//     * 退货后，尚未完成的任务将被取消。
//     *
//     * 请注意，完成的任务可能会正常终止或抛出异常。
//     * 如果在此操作正在进行中修改了给定的集合，则此方法的结果是未定义的。
//     *
//     * @param tasks 收集任务
//     * @param timeout  等待的最长时间
//     * @param unit 超时参数的时间单位
//     * @param <T> 从任务返回的值的类型
//     * @return 一个表示任务的期货列表，按照给定任务列表的迭代器生成的顺序。
//     *          如果操作没有超时，每个任务都会完成。 如果超时，其中一些任务将不会完成。
//     * @throws InterruptedException 如果在等待时中断，在这种情况下未完成的任务将被取消
//     * @throws NullPointerException  如果任务，其任何元素或单位是 null
//     * @throws RejectedExecutionException 如果任何任务无法安排执行
//     */
//    <T> List<Future<T>> invokeAll(Collection<? extends java.util.concurrent.Callable<T>> tasks,
//                                  long timeout, java.util.concurrent.TimeUnit unit)
//        throws InterruptedException;
//
//    /**
//     * 执行给定的任务，如果某个任务已成功完成（也就是未抛出异常），则返回其结果
//     * 正常或异常退货后，尚未完成的任务将被取消。
//     * 如果在此操作正在进行中修改了给定的集合，则此方法的结果是未定义的。
//     *
//     * @param tasks  收集任务
//     * @param <T> 从任务返回的值的类型
//     * @return 其中一个任务返回的结果
//     * @throws InterruptedException 如果等待中断
//     * @throws NullPointerException  如果任务或任何要执行的元素任务是 null
//     * @throws IllegalArgumentException 如果任务为空
//     * @throws ExecutionException  如果没有任务成功完成
//     * @throws RejectedExecutionException  如果不能安排执行任务
//     */
//    <T> T invokeAny(Collection<? extends java.util.concurrent.Callable<T>> tasks)
//        throws InterruptedException, ExecutionException;
//
//    /**
//     * 执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果
//     * 正常或异常退货后，尚未完成的任务将被取消。
//     * 如果在此操作正在进行中修改了给定的集合，则此方法的结果是未定义的。
//     *
//     * @param tasks 执行的任务列表
//     * @param timeout 等待的最长时间
//     * @param unit 等待的时间单位
//     * @param <T> 从任务返回的值的类型
//     * @return 其中一个任务返回的结果
//     * @throws InterruptedException  如果在等待时中断
//     * @throws NullPointerException 如果任务，单位或任何可执行的元素任务是 null
//     * @throws TimeoutException 如果在任务成功完成之前，给定的超时时间过去了
//     * @throws ExecutionException 如果没有任务成功完成
//     * @throws RejectedExecutionException 如果不能安排执行任务
//     */
//    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
//                    long timeout, TimeUnit unit)
//        throws InterruptedException, ExecutionException, TimeoutException;
//}
