package com.lyp.learn.base.demo.pk13;//package com.lyp.learn.demo.pk13;
//
//import java.util.concurrent.CancellationException;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeoutException;
//
///**
// * A Future代表异步计算的结果。 提供方法来检查计算是否完成，等待其完成，并检索计算结果。
// * 结果只能在计算完成后使用方法get进行检索，如有必要，阻塞，直到准备就绪。
// * 取消由cancel方法执行。 提供其他方法来确定任务是否正常完成或被取消。 计算完成后，不能取消计算。
// * 如果您想使用Future ，以便不可撤销，但不提供可用的结果，
// * 则可以声明Future<?>表格的类型，并返回null作为基础任务的结果。
// *
// * 示例使用 （请注意，以下课程都是化妆品。）
// * interface ArchiveSearcher {
// *      String search(String target);
// * }
// *
// *
// * class App {
// *   ExecutorService executor = ...
// *   ArchiveSearcher searcher = ...
// *   void showSearch(final String target) throws InterruptedException {
// *     Future<String> future
// *       = executor.submit(new Callable<String>() {
// *         public String call() {
// *             return searcher.search(target);
// *         }});
// *     displayOtherThings(); // do other things while searching
// *     try {
// *       displayText(future.get()); // use future
// *     } catch (ExecutionException ex) {
// *          cleanup(); return;
// *     }
// *   }
// * }
// *
// * FutureTask类是实现Future ，实现Runnable ，所以可以由Executor执行。
// * 例如，以上建设与submit可以替换为：
// * FutureTask<String> future =
// *   new FutureTask<String>(new Callable<String>() {
// *     public String call() {
// *       return searcher.search(target);
// *   }});
// * executor.execute(future);
// *
// * 内存一致性影响：
// * 异步运算采取的操作happen-before操作在另一个线程中相应的Future.get()
// */
//public interface Future<V> {
//
//    /**
//     * 尝试取消执行此任务。
//     * 如果任务已经完成，已经被取消或由于某种其他原因而无法取消，则此尝试将失败。
//     * 如果成功，并且当cancel时此任务尚未启动，则此任务不应运行。
//     * 如果任务已经开始，那么mayInterruptIfRunning参数确定是否执行此任务的线程应该以试图停止任务被中断。
//     * 此方法返回后，后续调用isDone()将始终返回true 。
//     * 随后调用isCancelled()总是返回true 如果此方法返回true
//
//     *
//     * @param mayInterruptIfRunning true如果执行该任务的线程应该被中断;
//     *                              否则，正在进行的任务被允许完成
//     * @return false如果任务无法取消，通常是因为它已经正常完成; true否则
//     */
//    boolean cancel(boolean mayInterruptIfRunning);
//
//    /**
//     * 如果此任务在正常完成之前被取消，则返回 true 。
//     */
//    boolean isCancelled();
//
//    /**
//     * 返回true如果任务已完成。
//     * 完成可能是由于正常终止，异常或取消 - 在所有这些情况下，此方法将返回true 。
//     */
//    boolean isDone();
//
//    /**
//     * 等待计算完成，然后检索其结果。
//     *
//     * @return 计算结果
//     * @throws CancellationException 如果计算被取消
//     * @throws ExecutionException 如果计算引发异常
//     * @throws InterruptedException 如果当前线程在等待时中断
//     */
//    V get() throws InterruptedException, ExecutionException;
//
//    /**
//     * 如果需要等待最多在给定的时间计算完成，然后检索其结果（如果可用）。
//     *
//     * @param timeout 等待的最长时间
//     * @param unit 超时参数的时间单位
//     * @return 计算结果
//     * @throws CancellationException if the computation was cancelled
//     * @throws ExecutionException if the computation threw an exception
//     * @throws InterruptedException if the current thread was interrupted
//     * while waiting
//     * @throws TimeoutException if the wait timed out
//     */
//    V get(long timeout, TimeUnit unit)
//        throws InterruptedException, ExecutionException, TimeoutException;
//}
