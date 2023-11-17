package com.lyp.learn.alipattern.filter;

/**
 * @author liyapu
 * @date 2023-11-17 22:57
 * @description 入口测试
 *
 * 过滤器(Filter)----实际处理业务的节点
 * 过滤链(FilterChain)----串联过滤器的链条
 */
public class FilterClient {
    public static void main(String[] args) {
        FilterChain filterChain = new StandardFilterChain();

        filterChain.addFilter(new ForTest1Filter());
        filterChain.addFilter(new ForTest2Filter());

        filterChain.doFilter(new StandardHttpRequest());
    }
}
