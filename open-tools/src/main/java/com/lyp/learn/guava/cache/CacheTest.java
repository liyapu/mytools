package com.lyp.learn.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.lyp.learn.guava.bean.Employee;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * http://www.ibloger.net/article/3345.html
 * Guava 通过接口 LoadingCache 提供了一个非常强大的基于内存的 LoadingCache<K，V>。
 * 在缓存中自动加载值，它提供了许多实用的方法，在有缓存需求时非常有用。
 *
 * 通常来说，Guava Cache适用于：
 *  你愿意消耗一些内存空间来提升速度。
 *  你预料到某些键会被查询一次以上。
 *  缓存中存放的数据总量不会超出内存容量。（Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。）
 *  如果你的场景符合上述的每一条，Guava Cache就适合你。
 *
 * 创建 Cache 范例
 * LoadingCache<String, Object> employeeCache = CacheBuilder.newBuilder()
 *         .maximumSize(100) // 最多可以缓存100条记录
 *         .expireAfterAccess(30, TimeUnit.MINUTES) // 缓存将在访问30分钟后过期
 *         .build(new CacheLoader<String, Object>() {
 *             @Override
 *             public Object load(String key) throws Exception {
 *                 // 数据库加载
 *                 return getFromDatabase(key);
 *             }
 *         });
 * Cache 实例通过 CacheBuilder 生成器模式获取。
 *
 *
 * 加载
 * 在使用缓存前，首先问自己一个问题：有没有合理的默认方法来加载或计算与键关联的值？如果有的话，你应当使用 CacheLoader。
 * 如果没有，或者你想要覆盖默认的加载运算，同时保留 "获取缓存-如果没有-则计算 [get-if-absent-compute]" 的原子语义，你应该在调用 get 时传入一个 Callable 实例。
 * 缓存元素也可以通过 Cache.put 方法直接插入，但自动加载是首选的，因为它可以更容易地推断所有缓存内容的一致性。
 * 任何缓存都应该提供 get-if-absent-compute 这一基础原子语义，具体含义如下：
 * 1、从缓存中取。
 * 2、缓存中存在该数据，直接返回；
 * 3、缓存中不存在该数据，从数据源中取。
 * 4、数据源中存在该数据，放入缓存，并返回；
 * 5、数据源中不存在该数据，返回空。
 *
 *
 */
public class CacheTest {

    @Test
    public void test1() {
        // 根据员工ID为员工创建缓存
        // 移除监听器
        LoadingCache<String, Object> employeeCache = CacheBuilder.newBuilder()
                .maximumSize(100) // 最多可以缓存100条记录
                .expireAfterAccess(30, TimeUnit.MINUTES) // 缓存将在访问30分钟后过期
                .removalListener((RemovalListener<String, Object>) notification -> System.out.println("notification："+notification))
                .build(new CacheLoader<String, Object>() {

                    // 通常重写一个 load 方法即可
                    @Override
                    public Object load(String key) throws Exception {
                        // 数据库加载
                        return getFromDatabase(key);
                    }

                    @Override
                    public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                        return super.reload(key, oldValue);
                    }
                });


        // 1、单个查询
        System.out.println("1、单个查询");
        try {
            // 在第一次调用时，缓存将填充相应的员工记录
            System.out.println("调用 #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

            // 第二次调用时，将从缓存中返回数据
            System.out.println("\n调用 #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 第三次调用时 getUnchecked 不执行检查
        System.out.println("\n调用 #3");
        System.out.println(employeeCache.getUnchecked("100"));
        System.out.println(employeeCache.getUnchecked("103"));
        System.out.println(employeeCache.getUnchecked("110"));

        // 2、批量查询
        try {
            System.out.println("\n2、批量查询");
            ImmutableMap<String, Object> cacheAll = employeeCache.getAll(Lists.newArrayList("100", "103", "110"));
            cacheAll.forEach((k, v) -> System.out.println("K：" + k + "，V：" + v));

            // 3、具有回调的查询
            System.out.println("\n3、具有回调的查询");
            String key = "001";
            System.out.println(employeeCache.get(key, new Callable<Employee>() {
                @Override
                public Employee call() throws Exception {
                    return getFromDatabase(key);
                }
            }));

            // lambda写法
            System.out.println(employeeCache.get(key, (Callable<Employee>) () -> getFromDatabase(key)));

            // 4、显示插入 put
            System.out.println("\n4、显示插入");
            employeeCache.put("001","听风");
            System.out.println(employeeCache.get("001"));

            // 5、更改视图
            System.out.println("\n5、更改视图");
            ConcurrentMap<String, Object> asMap = employeeCache.asMap();
            asMap.put("100","程序喵");
            employeeCache.getAll(Lists.newArrayList("100", "103", "110")).forEach((k, v) -> System.out.println("K：" + k + "，V：" + v));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 6、清除所有缓存
        System.out.println("\n6、清除所有缓存，调用移除监听器");
        employeeCache.invalidateAll();
    }

    private static Employee getFromDatabase(String empId) {
        Map<String, Employee> database = new HashMap();
        database.put("100", new Employee("Mahesh", "Finance", "100"));
        database.put("103", new Employee("Rohan", "IT", "103"));
        database.put("110", new Employee("Sohan", "Admin", "110"));

        database.put("001", new Employee("Tingfeng", "Admin", "001"));
        System.out.println("数据库命中" + empId);
        return database.get(empId);
    }
}
