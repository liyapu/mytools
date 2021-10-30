package com.lyp.learn.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * @author liyapu
 * @date 2021-10-30 15:22
 * @desc
 *   CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行，
 *      例如，定义两个CompletableFuture，
 *              第一个CompletableFuture 根据证券名称查询证券代码，
 *              第二个CompletableFuture 根据证券代码查询证券价格，
 *           这两个CompletableFuture实现串行操作如下：
 */
public class CompletableFuture02 {

    public static void main(String[] args) {
        CompletableFuture02 cf02 = new CompletableFuture02();
        cf02.test01();
    }

    public void test01(){
        //第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() ->{return queryCode("中国石油");});

        //cfQuery 成功后继续执行下一个任务
         CompletableFuture<Integer> cfFetch = cfQuery.thenApplyAsync((code) -> { return fetchPrice(code); });

         //cfFetch 成功后打印结果
        cfFetch.thenAccept((result) ->{
            System.out.println("price = " + result);
        });

        //主线程不要立刻结束，否则 CompletableFuture默认使用的线程池会立刻关闭
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据名称查询code
     * @param name
     * @return
     */
    public String queryCode(String name){
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "601857";
    }

    /**
     * 根据code查询价格
     * @param code
     * @return
     */
    public int fetchPrice(String code){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (int)(Math.random() * 100);

    }
}
