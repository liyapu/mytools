package com.lyp.learn.dp.pattern.interpreterpattern;

import java.util.HashMap;

/**
 * 环境
 */
public class Context {
    private HashMap<Expression,Integer> hashMap = new HashMap<>();

    public void add(Expression expression,Integer value){
        hashMap.put(expression,value);
    }

    public int get(Expression expression){
        return hashMap.get(expression);
    }

}
