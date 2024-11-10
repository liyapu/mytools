package com.lyp.learn.others;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author liyapu
 * @date 2024-11-08 17:17
 * @description
 */
public class MapComputeDemo {

    //----------------------compute----------------------

    /**
     * compute是Map接口的一个方法，它允许你对映射中的某个键的值进行计算。
     * 不同于computeIfPresent，compute无论键是否存在，都会执行计算。
     * 如果给定的键不存在，它会尝试添加一个新的键值对（如果计算结果不为null）。
     * 如果计算结果为null，且键存在，那么该键会被从映射中移除。
     *
     * 方法签名
     * compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
     * key：要计算的键。
     * remappingFunction：应用于当前键和值的函数。该函数的返回值将更新原来键的值。
     * 如果返回null，且键存在，键会被移除。
     */
    @Test
    public void testCompute01() {
        Map<Integer, Integer> productStock = new HashMap<>();
        productStock.put(1, 50);
        productStock.put(2, 75);
        productStock.put(6, null);

        // 更新商品ID为1的库存，增加20
        productStock.compute(1, (id, stock) -> (stock == null ? 0 : stock) + 20);

        // 添加一个新的商品ID为3，并设置初始库存为10
        productStock.compute(3, (id, stock) -> (stock == null ? 0 : stock) + 10);

        // 尝试更新商品ID为2的库存，但计算结果为null，所以该键被移除
        productStock.compute(2, (id, stock) -> null);


        productStock.compute(6, (id, stock) -> null);

        //{1=70, 3=10}
        System.out.println(productStock);
    }

    /**
     * 以测试用例方式来展示Map的compute()、computeIfPresent()、computeIfAbsent()这三个方法的区别
     * 原文链接：https://blog.csdn.net/zhaojiyuan1024/article/details/124781002
     * 结论
     * compute方法=computeIfPresent方法 + computeIfAbsent方法
     *
     * 一、compute(参数一、参数二)方法：
     * 参数一：指定的key
     * 参数二：接口函数（k,v）
     * 结论：
     * (修改数据) 原始Map指定的key值存在，函数接口返回不会空，则用接口函数返回的结果替换key的value
     * (删除数据) 原始Map指定的key值存在，函数接口返回为空，删除key对应的数据
     * (添加数据) 原始Map指定的key值不存在，函数接口返回为不空，则添加一条数据到Map，key：指定的key value：函数返回的结果
     */

    /**
     * 打印前两个参数，返回第三参数
     *
     * @param k
     * @param v
     * @param result
     * @return
     */
    public static String printLogAndReturnParam(String k, String v, String result) {
//        System.out.print("k-" + k + " ");
//        System.out.println("v-" + v);
        System.out.println("param(" + k + "," + v + ") ---->" + result);
        return result;
    }

    @Test
    public void testMapCompute02() {
        Map<String, String> map = new LinkedHashMap<>();
        System.out.println("测试场景：compute");
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.print("测试指定key存在，返回值一致:");
        map.compute("k1", (k, v) -> printLogAndReturnParam(k, v, "v1"));
        System.out.print("测试指定key存在，返回值不一致:");
        map.compute("k2", (k, v) -> printLogAndReturnParam(k, v, "VV2"));
        System.out.print("测试指定key存在，返回值为NULL:");
        map.compute("k3", (k, v) -> printLogAndReturnParam(k, v, null));
        System.out.print("测试指定key不存在，返回值NULL:");
        map.compute("k4", (k, v) -> printLogAndReturnParam(k, v, null));
        System.out.print("测试指定key不存在，返回值不为NULL:");
        map.compute("k5", (k, v) -> printLogAndReturnParam(k, v, "VV5"));
        System.out.print("输出结果：" + map);
        System.out.println("  结论如下：");
        System.out.println("测试指定key存在，返回值一致:value保持不变");
        System.out.println("测试指定key存在，返回值不一致:value使用返回值");
        System.out.println("测试指定key存在，返回值为NULL:删除该条数据");
        System.out.println("测试指定key不存在，返回值NULL:不添加不删除");
        System.out.println("测试指定key不存在，返回值不为NULL:添加数据，key：指定的key，value：返回值");
    }


    //----------------------computeIfPresent----------------------

    /**
     * computeIfPresent是Map接口的一个方法，它允许你在键存在于映射中时，对该键的值进行重新计算。
     * 如果给定的键找到了对应的值（即值不为null），则会应用给定的重新计算函数，并更新映射。
     * 如果重新计算函数的结果为null，则会删除该键。
     * 如果键不存在或原始值为null，则不做任何操作。
     * 方法签名
     * computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
     * key：要检查其存在性的键。
     * remappingFunction：如果键存在，应用于当前键和值的函数。
     */
    @Test
    public void testComputeIfPresent01() {
        //假设我们有一个映射，键是学生的ID，值是他们的分数。
        //我们想要为特定学生的分数加10分，但只有当该学生已经在映射中时。
        Map<Integer, Integer> studentScores = new HashMap<>();
        studentScores.put(1, 80);
        studentScores.put(2, 90);
        studentScores.put(3, 85);
        studentScores.put(6, 60);
        studentScores.put(7, null);

        // 增加学生ID为1的分数10分
        studentScores.computeIfPresent(1, (id, score) -> score + 10);

        // 尝试为不存在的学生ID增加分数（不会有任何操作）
        studentScores.computeIfPresent(4, (id, score) -> score + 10);

        // 移除学生ID为6的 整条记录
        studentScores.computeIfPresent(6, (id, score) -> null);

        //键存在，但是之前建对应的value为null, 什么也不做
        studentScores.computeIfPresent(7, (id, score) -> null);

        System.out.println(studentScores);
    }

    /**
     * 二、computeIfPresent（参数一、参数二）
     * 参数一：指定的key
     * 参数二：接口函数（k,v）
     * 结论：
     * (修改数据) 原始Map指定的key值存在，函数接口返回不会空，则用接口函数返回的结果替换key的value
     * (删除数据) 原始Map指定的key值存在，函数接口返回为空，删除key对应的数据
     */
    @Test
    public void testMapComputeIfPresent02() {
        Map<String, String> map = new LinkedHashMap<>();
        System.out.println("测试场景：computeIfPresent");
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.print("测试指定key存在，返回值一致:");
        map.computeIfPresent("k1", (k, v) -> printLogAndReturnParam(k, v, "v1"));
        System.out.print("测试指定key存在，返回值不一致:");
        map.computeIfPresent("k2", (k, v) -> printLogAndReturnParam(k, v, "VV2"));
        System.out.print("测试指定key存在，返回值为NULL:");
        map.computeIfPresent("k3", (k, v) -> printLogAndReturnParam(k, v, null));
        System.out.println("测试指定key不存在，返回值NULL:");
        map.computeIfPresent("k4", (k, v) -> printLogAndReturnParam(k, v, null));
        System.out.println("测试指定key不存在，返回值不为NULL:");
        map.computeIfPresent("k5", (k, v) -> printLogAndReturnParam(k, v, "VV5"));
        System.out.print("输出结果：" + map);
        System.out.println(" 结论如下：");
        System.out.println("测试指定key存在，返回值一致:value保持不变");
        System.out.println("测试指定key存在，返回值不一致:value使用返回值");
        System.out.println("测试指定key存在，返回值为NULL:删除该条数据");
        System.out.println("测试指定key不存在，返回值NULL:不添加不删除");
        System.out.println("测试指定key不存在，返回值不为NULL:不添加不删除");
    }

    @Test
    public void testComputeIfPresent03() {
        // 默认所有请求中的时间都是-99，也就是不存在，保证请求中的每个SKU都有返回
        Map<Integer, Integer> returnMap = new HashMap<>();
        returnMap.put(1, -99);
        returnMap.put(2, -99);
        returnMap.put(3, -99);
        returnMap.put(4, -99);
        returnMap.put(5, -99);
        returnMap.put(6, -99);
        returnMap.put(7, -99);
        returnMap.put(8, -99);
        returnMap.put(9, -99);

        List<Integer> ints = Lists.newArrayList(1, 3, 5, 7);

        ints.forEach(item -> {
            // returnMap中包含的SKU，才会做put操作，有batchHour放batchHour，没有就默认22，代表默认逻辑都是22点
            returnMap.computeIfPresent(item, (k, v) -> Optional.ofNullable(item * item).orElse(22));
        });
        System.out.println(returnMap);
    }

    /**
     * java.lang.NullPointerException
     * pairList.stream().collect(Collectors.toMap( 再转map时,value为null, 抛NPE空指针异常
     */
    @Test
    public void testComputeIfPresent04() {
        // 默认所有请求中的时间都是-99，也就是不存在，保证请求中的每个SKU都有返回
        Map<Integer, Integer> returnMap = new HashMap<>();
        returnMap.put(1, -99);
        returnMap.put(2, -99);
        returnMap.put(3, -99);
        returnMap.put(4, -99);
        returnMap.put(5, -99);
        returnMap.put(6, -99);
        returnMap.put(7, -99);
        returnMap.put(8, -99);
        returnMap.put(9, -99);

        List<SkuInfo> pairList = new ArrayList<>();
        pairList.add(new SkuInfo(1, 11));
        pairList.add(new SkuInfo(3, 13));
        pairList.add(new SkuInfo(5, 55));
        pairList.add(new SkuInfo(3, null));
        pairList.add(new SkuInfo(7, null));
        pairList.add(new SkuInfo(7, 77));

        Map<Integer, Integer> skuId2HourMap = pairList.stream()
                .collect(Collectors.toMap(SkuInfo::getSkuId, SkuInfo::getHour, (v1, v2) -> {
                    if (Objects.isNull(v1)) {
                        return v2;
                    }
                    if (Objects.isNull(v2)) {
                        return v1;
                    }
                    return Math.min(v1, v2);
                }));

        skuId2HourMap.entrySet().forEach(item -> {
            // returnMap中包含的SKU，才会做put操作，有batchHour放batchHour，没有就默认22，代表默认逻辑都是22点
            returnMap.computeIfPresent(item.getKey(), (k, v) -> Optional.ofNullable(item.getValue()).orElse(22));
        });
        System.out.println(returnMap);
    }

    @Test
    public void testComputeIfPresent044() {
        // 默认所有请求中的时间都是-99，也就是不存在，保证请求中的每个SKU都有返回
        Map<Integer, Integer> returnMap = new HashMap<>();
        returnMap.put(1, -99);
        returnMap.put(2, -99);
        returnMap.put(3, -99);
        returnMap.put(4, -99);
        returnMap.put(5, -99);
        returnMap.put(6, -99);
        returnMap.put(7, -99);
        returnMap.put(8, -99);
        returnMap.put(9, -99);

        List<SkuInfo> pairList = new ArrayList<>();
        pairList.add(new SkuInfo(1, 11));
        pairList.add(new SkuInfo(3, 13));
        pairList.add(new SkuInfo(5, 55));
        pairList.add(new SkuInfo(3, null));
        pairList.add(new SkuInfo(7, null));
        pairList.add(new SkuInfo(7, 77));

        Map<Integer, Integer> skuId2HourMap = pairList.stream()
                .map(item -> {
                    if (Objects.isNull(item.getHour())) {
                        //这里提前给null赋默认值,解决下面转toMap报空指针异常的问题
                        item.setHour(22);
                    }
                    return item;
                }).collect(Collectors.toMap(SkuInfo::getSkuId, SkuInfo::getHour, Math::min));

        //Stream.peek方法确实主要用于调试目的，它允许你在不打断流操作链的情况下，查看流中的元素。
        //然而，正如你提到的，peek方法应该谨慎使用，因为流的实现有可能出于优化目的跳过peek调用，
        //这可能导致peek操作对一些或全部元素不被调用，从而引入难以发现的错误。
        //不合规的代码示例
        //在下面的示例中，peek被用于打印过滤后的值，但这是不合规的使用，因为它依赖于peek对每个元素的调用，这在生产环境中可能不是一个稳定的假设。
//        Map<Integer, Integer> skuId2HourMap = pairList.stream()
//                .peek(item -> {
//                    if (Objects.isNull(item.getHour())) {
//                        //这里提前给null赋默认值,解决下面转toMap报空指针异常的问题
//                        item.setHour(22);
//                    }
//                }).collect(Collectors.toMap(SkuInfo::getSkuId, SkuInfo::getHour, Math::min));

//        skuId2HourMap.entrySet().forEach(item -> {
//            // returnMap中包含的SKU，才会做put操作，有batchHour放batchHour，没有就默认22，代表默认逻辑都是22点
//            returnMap.computeIfPresent(item.getKey(), (k, v) -> item.getValue());
//        });

        skuId2HourMap.forEach((key, value) -> {
            // returnMap中包含的SKU，才会做put操作，有batchHour放batchHour，没有就默认22，代表默认逻辑都是22点
            returnMap.computeIfPresent(key, (k, v) -> value);
        });


        //{1=11, 2=-99, 3=13, 4=-99, 5=55, 6=-99, 7=22, 8=-99, 9=-99}
        System.out.println(returnMap);
    }

    @Test
    public void testComputeIfPresent05() {
        Map<Integer, Integer> returnMap = initializeReturnMap();
        List<SkuInfo> pairList = createSkuInfoList();

        Map<Integer, Integer> skuId2HourMap = createSkuId2HourMap(pairList);
        updateReturnMap(returnMap, skuId2HourMap);

        System.out.println(returnMap);
    }

    private Map<Integer, Integer> initializeReturnMap() {
        Map<Integer, Integer> returnMap = new HashMap<>();
        IntStream.rangeClosed(1, 9).forEach(i -> returnMap.put(i, -99));
        return returnMap;
    }

    private List<SkuInfo> createSkuInfoList() {
        return Arrays.asList(
                new SkuInfo(1, 11),
                new SkuInfo(3, 33),
                new SkuInfo(5, 55),
                new SkuInfo(3, null),
                new SkuInfo(7, null),
                new SkuInfo(7, 77)
        );
    }

    private Map<Integer, Integer> createSkuId2HourMap(List<SkuInfo> pairList) {
        return pairList.stream()
                .collect(Collectors.toMap(
                        SkuInfo::getSkuId,
                        SkuInfo::getHour,
                        (v1, v2) -> chooseValue(v1, v2)
                ));
    }

    private Integer chooseValue(Integer v1, Integer v2) {
        if (Objects.isNull(v1)) {
            return v2;
        }
        if (Objects.isNull(v2)) {
            return v1;
        }
        return Math.min(v1, v2);
    }

    private void updateReturnMap(Map<Integer, Integer> returnMap, Map<Integer, Integer> skuId2HourMap) {
        skuId2HourMap.forEach((key, value) ->
                returnMap.computeIfPresent(key, (k, v) -> Optional.ofNullable(value).orElse(22))
        );
    }


    //----------------------computeIfAbsent----------------------

    /**
     * computeIfAbsent是Map接口的一个方法，它用于在指定的键不存在于映射中时，计算其值并将其插入到映射中。
     * 如果映射已经包含了指定的键，则不进行任何操作。
     * 这个方法非常适合用于延迟初始化的场景，即只有在需要时才创建值。
     * 方法签名
     * computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
     * key：要计算其值的键。
     * mappingFunction：如果键不存在，用于生成值的函数。该函数接收键作为输入，并返回新值作为输出。
     */
    @Test
    public void testComputeIfAbsent01() {
        //假设我们有一个映射，键是学生的ID，值是他们的名字。
        //我们想要为一个新学生添加记录，但只有在该学生ID尚未存在于映射中时才进行添加。
        Map<Integer, String> studentNames = new HashMap<>();
        studentNames.put(1, "Alice");
        studentNames.put(2, "Bob");

        // 尝试为ID为3的学生添加名字"Charlie"
        studentNames.computeIfAbsent(3, id -> "Charlie");

        // 尝试为ID为1的学生添加名字"Diana"，但因为ID为1的学生已存在，所以不会进行任何操作
        studentNames.computeIfAbsent(1, id -> "Diana");

        //{1=Alice, 2=Bob, 3=Charlie}
        System.out.println(studentNames);
    }

    /**
     * 三、computeIfAbsent（参数一、参数二）
     * 参数一：指定的key
     * 参数二：接口函数（k）
     * 结论：
     * (添加数据) 原始Map指定的key值不存在，函数接口返回为不空，则添加一条数据到Map，key：指定的key value：函数返回的结果
     */
    @Test
    public void testMapComputeIfAbsent02() {
        Map<String, String> map = new LinkedHashMap<>();
        System.out.println("测试场景：computeIfAbsent");
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println("测试指定key1存在，返回值一致");
        map.computeIfAbsent("k1", k -> "v1");
        System.out.println("测试指定key2存在，返回值不一致");
        map.computeIfAbsent("k2", k -> "VV2");
        System.out.println("测试指定key3存在，返回值为NULL");
        map.computeIfAbsent("k3", k -> null);
        System.out.println("测试指定key4不存在，返回值NULL");
        map.computeIfAbsent("k4", k -> null);
        System.out.println("测试指定key5不存在，返回值不为NULL");
        map.computeIfAbsent("k5", k -> "VV5");
        System.out.print("输出结果：" + map);
        System.out.println(" 结论如下：");
        System.out.println("测试指定key存在，返回值一致:不添加不删除");
        System.out.println("测试指定key存在，返回值不一致:不添加不删除");
        System.out.println("测试指定key存在，返回值为NULL:不添加不删除");
        System.out.println("测试指定key不存在，返回值NULL:不添加不删除");
        System.out.println("测试指定key不存在，返回值不为NULL:添加数据，key：指定的key，value：指定的value");
    }


}
