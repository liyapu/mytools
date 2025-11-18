package com.lyp.learn.utils.page03;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class PageQueryUtil {

    private static final int DEAD_CYCLE_LIMIT = 50000;

    /**
     * 分页获取全部数据
     *
     * @param pageQuery             查询逻辑
     * @param pageSize              分页大小
     * @param deadCycleErrorMessage 死循环提示信息
     * @param <T>
     * @return
     */
    public static <T> List<T> queryAllResultByPage(PageQuery<T> pageQuery, int pageSize, String deadCycleErrorMessage) {
        int cycleCount = 0;
        int offset = 0;
        List<T> result = Lists.newArrayList();
        while (true) {
            if (cycleCount > DEAD_CYCLE_LIMIT) {
//                throw new DeadCycleException(deadCycleErrorMessage);
                throw new RuntimeException(deadCycleErrorMessage);
            }
            List<T> list = pageQuery.getPageResult(offset, pageSize);
            result.addAll(list);
            if (CollectionUtils.size(list) < pageSize) {
                break;
            }
            offset = offset + pageSize;
            cycleCount++;
        }
        return result;
    }

    public static <T> List<T> queryAllResultByPage(PageQueryByLastMaxQueryId<T> pageQueryByLastMaxQueryId,
                                                   int pageSize,
                                                   String deadCycleErrorMessage,
                                                   Function<T, Long> function) {
        List<T> ans = Lists.newArrayList();
        int cycleCount = 0;
        long lastMaxQueryId = -1;
        while (true) {
            if (cycleCount > DEAD_CYCLE_LIMIT) {
//                throw new DeadCycleException(deadCycleErrorMessage);
                throw new RuntimeException(deadCycleErrorMessage);
            }
            List<T> list = pageQueryByLastMaxQueryId.getPageResultByLastMaxQueryId(pageSize, lastMaxQueryId);
            ans.addAll(list);
            if (CollectionUtils.size(list) < pageSize) {
                break;
            }
            lastMaxQueryId = function.apply(list.get(list.size() - 1));
            cycleCount++;
        }
        return ans;
    }

    /**
     * 上层先查询出来最小的id,然后传入进来，这样防止 最小值 -1 耗时过长
     * @param pageQueryByLastMaxQueryId
     * @param pageSize
     * @param deadCycleErrorMessage
     * @param function
     * @param defaultMaxId
     * @return
     * @param <T>
     */
    public static <T> List<T> queryAllResultByPageHasDefaultMaxId(PageQueryByLastMaxQueryId<T> pageQueryByLastMaxQueryId,
                                                   int pageSize,
                                                   String deadCycleErrorMessage,
                                                   Function<T, Long> function, Long defaultMaxId) {
        List<T> ans = Lists.newArrayList();
        int cycleCount = 0;
        long lastMaxQueryId = -1;
        if(Objects.nonNull(defaultMaxId)){
            lastMaxQueryId = defaultMaxId;
        }
        while (true) {
            if (cycleCount > DEAD_CYCLE_LIMIT) {
//                throw new DeadCycleException(deadCycleErrorMessage);
                throw new RuntimeException(deadCycleErrorMessage);
            }
            List<T> list = pageQueryByLastMaxQueryId.getPageResultByLastMaxQueryId(pageSize, lastMaxQueryId);
            ans.addAll(list);
            if (CollectionUtils.size(list) < pageSize) {
                break;
            }
            lastMaxQueryId = function.apply(list.get(list.size() - 1));
            cycleCount++;
        }
        return ans;
    }
}
