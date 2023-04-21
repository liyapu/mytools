package com.lyp.learn.dp.pattern.chainpattern3.demo4;

/**
 * @author liyapu
 * @date 2023-04-20 20:37
 * @description
 */
public class SexyWordFilter implements SensitiveWordFilter {

    @Override
    public boolean doFilter(Content content) {
        //合法的
        boolean legal = true;
        //...
        return legal;
    }
}