package com.lyp.learn.tools;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class QueryUtil {


    /**
     * 根据minId 循环查询DB
     *
     * @param minId          起始id 需要小于数据最小id
     * @param limit          单次查询size
     * @param onceQueryFunc  单次数据获取方法
     * @param findNextIdFunc 获取id 方法
     * @param <T>            泛型类
     * @return 结果集
     */
    public static <T> List<T> queryByMinIdAsc(Long minId,
                                              Integer limit,
                                              BiFunction<Long, Integer, List<T>> onceQueryFunc,
                                              Function<T, Long> findNextIdFunc) {
        //缓存上一次查询的id 防止由于数据查询函数的问题导致id回流造成死循环问题
        Long lastQueryMinId = minId;
        List<T> result = Lists.newArrayList();

        while (true) {
            //获取单次查询的结果集
            List<T> partResult = onceQueryFunc.apply(minId, limit);
            //为空说明查询完成
            if (CollectionUtils.isEmpty(partResult)) {
                break;
            }
            //放置结果集 并计算下一次查询的minId
            result.addAll(partResult);
            T lastItem = partResult.get(partResult.size() - 1);
            minId = findNextIdFunc.apply(lastItem);

            //如果minId小于等于上一次查询的minId 说明数据获取有问题 抛出异常
            if (minId == null || minId <= lastQueryMinId) {
                throw new RuntimeException(String.format("滚动查询中 minId出现异常情况 nowMinId:%s lasMinId:%s", minId, lastQueryMinId));
            }
            lastQueryMinId = minId;
        }
        return result;
    }
}
