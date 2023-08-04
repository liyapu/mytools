package com.lyp.learn.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description 线程池工具类
 */
public class ThreadPoolMyUtils  {

    /**
     * submit任务
     *
     * @param futureTask 任务
     * @return void
     */
    public static <E> void submit(ExecutorService es, FutureTask<E> futureTask) {
//        checkThreadPoolName(pool);
//        ExecutorService executor = ExecutorServices.forThreadPoolExecutor(pool.getThreadPoolName());
//        executor.submit(futureTask);
        es.submit(futureTask);
    }

    /**
     * submit任务集合
     *
     * @param es        执行任务的线程池
     * @param futureTasks 任务集合
     * @return void
     */
    public static <E> void submit(ExecutorService es, List<FutureTask<E>> futureTasks) {
        for (FutureTask futureTask : futureTasks) {
            submit(es, futureTask);
        }
    }

    /**
     * execute任务
     *
     * @param es     执行任务的线程池
     * @param runnable 任务
     * @return void
     */
    public static <E> void execute(ExecutorService es, Runnable runnable) {
        if (runnable instanceof FutureTask){
            throw new RuntimeException("执行FutureTask请使用submit方法");
        }
//        checkThreadPoolName(pool);
//        ExecutorService executor = ExecutorServices.forThreadPoolExecutor(pool.getThreadPoolName());
//        executor.execute(runnable);
        es.execute(runnable);
    }

    /**
     * execute集合
     *
     * @param es      执行任务的线程池
     * @param runnables 任务集合
     * @return void
     */
    public static <E> void execute(ExecutorService es, List<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            execute(es, runnable);
        }
    }

    /**
     * 获取task执行结果（执行时，主线程会阻塞在这里，如非必要请放在代码结尾调用）
     *
     * @param futureTask 任务
     * @param timeout    超时时间
     * @param unit       时间单位
     * @return {@link E}
     */
    public static <E> E get(FutureTask<E> futureTask, int timeout, TimeUnit unit) {
        try {
            return futureTask.get(timeout, unit);
        } catch (Exception e) {
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 获取task执行结果（执行时，主线程会阻塞在这里，如非必要请放在代码结尾调用）
     * 可以考虑某个任务异常时后续的行为是继续执行还是中断，即某个task异常时continue还是break，现在是break
     *
     * @param futureTasks 任务集合
     * @param timeout     超时时间
     * @param unit        时间单位
     * @return {@link List<E>}
     */
    public static <E> List<E> get(List<FutureTask<E>> futureTasks, int timeout, TimeUnit unit) {
        List result = new ArrayList<>();
        for (FutureTask futureTask : futureTasks) {
            result.add(get(futureTask, timeout, unit));
        }
        return result;
    }


    /**
     * submit任务并获取结果 （执行时，主线程会阻塞在这里，如非必要请分别调用submit和get）
     *
     * @param es        执行任务的线程池
     * @param futureTasks 任务集合
     * @param timeout     超时时间
     * @param unit        时间单位
     * @return {@link List<E>}
     */
    public static <E> List<E> submitAndGet(ExecutorService es, List<FutureTask<E>> futureTasks, int timeout, TimeUnit unit) {
        submit(es, futureTasks);
        return get(futureTasks, timeout, unit);
    }




    /**
     * List<Map> 转map
     *
     * @param resultList list
     * @return {@link Map<K,V>}
     */
    public static <K, V> Map<K, V> listMap2Map(List<Map<K, V>> resultList) {
        return resultList.parallelStream().collect(HashMap::new, HashMap::putAll, HashMap::putAll);
    }

    /**
     * list<List<E>> 转为list<E>
     *
     * @param resultList list
     * @return {@link List<E>}
     */
    public static <E> List<E> listArray2List(List<List<E>> resultList) {
        return resultList.parallelStream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    /**
     * 按照一定数量分组
     *
     * @param listToGroup listToGroup
     * @return 拆分后的List
     */
    public static <V> List<List<V>> groupByNum(List<V> listToGroup, int num) {
        return Lists.partition(listToGroup, num);
    }
}
