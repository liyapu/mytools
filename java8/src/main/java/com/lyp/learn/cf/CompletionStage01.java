package com.lyp.learn.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2021-10-31 18:52
 * @desc https://www.cnblogs.com/txmfz/p/11266411.html
 *        Java并发包之阶段执行之CompletionStage接口
 *
 * 前言
 * CompletionStage是Java8新增得一个接口，用于异步执行中的阶段处理，其大量用在Lambda表达式计算过程中，目前只有CompletableFuture
 * 一个实现类，但我先从这个接口的方法开始介绍，为了举例说明这些接口方法的使用，会用到部分CompletableFuture的方法，下一步再详细的介绍CompletableFuture。
 *
 * CompletionStage
 * 定义了一组接口用于在一个阶段执行结束之后，要么继续执行下一个阶段，要么对结果进行转换产生新的结果等等，一般来说要执行下一个阶段都需要上一个阶段正常完成，当然这个类也提供了对异常结果的处理接口。
 * CompletionStage只定义了一组基本的接口，其实现类还可据此扩展出更丰富的方法。
 *
 * 方法概述
 * CompletionStage的接口方法可以从多种角度进行分类，从最宏观的横向划分，CompletionStage的接口主要分三类：
 * 一、产出型或者函数型：就是用上一个阶段的结果作为指定函数的参数执行函数产生新的结果。这一类接口方法名中基本都有apply字样，接口的参数是(Bi)Function类型。
 *
 * 二、消耗型或者消费型：就是用上一个阶段的结果作为指定操作的参数执行指定的操作，但不对阶段结果产生影响。这一类接口方法名中基本都有accept字样，接口的参数是(Bi)Consumer类型。
 *
 * 三、不消费也不产出型：就是不依据上一个阶段的执行结果，只要上一个阶段完成（但一般要求正常完成），就执行指定的操作，且不对阶段的结果产生影响。这一类接口方法名中基本都有run字样，接口的参数是Runnable类型。
 *
 * 还有一组特别的方法带有compose字样，它以依赖阶段本身作为参数而不是阶段产生的结果进行产出型（或函数型）操作。
 *
 * 在以上三类横向划分方法的基础上，又可以按照以下的规则对这些接口方法进行纵向的划分：
 * 一、多阶段的依赖：一个阶段的执行可以由一个阶段的完成触发，或者两个阶段的同时完成，或者两个阶段中的任何一个完成。
 *
 * 方法前缀为then的方法安排了对单个阶段的依赖。
 * 那些由完成两个阶段而触发的，可以结合他们的结果或产生的影响，这一类方法带有combine或者both字样。
 * 那些由两个阶段中任意一个完成触发的，不能保证哪个的结果或效果用于相关阶段的计算，这类方法带有either字样。
 * 二、按执行的方式：阶段之间的依赖关系控制计算的触发，但不保证任何特定的顺序。因为一个阶段的执行可以采用以下三种方式之一安排：
 *
 * 默认的执行方式。所有方法名没有以async后缀的方法都按这种默认执行方式执行。
 * 默认的异步执行。所有方法名以async为后缀，但没有Executor参数的方法都属于此类。
 * 自定义执行方式。所有方法名以async为后缀，并且具有Executor参数的方法都属于此类。
 * 默认的执行方式（包括默认的异步执行）的执行属性由CompletionStage的实现类指定例如CompletableFuture，而自定义的执行方式的执行属性由传入的Executor
 * 指定，这可能具有任意的执行属性，甚至可能不支持并发执行，但还是被安排异步执行。
 *
 * 三、按上一个阶段的完成状态：无论触发阶段是正常完成还是异常完成，都有两种形式的方法支持处理。
 *
 * 不论上一个阶段是正常还是异常完成：
 * whenComplete方法可以在上一个阶段不论以何种方式完成的处理，但它是一个消费型接口，即不对整个阶段的结果产生影响。
 * handle前缀的方法也可以在上一个阶段不论以何种方式完成的处理，它是一个产出型（或函数型）接口，既可以由上一个阶段的异常产出新结果，也可以其正常结果产出新结果，使该结果可以由其他相关阶段继续进一步处理。
 * 上一个阶段是异常完成的时候执行：exceptionally方法可以在上一个阶段以异常完成时进行处理，它可以根据上一个阶段的异常产出新的结果，使该结果可以由其他相关阶段继续进一步处理。
 * CompletionStage的异常规则
 * 除了whenComplete不要求其依赖的阶段是正常完成还是异常完成，以及handle前缀的方法只要求其依赖的阶段异常完成之外，其余所有接口方法都要求其依赖的阶段正常完成。
 *
 * 如果一个阶段的执行由于一个(未捕获的)异常或错误而突然终止，那么所有要求其完成的相关阶段也将异常地完成，并通过CompletionException包装其具体异常堆栈。
 * 如果一个阶段同时依赖于两个阶段，并且两个阶段都异常地完成，那么CompletionException可以对应于这两个异常中的任何一个。
 * 如果一个阶段依赖于另外两个阶段中的任何一个，并且其中只有一个异常完成，则不能保证依赖阶段是正常完成还是异常完成。
 * 在使用方法whenComplete的情况下，当提供的操作本身遇到异常时，如果前面的阶段没有异常完成，则阶段将以其异常作为原因异常完成。
 * 所有方法都遵循上述触发、执行和异常完成规范，此外，虽然用于传递一个表示完成结果的参数（也就是说，对于T类型的参数）可以为null，但是如果为其它任何参数传递null都将导致NullPointerException。
 * 此接口不定义用于初始创建、强制正常或异常完成、探测完成状态或结果或等待阶段完成的方法。CompletionStage的实现类可以提供适当的方法来实现这些效果。方法 toCompletableFuture
 * 通过提供一个公共转换类型，支持该接口的不同实现之间的互操作性。
 */
public class CompletionStage01 {
    /**
     * 一、根据阶段正常完成结果的产出型（或者叫函数型）：
     * //依赖单个阶段
     * public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);     // 默认执行方式
     * public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);// 默认的异步执行方式
     * public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn,Executor executor); //自定义的执行方式
     *
     * //依赖两个阶段都完成
     * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super
     * U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,?
     * super U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,?
     * super U,? extends V> fn, Executor executor);
     *
     * //依赖两个阶段中的任何一个完成
     * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
     * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
     * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,
     * Executor executor);
     *
     * 这一类方法都由上一阶段（或者两个阶段，或者两个阶段中的任意一个）的正常完成结果触发，然后以该结果执行给定的函数，产出新的结果。这里把异步执行的两者形式也列举出来了。
     *
     * 以下是使用示例，运用了CompletionStage实现类CompletableFuture，这里忽略Async版本的异步方法：
     *
     *
     *  这些示例展示了根据一个阶段的结果、两个阶段的结果以及两个阶段中最先完成的结果进行转换，并返回新的结果。
     *  第一个和第一个示例结果都是"hello world"，其中第二个示例不论两个阶段谁先完成，参数s1都是"hello"，参数s2都是"world'。
     *  第三个示例，applyToEither依赖两个阶段谁最先完成，其结果有时候是"hello Tom"，有时候是"hello John"
     */

    @Test
    public void thenApply() {
        CompletableFuture<String> stage = CompletableFuture.supplyAsync(() -> "hello");

        String result = stage.thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    @Test
    public void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    @Test
    public void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tom";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "John";
        }), s -> "hello " + s).join();
        System.out.println(result);
    }

    /**
     * 二、根据阶段正常完成结果的消费型：
     * //依赖单个阶段
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);
     *
     * //依赖两个阶段都完成
     * public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ?
     * super U> action);
     * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,
     * ? super U> action);
     * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,
     * ? super U> action, Executor executor);
     *
     * //依赖两个阶段中的任何一个完成
     * public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
     * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action);
     * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action,
     * Executor executor);
     *
     * 这一类方法都由上一阶段（或者两个阶段，或者两个阶段中的任意一个）正常完成的结果触发，然后以该结果执行给定的操作action，但不会对阶段的结果进行影响。这里把异步执行的两者形式也列举出来了。
     *
     * 以下是使用示例，运用了CompletionStage实现类CompletableFuture，这里忽略Async版本的异步方法：
     *
     * 示例展示了根据一个阶段的结果、两个阶段的结果以及两个阶段中最先完成的结果进行消耗，并没有返回值。acceptEither的示例中，
     * 依赖两个阶段谁最先完成，打印结果有时候是"hello tom"，有时候是"hello john"。
     */
    @Test
    public void thenAccept(){
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s+" world"));
    }

    @Test
    public void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

        while (true){} //等待打印出结果
    }

    @Test
    public void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello john";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello tom";
        }), System.out::println);

        while (true){} //等待打印出结果
    }

    /**
     * 三、只要求依赖的阶段正常完成的不消耗也不产出型：
     * //依赖单个阶段
     * public CompletionStage<Void> thenRun(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor);
     *
     *
     * //依赖两个阶段都完成
     * public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
     * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
     * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor);
     *
     *
     * //依赖两个阶段中的任何一个完成
     * public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action);
     * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
     * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor);
     *
     * 这一类方法只要求上一阶段（或者两个阶段，或者两个阶段中的任意一个）正常完成，并不关心其具体结果，从而执行指定的操作cation，但不会对阶段的结果进行影响。这里把异步执行的两者形式也列举出来了。
     *
     * 以下是使用示例，运用了CompletionStage实现类CompletableFuture，这里忽略Async版本的异步方法：
     *
     * 示例展示了只要依赖的上一个阶段（或者两个阶段，或者两个阶段中的任意一个）正常完成，就会执行指定的操作，并且不会依赖上一个阶段（或者两个阶段，或者两个阶段中的任意一个最先完成的阶段）的结果。
     * 三个示例都回打印出"hello world"
     */
    @Test
    public void thenRun(){
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        while (true){}
    }

    @Test
    public void runAfterBoth(){
        //不关心这两个CompletionStage的结果，只关心这两个CompletionStage正常执行完毕，之后在进行操作（Runnable）。
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));
        while (true){}
    }

    @Test
    public void runAfterEither() {
        //两个CompletionStage，任何一个正常完成了都会执行下一步的操作（Runnable）。
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));
        while (true) {
        }
    }

    /**
     * 四、根据正常完成的阶段本身而不是其结果的产出型：
     * 以上产出型的方法都是应用依赖阶段的正常执行结果，CompletionStage提供了一组以阶段本身为依据的产出型接口方法：
     *  public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
     *  public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn);
     *  public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor);
     *
     *  其阶段最终结果打印出"hello world"，thenCompose和thenCombine很相似，但看起来thenCompose比thenCombine更简洁。
     */
    @Test
    public void thenCompose(){
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + " world";
        })).join();

        System.out.println(result);
    }

    /**
     * 五、不论阶段正常还是异常完成的消耗型：
     * public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
     * public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
     * public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executorexecutor);
     * <p>
     * 上面的一、二、三、四种类型的方法都需要依赖的阶段正常完成，如果异常完成将导致上面介绍的四种类型的方法最终也异常完成，不会得出我们希望的结果。
     * 而whenComplete则不论依赖的上一个阶段是正常完成还是异常完成都不会影响它的执行，
     * 但它是一个消耗型接口，即不会对阶段的原来结果产生影响，结合thenCombine综合whenComplete的示例如下：
     * <p>
     * 上例中，whenComplete的参数s表示通过thenCombine正常完成的结果，如果没有异常的话，该参数的值就是"hello world"，
     * t参数是Throwable类型的异常，因为thenCombine同时依赖两个阶段的正常完成，
     * 此时第一个阶段中抛出了异常，所以不会执行thenCombine指定的函数，即不会打印"combine result"，
     * whenComplete不论是否前面的阶段是否出现异常都会执行，最后打印出这样的信息：
     * <p>
     * 如果将上例中的thenCombine换成applyToEither，那么如果两个阶段中最先完成的阶段是异常完成，那么其结果与上面不变，还是异常结束；
     * 如果最先完成的阶段是正常完成（把抛异常之前那个Thread.sleep(3000) 改成 Thread.sleep(2000) ）的话，那么整个阶段将不会出现异常，
     * whenComplete的参数s就是"hello world"，t为null。
     */
    @Test
    public void thenCombine2(){
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //if (1 == 1) {
            //    throw new RuntimeException("测试一下异常情况");
            //}

            return "hello ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("return world...");  //会执行
            return "world";
        }), (s1, s2) -> {
            String s = s1 + "--- " + s2;   //并不会执行
            System.out.println("combine result :"+s); //并不会执行
            return s;
        }).whenComplete((s, t) -> {
            System.out.println("current result ==== is :" + s);
            if (t != null) {
                System.out.println("阶段执行过程中存在异常：");
                t.printStackTrace();
            }
        }).join();

        System.out.println("final result:" + result); //并不会执行
    }

    /**
     * 举一个生活上的例子，假如我们需要出去旅游，需要完成三个任务：
     * <p>
     * 任务一：订购航班
     * 任务二：订购酒店
     * 任务三：订购租车服务
     * 很显然任务一和任务二没有相关性，可以单独执行。但是任务三必须等待任务一与任务二结束之后，才能订购租车服务。
     */
    @Test
    public void testCombine3() {
        //任务1：  订购航班
        final CompletableFuture<String> orderAirplane = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询航班");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购航班");
            return "航班信息";
        });

        //任务2：  订购酒店
        final CompletableFuture<String> orderHotel = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询酒店");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购酒店");
            return "酒店信息";
        });

        //任务3：任务1 和 任务2 都完成，才能去订购租车服务
        final CompletableFuture<String> hireCar = orderHotel
            .thenCombine(orderAirplane, (airplane, hotel) -> {
                System.out.println("根据航班+酒店 订购租车服务:::" + airplane + " --- " + hotel);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "租车服务";
            });

        System.out.println(hireCar.join());
    }


    /**
     * 六、不论阶段正常还是异常完成的产出型：
     * whenComplete是对不论依赖阶段正常完成还是异常完成时的消耗或者消费，即不会改变阶段的现状，
     * 而handle前缀的方法则是对应的产出型方法，即可以对正常完成的结果进行转换，也可以对异常完成的进行补偿一个结果，即可以改变阶段的现状。
     * <p>
     * //不论正常还是异常的产出型：
     * public <U> CompletionStage<U> handle (BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor);
     * <p>
     * handle的第一个参数s是上一个阶段的结果，t参数是Throwable类型的异常，这里上一个阶段如果没有抛出异常，那么最终打印的结果是"Tom"，
     * 现在通过handle对出现异常的情况进行了补偿返回John，所以上例最终其实打印的是"John"。
     */
    @Test
    public void handle() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //出现异常
            if (1 == 3) {
//            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "Tom";
        }).handle((s, t) -> {
            System.out.println("s -----" + s);
            if (t != null) { //出现异常了
                return "John";
            }
            return s; //这里也可以对正常结果进行转换
        }).join();
        System.out.println(result);
    }

    /**
     * 七、异常完成的产出型：
     * 第五、六两种类型的方法是对于不论依赖的阶段是正常完成还是异常完成的处理，CompletionStage还提供了一个仅当上一个阶段异常完成时的处理，并且可以修改阶段的结果：
     * <p>
     * public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
     * <p>
     * 可见exceptionally只有一个参数e，表示上一个节点的异常，只有上一个阶段异常完成才会被执行，
     * 以上示例在异常时返回了新的值"hello world"对出现异常的阶段进行了补偿，所以最终整个阶段不会出现异常，并打印出"hello world"。
     */
    @Test
    public void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            e.printStackTrace(); //e肯定不会null
            return "hello world"; //补偿返回
        }).join();
        System.out.println(result); //打印hello world
    }

    /**
     * 八、实现该接口不同实现之间互操作的类型转换方法：
     * public CompletableFuture<T> toCompletableFuture();
     * 返回一个与此阶段保持相同完成属性的CompletableFuture实例。如果此阶段已经是一个CompletableFuture，那么直接返回该阶段本身，
     * 否则此方法的调用可能等效于thenApply(x -> x)，但返回一个类型为CompletableFuture的实例。不选择实现该互操作性的CompletionStage实现，可能会抛出UnsupportedOperationException异常。
     */
}
