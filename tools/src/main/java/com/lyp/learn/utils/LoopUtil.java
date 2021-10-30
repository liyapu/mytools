package com.lyp.learn.utils;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.lyp.learn.enums.ThreadPoolNames;
import com.lyp.learn.fun.OneParamFunction;
import com.lyp.learn.fun.SupportExceptionConsumer;
import com.lyp.learn.fun.TwoParamFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @description: 循环获取全量数据
 */
public class LoopUtil {

    /**
     * 循环获取全部数据
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc 获取 total 总条数
     * @param resultFunc 获取list集合
     * @param pageSize pageSize
     */
    public static <T,R> List<R> getFullDataByTotalCount(
            TwoParamFunction<Integer, Integer, T> responseFunc,
                                                        Function<T,Long> totalFunc,
                                                        Function<T, Collection<R>> resultFunc,
                                                        int pageSize){
        return getFullData(responseFunc, totalFunc, resultFunc, pageSize,true);
    }

    /**
     * 多线程 循环获取全部数据
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc 获取 total 总条数
     * @param resultFunc 获取list集合
     * @param pageSize pageSize
     * @param poolName 线程池的名称
     * @param second 线程等待的时间  秒为单位
     */
    public static <T,R> List<R> getFullDataByTotalCountWithMutiThread(TwoParamFunction<Integer, Integer, T> responseFunc,
                                                                      Function<T, Long> totalFunc,
                                                                      Function<T, Collection<R>> resultFunc,
                                                                      int pageSize,
                                                                      ThreadPoolNames poolName,
                                                                      int second){
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        try{
            //获取response
            T response = responseFunc.apply(offset,pageSize);
            if(response == null){
                return resultList;
            }
            //获取总条数
            long total = totalFunc.apply(response);
            if(total == 0){
                return resultList;
            }
            //获取结果集
            Collection<R> list = resultFunc.apply(response);
            if(CollectionUtils.isEmpty(list)){
                return resultList;
            }
            resultList.addAll(list);
            //多线程查询
            if(total > pageSize){
                List<R> totalList = getFullDataByMutiThread((offset2,limit2)->{
                    T response2 = responseFunc.apply(offset2,limit2);
                    if(response2 == null){
                        return new ArrayList<>();
                    }else{
                        Collection<R> list2 = resultFunc.apply(response2);
                        return list2 != null ? Lists.newArrayList(list2) : new ArrayList<>();
                    }
                }, total, pageSize, poolName, second, pageSize);
                resultList.addAll(totalList);
            }
            return resultList;
        }catch (Exception e){
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 根据总页数 循环获取全部数据
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalPagesFunc 获取 总页数
     * @param resultFunc 获取list集合
     * @param pageSize pageSize
     */
    public static <T,R> List<R> getFullDataByTotalPages(TwoParamFunction<Integer, Integer, T> responseFunc,
                                                        Function<T,Long> totalPagesFunc,
                                                        Function<T, Collection<R>> resultFunc,
                                                        int pageSize){
        return getFullData(responseFunc, totalPagesFunc, resultFunc, pageSize,false);
    }


    /**
     * 循环获取全部数据
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param totalFunc 获取 total 总条数 或者 获取循环的总次数
     * @param resultFunc 获取list集合
     * @param pageSize pageSize
     * @param calculateTotalPageNumbers 是否需要计算出总的循环次数， true：表示需要计算，通过total总条数和pageSize计算出总的循环次数
     *                                  false：表示不用计算，通过totalFunc直接获取总的循环次数
     *
     */
    private static <T,R> List<R> getFullData(TwoParamFunction<Integer, Integer, T> responseFunc,
                                            Function<T,Long> totalFunc,
                                            Function<T, Collection<R>> resultFunc,
                                            int pageSize,
                                            boolean calculateTotalPageNumbers){
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        //需要循环的总次数
        long totalPageNumbers = 0;
        try{
            do{
                //获取response
                T response = responseFunc.apply(offset,pageSize);
                if(response == null){
                    break;
                }
                if(pageIndex == 0){
                    //在第一次请求时，获取需要循环的总次数
                    totalPageNumbers = getTotalPageNumbers(response, totalFunc, pageSize, calculateTotalPageNumbers);
                    if(totalPageNumbers == 0){
                        break;
                    }
                }
                //获取结果集
                Collection<R> list = resultFunc.apply(response);
                if(CollectionUtils.isEmpty(list)){
                    break;
                }
                resultList.addAll(list);
                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;
//                if(!MccConfig.whileUtilSwitch){
//                    break;
//                }
            }while (pageIndex < totalPageNumbers);

            return resultList;
        }catch (Exception e){
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    private static <T> long getTotalPageNumbers(T response, Function<T,Long> totalFunc, int pageSize, boolean calculateTotalPageNumbers){
        //在第一次请求时，获取需要循环的总次数
        long totalPageNumbers = 0;
        //获取总条数
        long total = totalFunc.apply(response);
        if(total == 0){
            return totalPageNumbers;
        }
        //true：表示需要计算总的循环次数  通过总的条数 和 pageSize 计算出
        if(calculateTotalPageNumbers) {
            //得到余数
            long mod = total % pageSize;
            //需要循环多少次
            totalPageNumbers = total / pageSize;
            if (mod > 0) {
                totalPageNumbers = totalPageNumbers + 1;
            }
        }else{
            //否则 直接获取 需要循环的总次数
            totalPageNumbers = total;
        }
        return totalPageNumbers;
    }


    /**
     * rpc接口没有返回total字段，循环获取全量数据
     * @param responseFunc 获取response， 第一个入参是offset，第二个是 limit，第三个是response类型
     * @param resultFunc 获取list集合
     * @param pageSize pageSize
     */
    public static <T,R> List<R> getFullDataWithNoTotal(TwoParamFunction<Integer, Integer, T> responseFunc,
                                                       Function<T, Collection<R>> resultFunc,
                                                       int pageSize){
        List<R> resultList = new ArrayList<>();
        int offset = 0;
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        boolean needLogEvent = true;
        try{
            do{
                //获取response
                T response = responseFunc.apply(offset,pageSize);
                if(response == null){
                    break;
                }
                //获取结果集
                Collection<R> list = resultFunc.apply(response);
                if(CollectionUtils.isEmpty(list)){
                    break;
                }
                resultList.addAll(list);
                pageIndex = pageIndex + 1;
                offset = pageIndex * pageSize;
                if(needLogEvent){
                    //满足条件时，上报cat
                    needLogEvent = processLogEvent(pageIndex, needLogEvent, response.getClass().getSimpleName());
                }
            }while (resultList.size() == pageIndex*pageSize);
            return resultList;
        }catch (Exception e){
            throw ConvertUtils.wrapGateWayException(e);
        }
    }


    /**
     * 通过最小值 循环获取 所有数据
     * @param resultFunc 获取list集合， 第一个参数是最小值id，第二个参数获取到的list集合
     * @param minIdFunc 获取最小值id， 第一个参数是list集合，第二个参数是获取到最小值
     *
     */
    public static <R> List<R> getFullDataByMinId(OneParamFunction<Long, List<R>> resultFunc,
                                                 Function<List<R>,Long> minIdFunc){
        List<R> resultList = new ArrayList<>();
        processByMinId(resultFunc,
                       minIdFunc,
                       list -> resultList.addAll(list)
        );
        return resultList;
    }

    /**
     * 通过最小值 循环获取数据, 并对每次的结果进行处理
     * @param resultFunc 获取list集合， 第一个参数是最小值id，第二个参数获取到的list集合
     * @param minIdFunc 获取最小值id， 第一个参数是list集合，第二个参数是获取到最小值
     *
     */
    public static <R> void processByMinId(
            OneParamFunction<Long, List<R>> resultFunc,
                                          Function<List<R>, Long> minIdFunc,
                                          SupportExceptionConsumer<List<R>> consumer){
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        long minId = 0;
        boolean needLogEvent = true;
        List<R> list = null;
        try{
            do{
                //获取结果集
                list = resultFunc.apply(minId);
                if(CollectionUtils.isEmpty(list)){
                    break;
                }
                //执行方法
                consumer.accept(list);
                //重新获取最小值
                minId = minIdFunc.apply(list);
                pageIndex = pageIndex +1;

                if(needLogEvent){
                    //满足条件时，上报cat
                    needLogEvent = processLogEvent(pageIndex, needLogEvent, list.get(0).getClass().getSimpleName());
                }
//                if(!MccConfig.whileUtilSwitch){
//                    break;
//                }
            }while (CollectionUtils.isNotEmpty(list));
        }catch (Exception e){
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 通过offset、limit 循环获取数据, 并对每次的结果进行处理
     * @param resultFunc 获取list集合， 第一个入参是offset，第二个是 limit，第三个是获取到的list集合
     * @param pageSize pageSize
     * @param consumer 对结果集进行处理的函数
     */
    public static <R> void processByOffsetLimit(TwoParamFunction<Integer, Integer, List<R>> resultFunc,
                                                int pageSize,
                                                SupportExceptionConsumer<List<R>> consumer){
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        int offset = 0;
        boolean needLogEvent = true;
        List<R> list = null;
        try{
            do{
                //获取结果集
                list = resultFunc.apply(offset, pageSize);
                if(CollectionUtils.isEmpty(list)){
                    break;
                }
                //执行方法
                consumer.accept(list);

                pageIndex = pageIndex +1;
                offset = pageIndex*pageSize;

                if(needLogEvent){
                    //满足条件时，上报cat
                    needLogEvent = processLogEvent(pageIndex, needLogEvent, list.get(0).getClass().getSimpleName());
                }
//                if(!MccConfig.whileUtilSwitch){
//                    break;
//                }
            }while (CollectionUtils.isNotEmpty(list) && list.size() == pageSize);
        }catch (Exception e){
            throw ConvertUtils.wrapGateWayException(e);
        }
    }

    /**
     * 多线程获取全部数据
     * @param resultFunc 获取结果集  第一个入参是offset，第二个是 limit，第三个是返回的结果集
     * @param total 总的条数
     * @param pageSize pageSize
     * @param poolName 线程池的名称
     * @param second 线程等待的时间  秒为单位
     */
    public static <R> List<R> getFullDataByMutiThread(TwoParamFunction<Integer, Integer, List<R>> resultFunc,
                                                      long total,
                                                      int pageSize,
                                                      ThreadPoolNames poolName,
                                                      int second){
       return getFullDataByMutiThread(resultFunc,total,pageSize,poolName,second,0);
    }

    /**
     * 多线程获取全部数据
     * @param resultFunc 获取结果集  第一个入参是offset，第二个是 limit，第三个是返回的结果集
     * @param total 总的条数
     * @param pageSize pageSize
     * @param poolName 线程池的名称
     * @param second 线程等待的时间  秒为单位
     * @param offset 从指定的偏移量开始获取数据
     */
    private static <R> List<R> getFullDataByMutiThread(TwoParamFunction<Integer, Integer, List<R>> resultFunc,
                                                      long total,
                                                      int pageSize,
                                                      ThreadPoolNames poolName,
                                                      int second,
                                                      int offset){
        //页数，从0开始（0表示的是第一页）
        int pageIndex = 0;
        boolean needLogEvent = true;
        List<FutureTask<List<R>>> futureTasks = new ArrayList<>();
        do{
            int finalOffset = offset;
            FutureTask<List<R>> task = new FutureTask<>(() -> {
                List<R> list = resultFunc.apply(finalOffset, pageSize);
                return CollectionUtils.isNotEmpty(list) ? list : new ArrayList<>();
            });
            futureTasks.add(task);
            pageIndex = pageIndex + 1;
            offset = offset + pageSize;

            if(needLogEvent){
                //满足条件时，上报cat
                needLogEvent = processLogEvent(pageIndex, needLogEvent, "getFullDataByMutiThread");
            }
//            if(!MccConfig.whileUtilSwitch){
//                break;
//            }
        }while (offset < total);
        List<List<R>> list2list = ThreadPoolUtils.submitAndGet(poolName, futureTasks, second, TimeUnit.SECONDS);
        return ThreadPoolUtils.listArrary2List(list2list);
    }

    /**
     * 满足条件时上报cat
     * @param pageIndex 第几次循环，从0开始
     * @param needLogEvent 是否需要上报cat
     * @param logEventName
     */
    private static boolean processLogEvent(int pageIndex, boolean needLogEvent, String logEventName){
        if(pageIndex > 10 && needLogEvent){
//            Cat.logEvent(CatTypeEnum.WHILECAT.getTypeName(), logEventName);
            needLogEvent = false;
        }
        return needLogEvent;
    }

}
