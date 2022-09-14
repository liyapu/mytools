package com.lyp.learn.cf;


import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * https://blog.csdn.net/sermonlizhi/article/details/123356877
 * <p>
 * CompletionStage接口定义了任务编排的方法，执行某一阶段，可以向下执行后续阶段。异步执行的，默认线程池是ForkJoinPool.commonPool()，
 * 但为了业务之间互不影响，且便于定位问题，强烈推荐使用自定义线程池。
 * <p>
 * 1.2 功能
 * 1.2.1 常用方法
 * <p>
 * 依赖关系
 * thenApply()：把前面任务的执行结果，交给后面的Function
 * thenCompose()：用来连接两个有依赖关系的任务，结果由第二个任务返回
 * <p>
 * and集合关系
 * thenCombine()：合并任务，有返回值
 * thenAcceptBoth()：两个任务执行完成后，将结果交给thenAcceptBoth处理，无返回值
 * runAfterBoth()：两个任务都执行完成后，执行下一步操作(Runnable类型任务)
 * <p>
 * or聚合关系
 * applyToEither()：两个任务哪个执行的快，就使用哪一个结果，有返回值
 * acceptEither()：两个任务哪个执行的快，就消费哪一个结果，无返回值
 * runAfterEither()：任意一个任务执行完成，进行下一步操作(Runnable类型任务)
 * <p>
 * 并行执行
 * allOf()：当所有给定的 CompletableFuture 完成时，返回一个新的 CompletableFuture
 * anyOf()：当任何一个给定的CompletableFuture完成时，返回一个新的CompletableFuture
 * <p>
 * 结果处理
 * whenComplete：当任务完成时，将使用结果(或 null)和此阶段的异常(或 null如果没有)执行给定操作
 * exceptionally：返回一个新的CompletableFuture，当前面的CompletableFuture完成时，它也完成，当它异常完成时，给定函数的异常触发这个CompletableFuture的完成
 * <p>
 * <p>
 * 1.2.2 异步操作
 * CompletableFuture提供了四个静态方法来创建一个异步操作：
 * <p>
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 * 这四个方法的区别：
 * runAsync() 以Runnable函数式接口类型为参数，没有返回结果，
 * supplyAsync() 以Supplier函数式接口类型为参数，返回结果类型为U；
 * Supplier接口的 get()是有返回值的(会阻塞)
 * 使用没有指定Executor的方法时，内部使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
 * 如果指定线程池，则使用指定的线程池运行。
 * 默认情况下CompletableFuture会使用公共的ForkJoinPool线程池，这个线程池默认创建的线程数是 CPU 的核数（也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置ForkJoinPool线程池的线程数）。
 * 如果所有CompletableFuture共享一个线程池，那么一旦有任务执行一些很慢的 I/O 操作，就会导致线程池中所有线程都阻塞在 I/O 操作上，从而造成线程饥饿，进而影响整个系统的性能。所以，强烈建议你要根据不同的业务类型创建不同的线程池，以避免互相干扰
 * <p>
 * <p>
 * 获取结果(join&get)
 * join()和get()方法都是用来获取CompletableFuture异步之后的返回值。
 * join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出。
 * get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
 * <p>
 * <p>
 * 结果处理
 * 当CompletableFuture的计算结果完成，或者抛出异常的时候，我们可以执行特定的 Action。主要是下面的方法：
 * <p>
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * <p>
 * Action的类型是BiConsumer<? super T,? super Throwable>，它可以处理正常的计算结果，或者异常情况。
 * 方法不以Async结尾，意味着Action使用相同的线程执行，而Async可能会使用其它的线程去执行(如果使用相同的线程池，也可能会被同一个线程选中执行)。
 * 这几个方法都会返回CompletableFuture，当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常
 */
public class CompletionStage02 {

    /**
     * 可以输出预期结果
     * <p>
     * https://www.proyy.com/6968286631614742535.html
     * allOf 的返回值是 CompletableFuture类型，这是因为 每个传入的 CompletableFuture 的返回值都可能不同，
     * 所以组合的结果是 无法用某种类型来表示的，索性返回 Void 类型。那么，如何获取每个 CompletableFuture 的执行结果呢？
     * <p>
     * 这里有个关键问题，因为allof没有返回值，所以通过theApply，给allFutures附上一个回调函数。
     * 在回调函数里面，以此调用么一个Future的get()/join()函数
     */
    @Test
    public void testALlOf01() {
        StringBuilder sb = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = messages.stream()
            .map(msg -> CompletableFuture.supplyAsync(() -> {
                return msg + "123";
            })).collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
            .whenComplete((v, th) -> {
                futures.forEach(cf -> sb.append(cf.join()));
            });

        System.out.println(sb);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加 回调函数 thenApply
     */
    @Test
    public void testALlOf02() {
        StringBuilder sb = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = messages.stream()
            .map(msg -> CompletableFuture.supplyAsync(() -> {
                String m = StringUtils.repeat(msg, 3);
                System.out.println("m = " + m);
                return m;
            })).collect(Collectors.toList());

        String strResult = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
            .thenApply(cf -> {
                String s = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList())
                    .stream()
                    .collect(Collectors.joining());
                System.out.println("s------" + s);
                sb.append(s);
                return s;
            }).join();

        System.out.println("sb = " + sb);
        System.out.println("strResult = " + strResult);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    public void testALlOf03() {
        StringBuilder sb = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = messages.stream()
            .map(msg -> CompletableFuture.supplyAsync(() -> {
                String m = StringUtils.repeat(msg, 3);
                System.out.println("m = " + m);
                return m;
            })).collect(Collectors.toList());

        String strResult = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
            .thenApply(cf -> {
                String s = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList())
                    .stream()
                    .collect(Collectors.joining());
                System.out.println("s------" + s);
                sb.append(s);
                return s;
            }).join();

        System.out.println("sb = " + sb);
        System.out.println("strResult = " + strResult);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加 回调函数 whenComplete
     */
    @Test
    public void testALlOf04() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<String> testList = Lists.newArrayList();
        testList.add("cf1");
        testList.add("cf2");

        long start = System.currentTimeMillis();
        CompletableFuture[] cfArr = testList.stream().
            map(t -> CompletableFuture
                .supplyAsync(() -> t + " tom ", executorService)
                .whenComplete((result, th) -> {
                    System.out.println("hello" + result);
                })).toArray(CompletableFuture[]::new);
        // 开始等待所有任务执行完成
        System.out.println("start block");
        CompletableFuture.allOf(cfArr).join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));

    }

    /**
     * 获取 allOf 的结果
     */
    @Test
    public void testAllOf05() {
        final CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        final CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        final CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 30;
        });

        List<CompletableFuture<Integer>> cfList = Lists.newArrayList(cf1, cf2, cf3);

        final CompletableFuture<List<Integer>> allCf = CompletableFuture
            .allOf(cfList.toArray(new CompletableFuture[cfList.size()]))
            .thenApply(v -> {
                return cfList.stream()
                    .map(cf -> cf.join())
                    .collect(Collectors.toList());
            });

        final CompletableFuture<Integer> cfSumResult = allCf.thenApply(intList -> intList.stream()
            .mapToInt(Integer::valueOf)
            .sum());
        System.out.println("cfSumResult = " + cfSumResult.join());
    }

    @Test
    public void testAllOf06() {
        final CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        final CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        final CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 30;
        });

        List<CompletableFuture<Integer>> cfList = Lists.newArrayList(cf1, cf2, cf3);

        //final Integer sumResult = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()]))
        final Integer sumResult = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]))
            .thenApply(v -> cfList.stream()
                .map(CompletableFuture::join)
                .mapToInt(Integer::valueOf)
                .sum()
            ).join();

        System.out.println("sumResult = " + sumResult);
    }

    @Test
    public void testAllOf07() {
        final CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        final CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        final CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 30;
        });

        //直接转成 array
        final CompletableFuture<Integer>[] cfArrays = Lists.newArrayList(cf1, cf2, cf3)
            .toArray(new CompletableFuture[0]);

        final Integer sumResult = CompletableFuture.allOf(cfArrays)
            .thenApply(v -> Arrays.stream(cfArrays)
                .map(CompletableFuture::join)
                .mapToInt(Integer::valueOf)
                .sum()
            ).join();

        System.out.println("sumResult = " + sumResult);
    }


    /**
     * anyOf
     * <p>
     * anyOf 的含义是只要有任意一个 CompletableFuture 结束，就可以做 接下来的事情，而无须像 AllOf 那样，等待所有的 CompletableFuture 结束。
     * 但由于每个 CompletableFuture 的返回值类型都可能不同，任意一个， 意味着无法判断是什么类型，所以 anyOf 的返回值是 CompletableFuture类型。
     * <p>
     * 在该例子中，因为future1、future2、future3的返回值都是CompletableFuture，所以anyOf的返回的Object一定也是 String 类型。
     */
    @Test
    public void testAnyOf01() {
        final CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future1 结果";
        });

        final CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future2 结果";
        });

        final CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future3 结果";
        });

        final CompletableFuture<Object> cfResult = CompletableFuture.anyOf(cf1, cf2, cf3);
        System.out.println(cfResult.join());
    }
}
