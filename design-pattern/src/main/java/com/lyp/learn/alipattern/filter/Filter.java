package com.lyp.learn.alipattern.filter;

/**
 * @author liyapu
 * @date 2023-11-17 22:55
 * @description
 */
public interface Filter {
    void doFilter(HttpRequest httpRequest, FilterChain filterChain);

}
