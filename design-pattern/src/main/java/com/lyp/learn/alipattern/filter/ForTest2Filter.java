package com.lyp.learn.alipattern.filter;

/**
 * @author liyapu
 * @date 2023-11-17 22:55
 * @description
 */
public class ForTest2Filter implements Filter {
    @Override
    public void doFilter(HttpRequest httpRequest, FilterChain filterChain) {
        // do

        System.out.println(this.getClass().getSimpleName() + " before " + System.currentTimeMillis());

        filterChain.doFilter(httpRequest);

        // after

        System.out.println(this.getClass().getSimpleName() + " end " + System.currentTimeMillis());
    }
}