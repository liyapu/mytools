package com.lyp.learn.utils.p;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 分页处理工具
 */
public class PageUtil {

    /**
     * 分页执行consumer
     *
     * @param consumer
     * @param paramList
     * @param pageSize
     * @param <T>
     */
    public static <T> void doConsumerByPage(Consumer<List<T>> consumer, List<T> paramList, int pageSize) {
        if (CollectionUtils.isEmpty(paramList)) {
            return;
        }
        List<List<T>> partition = ListUtils.partition(ListUtils.emptyIfNull(paramList), pageSize);
        for (List<T> each : partition) {
            consumer.accept(each);
        }
    }

    /**
     * 分页执行 consumer 并发执行
     *
     * @param consumer
     * @param paramList
     * @param pageSize
     * @param executorService
     * @param <T>
     */
    public static <T> void doConsumerByPage(Consumer<List<T>> consumer, List<T> paramList, int pageSize, ExecutorService executorService) {
        if (CollectionUtils.isEmpty(paramList)) {
            return;
        }
        List<List<T>> partition = ListUtils.partition(ListUtils.emptyIfNull(paramList), pageSize);
        List<CompletableFuture<Void>> futures = Lists.newArrayList();
        for (List<T> each : partition) {
            futures.add(CompletableFuture.runAsync(() -> consumer.accept(each), executorService));
        }
        futures.forEach(CompletableFuture::join);
    }


    /**
     * 分页执行function
     *
     * @param function
     * @param paramList 参数列表
     * @param pageSize  分页大小
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> doFunctionByPage(Function<List<T>, List<R>> function, List<T> paramList,
                                                  int pageSize) {
        if (CollectionUtils.isEmpty(paramList)) {
            return Collections.emptyList();
        }
        List<R> result = Lists.newArrayList();
        List<List<T>> partition = ListUtils.partition(ListUtils.emptyIfNull(paramList), pageSize);
        for (List<T> each : partition) {
            List<R> r = function.apply(each);
            if (CollectionUtils.isNotEmpty(r)) {
                result.addAll(r);
            }
        }
        return result;
    }

    /**
     * 分页执行function 多线程执行
     *
     * @param function
     * @param paramList
     * @param pageSize
     * @param executorService
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> doFunctionByPage(Function<List<T>, List<R>> function, List<T> paramList,
                                                  int pageSize, ExecutorService executorService) {
        if (CollectionUtils.isEmpty(paramList)) {
            return Collections.emptyList();
        }
        List<R> result = Lists.newArrayList();
        List<List<T>> partition = ListUtils.partition(ListUtils.emptyIfNull(paramList), pageSize);
        List<CompletableFuture<List<R>>> futures = Lists.newArrayList();
        for (List<T> each : partition) {
            futures.add(CompletableFuture.supplyAsync(() -> function.apply(each), executorService));
        }
        futures.forEach(x -> {
            List<R> r = x.join();
            if (CollectionUtils.isNotEmpty(r)) {
                result.addAll(r);
            }
        });
        return result;
    }

}
