package com.lyp.learn.utils.looputilcompletablefuturelearn.package02;

import com.google.common.collect.Lists;
import com.lyp.learn.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 循环获取全量数据
 * 源版修改 线程池为 ExecutorService executorService, 函数修改为 java 自带的函数式函数
 */
@Slf4j
public class LoopUtils {
    /**
     * 循环获取全部数据
     *
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc    获取 total 总条数
     * @param resultFunc   获取list集合
     * @param pageSize     pageSize
     */
    public static <T, R> List<R> getFullDataByTotalCount(BiFunction<Integer, Integer, T> responseFunc,
                                                         Function<T, Long> totalFunc,
                                                         Function<T, Collection<R>> resultFunc,
                                                         int pageSize) {
        return getFullData(responseFunc, totalFunc, resultFunc, pageSize, true, 0, "");
    }

    /**
     * 循环获取全部数据
     *
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc    获取 total 总条数
     * @param resultFunc   获取list集合
     * @param pageSize     pageSize
     * @param totalMax     total的最大值
     * @param msg          如果total超过totalMax，异常提示语
     */
    public static <T, R> List<R> checkTotalAndGetFullDataByTotalCount(BiFunction<Integer, Integer, T> responseFunc,
                                                                      Function<T, Long> totalFunc,
                                                                      Function<T, Collection<R>> resultFunc,
                                                                      int pageSize,
                                                                      long totalMax,
                                                                      String msg) {

        checkTotalParam(totalMax, msg);
        return getFullData(responseFunc, totalFunc, resultFunc, pageSize, true, totalMax, msg);
    }

    /**
     * 多线程 循环获取全部数据
     *
     * @param responseFunc    获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc       获取 total 总条数
     * @param resultFunc      获取list集合
     * @param pageSize        pageSize
     * @param executorService 线程池
     * @param second          线程等待的时间  秒为单位
     */
    public static <T, R> List<R> getFullDataByTotalCountWithMutiThread(BiFunction<Integer, Integer, T> responseFunc,
                                                                       Function<T, Long> totalFunc,
                                                                       Function<T, Collection<R>> resultFunc,
                                                                       int pageSize,
                                                                       ExecutorService executorService,
                                                                       int second) {
        return doCheckTotalAndGetFullDataByTotalCountWithMutiThread(responseFunc, totalFunc, resultFunc, pageSize, executorService, second, 0, "");
    }

    /**
     * 多线程 循环获取全部数据
     *
     * @param responseFunc    获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc       获取 total 总条数
     * @param resultFunc      获取list集合
     * @param pageSize        pageSize
     * @param executorService 线程池
     * @param second          线程等待的时间  秒为单位
     * @param totalMax        total的最大值
     * @param msg             如果total超过totalMax，异常提示语
     */
    public static <T, R> List<R> checkTotalAndGetFullDataByTotalCountWithMutiThread(BiFunction<Integer, Integer, T> responseFunc,
                                                                                    Function<T, Long> totalFunc,
                                                                                    Function<T, Collection<R>> resultFunc,
                                                                                    int pageSize,
                                                                                    ExecutorService executorService,
                                                                                    int second,
                                                                                    long totalMax,
                                                                                    String msg) {

        checkTotalParam(totalMax, msg);
        return doCheckTotalAndGetFullDataByTotalCountWithMutiThread(responseFunc, totalFunc, resultFunc, pageSize, executorService, second, totalMax, msg);
    }

    /**
     * 多线程 循环获取全部数据
     *
     * @param responseFunc    获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc       获取 total 总条数
     * @param resultFunc      获取list集合
     * @param pageSize        pageSize
     * @param executorService 线程池
     * @param second          线程等待的时间  秒为单位
     * @param totalMax        total的最大值
     * @param msg             如果total超过totalMax，异常提示语
     */
//    private static <T, R> List<R> doCheckTotalAndGetFullDataByTotalCountWithMutiThread(BiFunction<Integer, Integer, T> responseFunc,
//                                                                                       Function<T, Long> totalFunc,
//                                                                                       Function<T, Collection<R>> resultFunc,
//                                                                                       int pageSize,
//                                                                                       ExecutorService executorService,
//                                                                                       int second,
//                                                                                       long totalMax,
//                                                                                       String msg) {
//
//        List<R> resultList = new ArrayList<>();
//        int offset = 0;
//        try {
//            //获取response
//            T response = responseFunc.apply(offset, pageSize);
//            if (response == null) {
//                return resultList;
//            }
//            //获取总条数
//            long total = totalFunc.apply(response);
//            if (total == 0) {
//                return resultList;
//            }
//            checkTotal(totalMax, msg, total);
//            //获取结果集
//            Collection<R> list = resultFunc.apply(response);
//            if (CollectionUtils.isEmpty(list)) {
//                return resultList;
//            }
//            resultList.addAll(list);
//            //多线程查询
//            if (total > pageSize) {
//                List<R> totalList = getFullDataByMutiThread((offset2, limit2) -> {
//                    T response2 = responseFunc.apply(offset2, limit2);
//                    if (response2 == null) {
//                        return new ArrayList<>();
//                    } else {
//                        Collection<R> list2 = resultFunc.apply(response2);
//                        return list2 != null ? Lists.newArrayList(list2) : new ArrayList<>();
//                    }
//                }, total, pageSize, executorService, second, pageSize);
//                resultList.addAll(totalList);
//            }
//            return resultList;
//        } catch (Exception e) {
//            throw ConvertUtils.wrapGateWayException(e);
//        }
//    }
    private static <T, R> List<R> doCheckTotalAndGetFullDataByTotalCountWithMutiThread(BiFunction<Integer, Integer, T> responseFunc,
                                                                                       Function<T, Long> totalFunc,
                                                                                       Function<T, Collection<R>> resultFunc,
                                                                                       int pageSize,
                                                                                       ExecutorService executorService,
                                                                                       int second,
                                                                                       long totalMax,
                                                                                       String msg) {

        List<R> resultList = new ArrayList<>();
        int offset = 0;
        try {
            // 获取初始response
            T response = responseFunc.apply(offset, pageSize);
            if (response == null) {
                return resultList;
            }
            // 获取总条数
            long total = totalFunc.apply(response);
            if (total == 0) {
                return resultList;
            }
            checkTotal(totalMax, msg, total);
            // 获取初始结果集
            Collection<R> list = resultFunc.apply(response);
            if (CollectionUtils.isEmpty(list)) {
                return resultList;
            }
            resultList.addAll(list);

            // 多线程查询
            if (total > pageSize) {
                int totalPages = (int) Math.ceil((double) total / pageSize);
//                List<CompletableFuture<List<R>>> futures = IntStream.range(1, totalPages)
//                        .mapToObj(page -> CompletableFuture.supplyAsync(() -> {
//                            int currentOffset = page * pageSize;
//                            T response2 = responseFunc.apply(currentOffset, pageSize);
//                            if (response2 == null) {
//                                return Collections.emptyList();
//                            } else {
//                                Collection<R> list2 = resultFunc.apply(response2);
//                                return list2 != null ? new ArrayList<>(list2) : Collections.emptyList();
//                            }
//                        }, executorService))
//                        .collect(Collectors.toList());

                List<CompletableFuture<List<R>>> futures = new ArrayList<>();
                for (int page = 1; page < totalPages; page++) {
                    int currentPage = page;
                    CompletableFuture<List<R>> future = CompletableFuture.supplyAsync(() -> {
                        int currentOffset = currentPage * pageSize;
                        T response2 = responseFunc.apply(currentOffset, pageSize);
                        if (response2 == null) {
                            return Collections.emptyList();
                        } else {
                            Collection<R> list2 = resultFunc.apply(response2);
                            return list2 != null ? new ArrayList<>(list2) : Collections.emptyList();
                        }
                    }, executorService);
                    futures.add(future);
                }

                CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
                allOf.get(second, TimeUnit.SECONDS);

                for (CompletableFuture<List<R>> future : futures) {
                    resultList.addAll(future.join());
                }
            }
            return resultList;
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }


    /**
     * 根据总页数 循环获取全部数据
     *
     * @param responseFunc   获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalPagesFunc 获取 总页数
     * @param resultFunc     获取list集合
     * @param pageSize       pageSize
     */
    public static <T, R> List<R> getFullDataByTotalPages(BiFunction<Integer, Integer, T> responseFunc,
                                                         Function<T, Long> totalPagesFunc,
                                                         Function<T, Collection<R>> resultFunc,
                                                         int pageSize) {
        return getFullData(responseFunc, totalPagesFunc, resultFunc, pageSize, false, 0, "");
    }


    /**
     * 循环获取全部数据
     *
     * @param responseFunc              获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc                 获取 total 总条数 或者 获取循环的总次数
     * @param resultFunc                获取list集合
     * @param pageSize                  pageSize
     * @param calculateTotalPageNumbers 是否需要计算出总的循环次数， true：表示需要计算，通过total总条数和pageSize计算出总的循环次数
     *                                  false：表示不用计算，通过totalFunc直接获取总的循环次数
     */
    private static <T, R> List<R> getFullData(BiFunction<Integer, Integer, T> responseFunc,
                                              Function<T, Long> totalFunc,
                                              Function<T, Collection<R>> resultFunc,
                                              int pageSize,
                                              boolean calculateTotalPageNumbers,
                                              long totalMax,
                                              String msg) {
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        //需要循环的总次数
        long totalPageNumbers = 0;
        try {
            do {
                //获取response
                T response = responseFunc.apply(offset, pageSize);
                if (response == null) {
                    break;
                }
                if (pageIndex == 0) {
                    //在第一次请求时，获取需要循环的总次数
                    totalPageNumbers = getTotalPageNumbers(response, totalFunc, pageSize, calculateTotalPageNumbers, totalMax, msg);
                    if (totalPageNumbers == 0) {
                        break;
                    }
                }
                //获取结果集
                Collection<R> list = resultFunc.apply(response);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                resultList.addAll(list);
                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;
            } while (pageIndex < totalPageNumbers);

            return resultList;
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    private static <T> long getTotalPageNumbers(T response, Function<T, Long> totalFunc, int pageSize,
                                                boolean calculateTotalPageNumbers, long totalMax, String msg) {
        //在第一次请求时，获取需要循环的总次数
        long totalPageNumbers = 0;
        //获取总条数
        long total = totalFunc.apply(response);
        if (total == 0) {
            return totalPageNumbers;
        }
        checkTotal(totalMax, msg, total);
        //true：表示需要计算总的循环次数  通过总的条数 和 pageSize 计算出
        if (calculateTotalPageNumbers) {
            //得到余数
            long mod = total % pageSize;
            //需要循环多少次
            totalPageNumbers = total / pageSize;
            if (mod > 0) {
                totalPageNumbers = totalPageNumbers + 1;
            }
        } else {
            //否则 直接获取 需要循环的总次数
            totalPageNumbers = total;
        }
        return totalPageNumbers;
    }

    /**
     * 验证参数是否有效
     *
     * @param totalMax
     * @param msg
     */
    private static void checkTotalParam(long totalMax, String msg) {
        if (totalMax <= 0) {
            throw new IllegalArgumentException("totalMax必须大于0");
        }
        if (StringUtils.isEmpty(msg)) {
            throw new IllegalArgumentException("msg不能为空");
        }
    }

    /**
     * 验证total是否超过totalMax
     *
     * @param totalMax
     * @param msg
     * @param total
     */
    private static void checkTotal(long totalMax, String msg, long total) {
        if (totalMax > 0 && total > totalMax) {
            if (StringUtils.isEmpty(msg)) {
                throw new IllegalArgumentException("totalMax大于0时，msg不能为空");
            }
            throw new IllegalArgumentException(msg);
        }
    }

    private static <T> long getTotalPageNumbers(T response, Function<T, Long> totalFunc, int pageSize,
                                                boolean calculateTotalPageNumbers, long exportSize) {
        //在第一次请求时，获取需要循环的总次数
        long totalPageNumbers = 0;
        //获取总条数
        long total = totalFunc.apply(response);
        if (total == 0) {
            return totalPageNumbers;
        }
        if (exportSize > 0 && total > exportSize) {
            throw new IllegalArgumentException("导出数据条数超过" + exportSize + "，请重新选择查询条件");
        }
        //true：表示需要计算总的循环次数  通过总的条数 和 pageSize 计算出
        if (calculateTotalPageNumbers) {
            //得到余数
            long mod = total % pageSize;
            //需要循环多少次
            totalPageNumbers = total / pageSize;
            if (mod > 0) {
                totalPageNumbers = totalPageNumbers + 1;
            }
        } else {
            //否则 直接获取 需要循环的总次数
            totalPageNumbers = total;
        }
        return totalPageNumbers;
    }


    /**
     * rpc接口没有返回total字段，循环获取全量数据
     *
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param resultFunc   获取list集合
     * @param pageSize     pageSize
     */
    public static <T, R> List<R> getFullDataWithNoTotal(BiFunction<Integer, Integer, T> responseFunc,
                                                        Function<T, Collection<R>> resultFunc,
                                                        int pageSize) {
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        try {
            do {
                //获取response
                T response = responseFunc.apply(offset, pageSize);
                if (response == null) {
                    break;
                }
                //获取结果集
                Collection<R> list = resultFunc.apply(response);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                resultList.addAll(list);
                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;
            } while (resultList.size() == pageIndex * pageSize);
            return resultList;
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }


    /**
     * 通过最小值 循环获取 所有数据
     *
     * @param resultFunc 获取list集合， 第一个参数是最小值id，第二个参数获取到的list集合
     * @param minIdFunc  获取最小值id， 第一个参数是list集合，第二个参数是获取到最小值
     */
    public static <R> List<R> getFullDataByMinId(Function<Long, List<R>> resultFunc,
                                                 Function<List<R>, Long> minIdFunc) {
        List<R> resultList = new ArrayList<>();
        processByMinId(resultFunc,
                minIdFunc,
                list -> resultList.addAll(list)
        );
        return resultList;
    }

    /**
     * 通过最小值 循环获取数据, 并对每次的结果进行处理
     *
     * @param resultFunc 获取list集合， 第一个参数是最小值id，第二个参数获取到的list集合
     * @param minIdFunc  获取最小值id， 第一个参数是list集合，第二个参数是获取到最小值
     */
    public static <R> void processByMinId(Function<Long, List<R>> resultFunc,
                                          Function<List<R>, Long> minIdFunc,
                                          Consumer<List<R>> consumer) {
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        long minId = 0;
        List<R> list = null;
        try {
            do {
                //获取结果集
                list = resultFunc.apply(minId);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                //执行方法
                consumer.accept(list);
                //重新获取最小值
                minId = minIdFunc.apply(list);
                pageIndex = pageIndex + 1;

            } while (CollectionUtils.isNotEmpty(list));
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 通过offset、limit 循环获取数据, 并对每次的结果进行处理
     *
     * @param resultFunc 获取list集合， 第一个入参是offset，第二个是 limit，第三个是获取到的list集合
     * @param pageSize   pageSize
     * @param consumer   对结果集进行处理的函数
     */
    public static <R> void processByOffsetLimit(BiFunction<Integer, Integer, List<R>> resultFunc,
                                                int pageSize,
                                                Consumer<List<R>> consumer) {
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        int offset = 0;
        List<R> list = null;
        try {
            do {
                //获取结果集
                list = resultFunc.apply(offset, pageSize);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                //执行方法
                consumer.accept(list);

                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;

            } while (CollectionUtils.isNotEmpty(list) && list.size() == pageSize);
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 多线程获取全部数据
     *
     * @param resultFunc      获取结果集  第一个入参是offset，第二个是 limit，第三个是返回的结果集
     * @param total           总的条数
     * @param pageSize        pageSize
     * @param executorService 线程池的名称
     * @param second          线程等待的时间  秒为单位
     */
    public static <R> List<R> getFullDataByMutiThread(BiFunction<Integer, Integer, List<R>> resultFunc,
                                                      long total,
                                                      int pageSize,
                                                      ExecutorService executorService,
                                                      int second) {
        return getFullDataByMutiThread(resultFunc, total, pageSize, executorService, second, 0);
    }

    /**
     * 多线程获取全部数据
     *
     * @param resultFunc      获取结果集  第一个入参是offset，第二个是 limit，第三个是返回的结果集
     * @param total           总的条数
     * @param pageSize        pageSize
     * @param executorService 线程池
     * @param second          线程等待的时间  秒为单位
     * @param offset          从指定的偏移量开始获取数据
     */
//    private static <R> List<R> getFullDataByMutiThread(BiFunction<Integer, Integer, List<R>> resultFunc,
//                                                       long total,
//                                                       int pageSize,
//                                                       ExecutorService executorService,
//                                                       int second,
//                                                       int offset) {
//        //页数，从0开始（0表示的是第一页）
//        int pageIndex = 0;
//        List<FutureTask<List<R>>> futureTasks = new ArrayList<>();
//        do {
//            int finalOffset = offset;
//            FutureTask<List<R>> task = new FutureTask<>(() -> {
//                List<R> list = resultFunc.apply(finalOffset, pageSize);
//                return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
//            });
//            futureTasks.add(task);
//            pageIndex = pageIndex + 1;
//            offset = offset + pageSize;
//        } while (offset < total);
//        List<List<R>> list2list = ThreadPoolUtils.submitAndGet(executorService, futureTasks, second, TimeUnit.SECONDS);
//        return ThreadPoolUtils.listArrary2List(list2list);
//    }
    private static <R> List<R> getFullDataByMutiThread(BiFunction<Integer, Integer, List<R>> resultFunc,
                                                       long total,
                                                       int pageSize,
                                                       ExecutorService executorService,
                                                       int second,
                                                       int offset) {
        List<CompletableFuture<List<R>>> futures = new ArrayList<>();
        while (offset < total) {
            int finalOffset = offset;
            CompletableFuture<List<R>> future = CompletableFuture.supplyAsync(() -> {
                List<R> list = resultFunc.apply(finalOffset, pageSize);
                return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
            }, executorService);
            futures.add(future);
            offset += pageSize;
        }

        List<R> resultList = futures.stream()
                .map(future -> {
                    try {
                        return future.get(second, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return resultList;
    }


    /**
     * 导出数据
     *
     * @param responseFunc
     * @param totalFunc
     * @param resultFunc
     * @param pageSize
     * @param exportSize
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> exportFullData(BiFunction<Integer, Integer, T> responseFunc,
                                                Function<T, Long> totalFunc,
                                                Function<T, Collection<R>> resultFunc,
                                                int pageSize, long exportSize) {
        return getFullData(responseFunc, totalFunc, resultFunc, pageSize, true, exportSize);
    }

    /**
     * 循环获取全部数据
     *
     * @param responseFunc              获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc                 获取 total 总条数 或者 获取循环的总次数
     * @param resultFunc                获取list集合
     * @param pageSize                  pageSize
     * @param calculateTotalPageNumbers 是否需要计算出总的循环次数， true：表示需要计算，通过total总条数和pageSize计算出总的循环次数
     *                                  false：表示不用计算，通过totalFunc直接获取总的循环次数
     */
    private static <T, R> List<R> getFullData(BiFunction<Integer, Integer, T> responseFunc,
                                              Function<T, Long> totalFunc,
                                              Function<T, Collection<R>> resultFunc,
                                              int pageSize,
                                              boolean calculateTotalPageNumbers, long exportSize) {
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        //需要循环的总次数
        long totalPageNumbers = 0;
        try {
            do {
                //获取response
                T response = responseFunc.apply(offset, pageSize);
                if (response == null) {
                    break;
                }
                if (pageIndex == 0) {
                    //在第一次请求时，获取需要循环的总次数
                    totalPageNumbers = getTotalPageNumbers(response, totalFunc, pageSize, calculateTotalPageNumbers, exportSize);
                    if (totalPageNumbers == 0) {
                        break;
                    }
                }
                //获取结果集
                Collection<R> list = resultFunc.apply(response);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                resultList.addAll(list);
                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;
            } while (pageIndex < totalPageNumbers);

            return resultList;
        } catch (IllegalArgumentException pe) {
            throw pe;
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 按照list分组后多线程获取全部数据
     *
     * @param groupByList     分组的list
     * @param groupNum        分组条数
     * @param resultFunc      获取每组后的list集合
     * @param executorService 线程池
     * @param second          超时时间
     */
//    public static <T, R> List<R> getFullDataByGroupByWithThreadPool(List<T> groupByList, int groupNum,
//                                                                    Function<List<T>, List<R>> resultFunc, ExecutorService executorService, int second) {
//
//        List<FutureTask<List<R>>> futureTasks = Lists.newArrayList();
//        List<List<T>> groupList = ThreadPoolUtils.groupByNum(groupByList, groupNum);
//        //如果只有一个分组，则用主线程去查询
//        if (groupList.size() == 1) {
//            List<R> list = resultFunc.apply(groupList.get(0));
//            return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
//        }
//        for (List<T> group : groupList) {
//            if (group.isEmpty()) {
//                continue;
//            }
//            FutureTask<List<R>> task = new FutureTask<>(() -> {
//                List<R> list = resultFunc.apply(group);
//                return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
//            });
//
//            futureTasks.add(task);
//        }
//        List<List<R>> list = ThreadPoolUtils.submitAndGet(executorService, futureTasks, second,
//                TimeUnit.SECONDS);
//        return ThreadPoolUtils.listArrary2List(list);
//    }
    public static <T, R> List<R> getFullDataByGroupByWithThreadPool(List<T> groupByList, int groupNum,
                                                                    Function<List<T>, List<R>> resultFunc, ExecutorService executorService, int second) {

        List<List<T>> groupList = ThreadPoolUtils.groupByNum(groupByList, groupNum);
        //如果只有一个分组，则用主线程去查询
        if (groupList.size() == 1) {
            List<R> list = resultFunc.apply(groupList.get(0));
            return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
        }

//        List<CompletableFuture<List<R>>> futures = groupList.stream()
//                .filter(group -> !group.isEmpty())
//                .map(group -> CompletableFuture.supplyAsync(() -> {
//                    List<R> list = resultFunc.apply(group);
//                    return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
//                }, executorService))
//                .collect(Collectors.toList());

        List<CompletableFuture<List<R>>> futures = new ArrayList<>();
        for (List<T> group : groupList) {
            if (!group.isEmpty()) {
                CompletableFuture<List<R>> future = CompletableFuture.supplyAsync(() -> {
                    List<R> list = resultFunc.apply(group);
                    return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
                }, executorService);
                futures.add(future);
            }
        }

        List<R> resultList = futures.stream()
                .map(future -> {
                    try {
                        return future.get(second, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return resultList;
    }


    /**
     * 按照list分组后循环获取全部数据
     *
     * @param groupByList 分组的list
     * @param groupNum    分组条数
     * @param resultFunc  获取每组后的list集合
     */
    public static <T, R> List<R> getFullDataByGroupBy(List<T> groupByList, int groupNum,
                                                      Function<List<T>, List<R>> resultFunc) {
        List<R> resultList = Lists.newArrayList();
        List<List<T>> groupList = ThreadPoolUtils.groupByNum(groupByList, groupNum);

        for (List<T> group : groupList) {
            if (group.isEmpty()) {
                continue;
            }
            List<R> list = resultFunc.apply(group);
            if (CollectionUtils.isNotEmpty(list)) {
                resultList.addAll(list);
            }
        }
        return resultList;
    }
}
