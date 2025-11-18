package com.lyp.learn.utils.p2;

import java.util.List;


public interface PageQueryByLastMaxQueryId<T> {
    /**
     * 获取分页结果
     *
     * @param limit          分页条数
     * @param lastMaxQueryId lastMaxQueryId
     * @return {@link List}
     * @see List
     * @see T
     */
    List<T> getPageResultByLastMaxQueryId(int limit, long lastMaxQueryId);
}