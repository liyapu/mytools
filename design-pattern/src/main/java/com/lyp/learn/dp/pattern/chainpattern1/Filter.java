package com.lyp.learn.dp.pattern.chainpattern1;

/**
 * 责任链模式
 * 定义接口Filter,具体的过滤规则需要实现这个接口
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);

}
