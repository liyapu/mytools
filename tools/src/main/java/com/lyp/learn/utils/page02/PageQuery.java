package com.lyp.learn.utils.page02;

import java.util.List;

public interface PageQuery<T> {
    // 获取分页结果
    List<T> getPageResult(int offset, int limit);
}
