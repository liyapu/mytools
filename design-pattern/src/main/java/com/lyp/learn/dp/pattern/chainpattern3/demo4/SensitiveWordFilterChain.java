package com.lyp.learn.dp.pattern.chainpattern3.demo4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyapu
 * @date 2023-04-20 20:39
 * @description
 */
public class SensitiveWordFilterChain {

    private List<SensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(SensitiveWordFilter filter) {
        this.filters.add(filter);
    }

    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {
        for (SensitiveWordFilter filter : filters) {
            if (!filter.doFilter(content)) {
                return false;
            }
        }
        return true;
    }
}