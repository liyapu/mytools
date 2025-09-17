package com.lyp.learn.page;

import java.util.List;

/**
 * @author: liya11
 * @create: 2021/12/2
 **/
public interface PageQuery<T> {
    // 获取分页结果
    List<T> getPageResult(int offset, int limit);
}
