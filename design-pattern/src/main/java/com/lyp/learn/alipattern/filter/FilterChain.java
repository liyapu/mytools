package com.lyp.learn.alipattern.filter;

/**
 * @author liyapu
 * @date 2023-11-17 22:56
 * @description
 */
public interface FilterChain {
    void doFilter(HttpRequest httpRequest);

    void addFilter(Filter filter);
}
