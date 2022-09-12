package com.lyp.learn.base.jucatguigu.cf05;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liyapu
 * @date 2022-09-12 15:03
 * @description CompletableFuture 常用方法
 * <p>
 * 1. 获得结果和触发计算
 * 获取结果：
 * public T get()
 * public T get(long timeout,TimeUnit unit)
 * public T join()
 * public T getNow(T valueIfAbsent)
 * 主动触发计算
 * public boolean complete(T value)
 * 是否打断 get方法立即返回括号值
 * <p>
 * 2. 对计算结果进行处理
 * thenApply  计算结果存在依赖关系，这两个线程串行化
 * 异常相关：由于存在依赖关系(当前步错，不走下一步)，当前步骤有异常的话就叫停
 * handle 计算结果存在依赖关系，这两个线程串行化
 * 异常相关：有异常也可以往下一步走，根据带的异常参数可以进一步处理
 * <p>
 * 3. 对计算结果进行消费
 * 接收任务的处理结果，并消费处理，无返回结果
 * thenAccept
 * <p>
 * 对比：
 * thenRun(Runnable runnable)  任务A执行完执行B,并且B不需要A的结果
 * thenAccept(Consumer action) 任务A执行完执行B,B需要A的结果，但是任务B无返回值
 * thenApply(Function fn)      任务A执行完执行B,B需要A的结果，同时任务B有返回值
 * <p>
 * 4. 对计算速度进行选用
 * applyToEither  谁快用谁
 * <p>
 * 5. 对计算结果进行合并
 * 两个CompletionStage任务都完成后，最终能把两个任务的结果一起交给thenCombine来处理
 * 先完成的先等着，等待其它分支任务
 * thenCombine
 */
public class CompletableFutureApiDemo {

    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        //1. 获得结果和触发计算
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();

        //2. 对计算结果进行处理
        //test10();
        //test11();
        //test12();
        //test13();

        //3. 对计算结果进行消费
        //test20();
        //test21();
        //test22();

        //4. 对计算速度进行选用
        //test30();

        //5. 对计算结果进行合并
        //test40();
        test41();
    }

    private static void test1() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        try {
            /*
              get() 不见不散，等着拿结果
             */
            //System.out.println(cf.get());

            /*
               get(long timeout, TimeUnit unit) 过时不候，超时报 TimeoutException 异常
             */
            System.out.println("test1 ---" + cf.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    private static void test2() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        //等待获取，不抛检查型异常
        //System.out.println(cf.join());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test2 ---" + cf.getNow("获取不到的默认值"));
        /*
           getNow(T valueIfAbsent) 没有计算完成的情况下，给我一个替代结果
                                   立即获取结果不阻塞
                                          计算完，返回计算完成后的结果
                                          没算完，返回设定的valueIfAbsent值
         */
    }

    private static void test3() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        System.out.println("test3 ---" + cf.getNow("获取不到的默认值"));
    }

    private static void test4() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        //cf.complete("打断计算，使用我现在给的结果");
        //System.out.println("test4 ---" + cf.join());

        String result = cf.complete("   打断计算，使用我现在给的结果  ") + cf.join();
        System.out.println("test4 ---" + result);
    }

    private static void test5() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "   >>>>hello";
        });

        //cf.complete("打断计算，使用我现在给的结果");
        //System.out.println("test4 ---" + cf.join());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结果已经计算好了，不会使用complete 给的结果，直接返回原来的结果
        String result = cf.complete("   打断计算，使用我现在给的结果   ") + cf.join();
        System.out.println("test4 ---" + result);
    }

    public static void test10() {
        Integer num = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步");
            return 1;
        }).thenApply(result -> {
            // result 是上一步计算的结果
            System.out.println("第二步");
            return result + 2;
        }).thenApply(r -> {
            System.out.println("第三步");
            return r + 3;
        }).whenComplete((result, exceptionn) -> {
            if (Objects.isNull(exceptionn)) {
                System.out.println("无异常");
            } else {
                System.out.println("有异常------" + exceptionn.getMessage());
            }
        }).join();
        System.out.println("test10 ---->" + num);
    }

    /**
     * 第二步异常了，第三步就没有走
     */
    public static void test11() {
        Integer num = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步");
            return 1;
        }).thenApply(result -> {
            // result 是上一步计算的结果
            System.out.println("第二步");
            int i = 10 / 0;
            return result + 2;
        }).thenApply(r -> {
            System.out.println("第三步");
            return r + 3;
        }).whenComplete((r, e) -> {
            if (Objects.isNull(e)) {
                System.out.println("whenComplete 无异常");
            } else {
                System.out.println("whenComplete 有异常------" + e.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("exceptionally " + e.getMessage());
            //return 1000000;
            return null;
        }).join();
        System.out.println("test11 ---->" + num);
    }

    /**
     * 指定线程池
     */
    public static void test12() {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        Integer num = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步");
            return 1;
        }, executorService).thenApply(result -> {
            // result 是上一步计算的结果
            System.out.println("第二步");
            int i = 10 / 0;
            return result + 2;
        }).thenApply(r -> {
            System.out.println("第三步");
            return r + 3;
        }).whenComplete((r, e) -> {
            if (Objects.isNull(e)) {
                System.out.println("whenComplete 无异常");
            } else {
                System.out.println("whenComplete 有异常------" + e.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("exceptionally " + e.getMessage());
            return 1000000;
        }).join();
        System.out.println("test12 ---->" + num);
    }

    /**
     * 第二步异常了，第三步继续走
     * handle
     */
    public static void test13() {
        Integer num = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步");
            return 1;
        }).handle((result, ex) -> {
            // result 是上一步计算的结果
            System.out.println("第二步");
            int i = 10 / 0;
            return result + 2;
        }).handle((result, ex) -> {
            System.out.println("第三步");
            return result + 3;
        }).whenComplete((r, e) -> {
            if (Objects.isNull(e)) {
                System.out.println("whenComplete 无异常");
            } else {
                System.out.println("whenComplete 有异常------" + e.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("exceptionally " + e.getMessage());
            return 1000000;
        }).join();
        System.out.println("test13 ---->" + num);
    }

    public static void test20() {
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(r -> {
            return r + 2;
        }).thenAccept(r -> System.out.println("test14 r ---" + r));
    }

    public static void test21() {
        System.out.println(CompletableFuture.supplyAsync(() -> "任务A").thenRun(() -> {
        }).join());
        System.out.println();

        System.out.println(CompletableFuture.supplyAsync(() -> "任务A").thenAccept(r -> System.out.println(r)).join());
        System.out.println();

        System.out.println(CompletableFuture.supplyAsync(() -> "任务A").thenApply(r -> r + " 任务B").join());
    }

    /**
     * 线程池使用
     * 1. 没有传入自定义线程池，都用默认线程池ForkJoinPool
     * 2. 传入了一个自定义线程池
     * 如果你执行第一个任务的时候，传入了一个自定义线程池
     * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池
     * 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
     * 3.备注
     * 有可能处理太快，系统优化切换原则，直接使用main线程处理
     * <p>
     * 其它如：thenAccept和thenAcceptAsync, thenApply和thenApplyAsync等，它们之间的区别也是同理
     */
    public static void test22() {
        //使用 ForkJoinPool.commonPool
        //CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
        //    System.out.println("1号任务\t " + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    return "aaa";
        //}).thenRun(() -> {
        //    System.out.println("2号任务\t " + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}).thenRun(() -> {
        //    System.out.println("3号任务\t " + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}).thenRun(() -> {
        //    System.out.println("4号任务\t" + Thread.currentThread().getName());
        //}).thenRun(() -> {
        //    System.out.println("5号任务\t" + Thread.currentThread().getName());
        //});
        //
        //final Void join = cf.join();

        //使用自定义 pool-1 线程池和 ForkJoinPool.commonPool 线程池
        //CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
        //    System.out.println("1号任务\t" + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    return "aaa";
        //}, executorService).thenRunAsync(() -> {
        //    System.out.println("2号任务\t" + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}).thenRunAsync(() -> {
        //    System.out.println("3号任务\t" + Thread.currentThread().getName());
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}).thenRunAsync(() -> {
        //    System.out.println("4号任务\t" + Thread.currentThread().getName());
        //}).thenRunAsync(() -> {
        //    System.out.println("5号任务\t" + Thread.currentThread().getName());
        //});
        //
        //final Void join = cf.join();

        //使用自定义 pool-1 线程池
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("1号任务\t" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aaa";
        }, executorService).thenRun(() -> {
            System.out.println("2号任务\t" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("3号任务\t" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("4号任务\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            System.out.println("5号任务\t" + Thread.currentThread().getName());
        });

        final Void join = cf.join();
    }

    public static void test30() {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("playA come in ......");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("playB come in ......");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });

        CompletableFuture<String> result = playA.applyToEither(playB, cfResult -> {
            return cfResult + " is winer";
        });

        System.out.println(Thread.currentThread().getName() + " \t " + result.join());
    }


    public static void test40() {
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

        final CompletableFuture<Integer> cfResult = cf1.thenCombine(cf2, (cf1Result, cf2Result) -> {
            System.out.println("----开始两个结果合并----");
            return cf1Result + cf2Result;
        });

        System.out.println(cfResult.join());
    }

    public static void test41() {
        final CompletableFuture<Integer> cfResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("----->>> come in 1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("----->>> come in 2");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }), (x, y) -> {
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("----->>> come in 3");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        }), (a, b) -> {
            return a + b;
        });
        System.out.println("----主线程结束.");
        System.out.println(cfResult.join());
    }


}
