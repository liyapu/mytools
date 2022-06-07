package com.lyp.learn.utils;

import com.lyp.learn.bean.Man;
import com.lyp.learn.bean.ManListResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2021-11-03 22:24
 * @desc
 */
public class ThreadPoolMyUtilsTest extends TestCase {

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
        8, 20, 10, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(5),
        new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void testSubmit() {
        Man man = new Man();

        List<Integer> ids = Arrays.asList(1, 5, 8);
        FutureTask<ManListResponse> ft = new FutureTask<>(() -> {
            ManListResponse manListResponse = man.queryByIds(ids);
            return manListResponse;
        });
        System.out.println("做的 其它事情 1111111----------");
        ThreadPoolMyUtils.submit(threadPool, ft);
        System.out.println("做的 其它事情 2222222----------");
        ManListResponse manListResponse = ThreadPoolMyUtils.get(ft, 5, TimeUnit.SECONDS);
        System.out.println("做的 其它事情 333333----------");
        System.out.println("manListResponse === " + JsonUtil.writeToString(manListResponse));

        doNotStop();
    }

    /**
     * 不让当前方法结束
     */
    public void doNotStop() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * ids 分成了 5 批次
     * man.queryByIds 让其 睡眠 1s
     * <p>
     * threadPool 核心线程数 配置为 1 , costTimes 为 5s
     * threadPool 核心线程数 配置为 2 , costTimes 为 3s
     * threadPool 核心线程数 配置为 8 , costTimes 为 1s
     */
    public void testSubmitAndGet2() {
        long start = System.currentTimeMillis();

        Man man = new Man();

        List<Integer> ids = Arrays
            .asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 30, 31, 32, 33, 34);

        List<List<Integer>> idGroupList = ThreadPoolMyUtils.groupByNum(ids, 5);
        List<FutureTask<ManListResponse>> ftList = new ArrayList<>();

        for (List<Integer> idGroup : idGroupList) {
            FutureTask<ManListResponse> ftManListResponse = new FutureTask<>(() -> {
                ManListResponse manListResponse = man.queryByIds(idGroup);
                return manListResponse;
            });
            ftList.add(ftManListResponse);
        }

        List<ManListResponse> manListResponses =
            ThreadPoolMyUtils.submitAndGet(threadPool, ftList, 3, TimeUnit.SECONDS);
        System.out.println("manListResponses ==== " + JsonUtil.writeToString(manListResponses));

        long end = System.currentTimeMillis();
        System.out.println("costTimes " + (end - start) / 1000 + " s");
        doNotStop();
    }

    public void testSubmitAndGet3() {
        long start = System.currentTimeMillis();

        Man man = new Man();

        List<Integer> ids = Arrays
            .asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 30, 31, 32, 33, 34);
        List<List<Integer>> idGroupList = ThreadPoolMyUtils.groupByNum(ids, 5);

        List<FutureTask<ManListResponse>> ftList = idGroupList.stream()
            .map(idGroup -> new FutureTask<>(() -> man.queryByIds(idGroup)))
            .collect(Collectors.toList());

        List<ManListResponse> manListResponses =
            ThreadPoolMyUtils.submitAndGet(threadPool, ftList, 3, TimeUnit.SECONDS);
        System.out.println("manListResponses ==== " + JsonUtil.writeToString(manListResponses));

        long end = System.currentTimeMillis();
        System.out.println("costTimes " + (end - start) / 1000 + " s");
        doNotStop();
    }

    public void testSubmitAndGet4() {
        long start = System.currentTimeMillis();
        Man man = new Man();

        List<Integer> ids = Arrays
            .asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 30, 31, 32, 33, 34);

        List<List<Integer>> idGroupList = ThreadPoolMyUtils.groupByNum(ids, 5);

        List<FutureTask<List<Man>>> ftList = new ArrayList<>();

        for (List<Integer> idGroup : idGroupList) {
            FutureTask<List<Man>> ftListMan = new FutureTask<>(() -> {
                ManListResponse manListResponse = man.queryByIds(idGroup);
                ValidateUtil.checkResponse(manListResponse.getCode(), manListResponse.getMsg());
                List<Man> manList = manListResponse.getData();
                return manList;
            });

            ftList.add(ftListMan);
        }

        List<List<Man>> manListList = ThreadPoolMyUtils.submitAndGet(threadPool, ftList, 3, TimeUnit.SECONDS);

        System.out.println("manListList ==== " + JsonUtil.writeToString(manListList));
        List<Man> manListResult = ThreadPoolMyUtils.listArrary2List(manListList);
        System.out.println("manListResult ==== " + JsonUtil.writeToString(manListResult));

        long end = System.currentTimeMillis();
        System.out.println("costTimes " + (end - start) / 1000 + " s");
        doNotStop();
    }

    public void testSubmitAndGet5() {
        long start = System.currentTimeMillis();
        Man man = new Man();

        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);

        List<FutureTask<Man>> futureTasks = new ArrayList<>();
        for (Integer id : ids) {
            FutureTask<Man> futureTask = new FutureTask<>(() -> man.queryById(id));
            futureTasks.add(futureTask);
        }

        List<Man> manList = ThreadPoolMyUtils.submitAndGet(threadPool, futureTasks, 3, TimeUnit.SECONDS);
        System.out.println("manList ==== " + JsonUtil.writeToString(manList));

        long end = System.currentTimeMillis();
        System.out.println("costTimes " + (end - start) / 1000 + " s");
        doNotStop();
    }
}