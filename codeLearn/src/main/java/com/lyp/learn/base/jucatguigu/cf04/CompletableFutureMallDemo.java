package com.lyp.learn.base.jucatguigu.cf04;


import com.google.common.base.Stopwatch;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 案例说明：电商比价需求，模拟如下情况：
 * 1 需求说明
 * 1.1同一款产品，同时搜索出同款产品在各大电商平台的售价；
 * 1.2同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * <p>
 * 2 输出返回：
 * 出来结果希望是同款产品的在不同地方的价格清单列表， 返回一个List<String>
 * 《mysql》in jd price is 88.05
 * 《mysql》in dang dang price is 86.11
 * 《mysql》in tao bao price is 90.43
 * <p>
 * 3 解决方案，比对同一个商品在各个平台上的价格，要求获得一个清单列表
 * 3.1 step by step， 按部就班， 查完京东查淘宝， 查完淘宝查天猫......
 * 3.2 all in，万箭齐发，一口气多线程异步任务同时查询。。。
 */
public class CompletableFutureMallDemo {

    static List<NetMall> netMallList = Arrays.asList(
        new NetMall("jd"),
        new NetMall("dangdang"),
        new NetMall("taobao")
    );


    public static void main(String[] args) {
        testGetPrice1();
        testGetPrice2();
        testGetPrice3ByCompletableFuture();
    }

    private static void testGetPrice1() {
        final Stopwatch started = Stopwatch.createStarted();
        final List<String> mySqlPriceList = getPrice1(netMallList, "MySql");
        for (String s : mySqlPriceList) {
            System.out.println(s);
        }
        started.stop();
        System.out.println("testGetPrice1 花费时间 " + started);
        System.out.println();
    }

    /**
     * step by step 一家一家的搜索
     */
    public static List<String> getPrice1(List<NetMall> nmList, String productName) {
        //《mysql》in tao bao price is 90.43
        return nmList.stream()
            .map(netMall -> String.format("《%s》in %s price is %.2f", productName, netMall.getNetMallName(),
                netMall.calcPrice(productName)))
            .collect(Collectors.toList());
    }


    private static void testGetPrice2() {
        final Stopwatch started = Stopwatch.createStarted();
        final List<String> mySqlPriceList = getPrice2(netMallList, "MySql");
        for (String s : mySqlPriceList) {
            System.out.println(s);
        }
        started.stop();
        System.out.println("testGetPrice2 花费时间 " + started);
        System.out.println();
    }

    /**
     * parallelStream
     * ParallelStreams 默认使用 ForkJoinPool.commonPool()线程池。
     */
    public static List<String> getPrice2(List<NetMall> nmList, String productName) {
        return nmList.parallelStream()
            .map(netMall -> String.format("《%s》in %s price is %.2f", productName, netMall.getNetMallName(),
                netMall.calcPrice(productName)))
            .collect(Collectors.toList());
    }

    private static void testGetPrice3ByCompletableFuture() {
        final Stopwatch started = Stopwatch.createStarted();
        final List<String> mySqlPriceList = getPrice3ByCompletableFuture(netMallList, "MySql");
        for (String s : mySqlPriceList) {
            System.out.println(s);
        }
        started.stop();
        System.out.println("testGetPrice3ByCompletableFuture 花费时间 " + started);
        System.out.println();
    }

    /**
     * CompletableFuture
     * all in，万箭齐发
     */
    public static List<String> getPrice3ByCompletableFuture(List<NetMall> nmList, String productName) {
        return nmList.stream()
            .map(netMall -> CompletableFuture.supplyAsync(
                () -> String.format("《%s》in %s price is %.2f", productName, netMall.getNetMallName(),
                    netMall.calcPrice(productName))))
            .collect(Collectors.toList())
            .stream()
            //.map(cf -> cf.join())
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

}
