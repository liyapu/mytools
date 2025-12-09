package com.lyp.learn.utils.db01;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 持久化工具
 **/
public class PersistentTool {


    /**
     * 获取需要删除的列表
     *
     * @param dbList
     * @param memoryList
     * @return
     */
    private static <T, DK> List<T> getDeleteList(List<T> dbList, List<T> memoryList,
                                                Function<T, DK> distinctKeyFunction) {
        Set<DK> memoryKeySet =
                ListUtils.emptyIfNull(memoryList).stream().map(distinctKeyFunction).collect(Collectors.toSet());
        return ListUtils.emptyIfNull(dbList).stream().filter(t -> {
            DK dk = distinctKeyFunction.apply(t);
            return !memoryKeySet.contains(dk);
        }).collect(Collectors.toList());
    }


    /**
     * 获取需要插入的列表
     *
     * @param dbList
     * @param memoryList
     * @return
     */
    private static <T, DK> List<T> getInsertList(List<T> dbList, List<T> memoryList,
                                                Function<T, DK> distinctKeyFunction) {
        Set<DK> dbKeySet =
                ListUtils.emptyIfNull(dbList).stream().map(distinctKeyFunction).collect(Collectors.toSet());
        return ListUtils.emptyIfNull(memoryList).stream().filter(t -> {
            DK dk = distinctKeyFunction.apply(t);
            return !dbKeySet.contains(dk);
        }).collect(Collectors.toList());
    }


    /**
     * 获取需要更新的列表
     *
     * @param dbList
     * @param memoryList
     * @return
     */
    private static <T, DK, PK> List<T> getUpdateList(List<T> dbList, List<T> memoryList,
                                                     Function<T, DK> distinctKeyFunction,
                                                     Function<T, PK> idGetterFunction,
                                                     BiConsumer<T, PK> idSetterConsumer) {
        Map<DK, PK> dbKeyMap =
                ListUtils.emptyIfNull(dbList).stream().collect(Collectors.toMap(distinctKeyFunction, t -> idGetterFunction.apply(t), (o, o2) -> o2));
        return ListUtils.emptyIfNull(memoryList).stream().filter(t -> {
            DK dk = distinctKeyFunction.apply(t);
            return dbKeyMap.containsKey(dk);
        }).map(t -> {
            DK dk = distinctKeyFunction.apply(t);
            PK pk = dbKeyMap.get(dk);
            idSetterConsumer.accept(t, pk);
            return t;
        }).collect(Collectors.toList());

    }


    /**
     * 计算数据库需要更新的数据
     *
     * @param dbList                   db中的数据
     * @param memoryList               内存中的数据
     * @param distinctKeyFunction      唯一键函数
     * @param groupingKeyFunction      分组键函数
     * @param primaryKeyGetterFunction 主键getter
     * @param primaryKeySetterConsumer 主键setter
     * @param pageSize
     * @param <T>
     * @param <DK>
     * @param <GK>
     * @param <PK>
     * @return
     */
    public static <T, DK, GK, PK> List<CommonPersistenceBO<T>> getCommonPersistenceBOList(List<T> dbList,
                                                                                          List<T> memoryList,
                                                                                          Function<T, DK> distinctKeyFunction,
                                                                                          Function<T, GK> groupingKeyFunction,
                                                                                          Function<T, PK> primaryKeyGetterFunction,
                                                                                          BiConsumer<T, PK> primaryKeySetterConsumer,
                                                                                          int pageSize) {

        List<T> insertList = getInsertList(dbList, memoryList, distinctKeyFunction);
        List<T> deleteList = getDeleteList(dbList, memoryList, distinctKeyFunction);
        List<T> updateList = getUpdateList(dbList, memoryList, distinctKeyFunction, primaryKeyGetterFunction, primaryKeySetterConsumer);

        List<GK> groupKeyList =
                ListUtils.union(ListUtils.union(insertList, deleteList), updateList).stream().map(groupingKeyFunction).distinct().collect(Collectors.toList());

        List<CommonPersistenceBO<T>> result = Lists.newArrayList();

        List<List<GK>> partition = ListUtils.partition(groupKeyList, pageSize);
        for (List<GK> part : partition) {
            Set<GK> partSet = Sets.newHashSet(part);
            List<T> partInsertList =
                    insertList.stream().filter(t -> partSet.contains(groupingKeyFunction.apply(t))).collect(Collectors.toList());
            List<T> partDeleteList =
                    deleteList.stream().filter(t -> partSet.contains(groupingKeyFunction.apply(t))).collect(Collectors.toList());
            List<T> partUpdateList =
                    updateList.stream().filter(t -> partSet.contains(groupingKeyFunction.apply(t))).collect(Collectors.toList());

            CommonPersistenceBO<T> bo = new CommonPersistenceBO<>();

            bo.setInsertList(partInsertList);
            bo.setDeleteList(partDeleteList);
            bo.setUpdateList(partUpdateList);

            result.add(bo);
        }
        return result;
    }

    /**
     * 计算数据库需要更新的数据
     *
     * @param dbList                   db中的数据
     * @param memoryList               内存中的数据
     * @param distinctKeyFunction      唯一键函数
     * @param primaryKeyGetterFunction 主键getter
     * @param primaryKeySetterConsumer 主键setter
     * @param <T>
     * @param <DK>
     * @param <PK>
     * @return
     */
    public static <T, DK, PK> CommonPersistenceBO<T> getCommonPersistenceBO(List<T> dbList,
                                                                            List<T> memoryList,
                                                                            Function<T, DK> distinctKeyFunction,
                                                                            Function<T, PK> primaryKeyGetterFunction,
                                                                            BiConsumer<T, PK> primaryKeySetterConsumer) {

        List<T> insertList = getInsertList(dbList, memoryList, distinctKeyFunction);
        List<T> deleteList = getDeleteList(dbList, memoryList, distinctKeyFunction);
        List<T> updateList = getUpdateList(dbList, memoryList, distinctKeyFunction, primaryKeyGetterFunction,
                primaryKeySetterConsumer);

        CommonPersistenceBO<T> bo = new CommonPersistenceBO<>();

        bo.setInsertList(insertList);
        bo.setDeleteList(deleteList);
        bo.setUpdateList(updateList);

        return bo;
    }

}
