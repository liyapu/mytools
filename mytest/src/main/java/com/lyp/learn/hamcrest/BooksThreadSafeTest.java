package com.lyp.learn.hamcrest;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.RunsInThreads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * 线程安全测试
 */
public class BooksThreadSafeTest {


    @Test
    public void test01() {
        Books books = new Books();
        String title = "Elegant Objects";
        int id = books.add(title);
        assert books.title(id).equals(title);
    }

    /**
     * 多线程测试
     *   使用 cyclicBarrier
     *
     *   为了使Books类成为线程安全的，我们只需要向其方法add（）中同步添加就可以了
     */
    @Test
    public void test02() throws ExecutionException, InterruptedException {
        Books books = new Books();
        //设置开启的线程数
        int threads = 50;

        //让线程同时开始
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads);
        ExecutorService service = Executors.newFixedThreadPool(threads);

        // 等待开启的子线程都执行完成之后，再继续执行主线程，打印 books.map ,断言是否正确
        Collection<Future<Integer>> futures = new ArrayList<>(threads);


        for (int i = 1; i <= threads; i++) {
            final String title = String.format("bt %d ", i);
            futures.add(service.submit(() -> {

                System.out.println(Thread.currentThread().getName() + " 已准备");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int id = books.add(title);
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                return id;

            }));
        }

        Set<Integer> ids = new HashSet<>();
        for (Future<Integer> f : futures) {
            ids.add(f.get());
        }

        books.map.forEach((k, v) -> System.out.println(k + "---->" + v));
        ids.stream().forEach(System.out::println);
        System.out.println("books map length = " + books.map.size());

        assertThat(books.map.size(), equalTo(threads));
    }

    /**
     * 多线程测试
     *   使用 CountDownLatch
     */
    @Test
    public void test03() throws ExecutionException, InterruptedException {
        Books books = new Books();
        //设置开启的线程数
        int threads = 3;

        //让线程同时开始
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(threads);

        // 等待开启的子线程都执行完成之后，再继续执行主线程，打印 books.map ,断言是否正确
        Collection<Future<Integer>> futures = new ArrayList<>(threads);


        for (int i = 1; i <= threads; i++) {
            final String title = String.format("bt %d ", i);
            futures.add(service.submit(() -> {

                System.out.println(Thread.currentThread().getName() + " 已准备");
                try {
                    latch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int id = books.add(title);
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                return id;

            }));
        }

        latch.countDown();

        Set<Integer> ids = new HashSet<>();
        for (Future<Integer> f : futures) {
            ids.add(f.get());
        }

        books.map.forEach((k, v) -> System.out.println(k + "---->" + v));
        ids.stream().forEach(System.out::println);
        System.out.println("books map length = " + books.map.size());

        assertThat(books.map.size(), equalTo(threads));
    }


}





























