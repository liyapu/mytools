package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:02
 * @description
 */

import com.google.common.collect.Maps;

import java.util.Map;

public class StandardPipelineContext implements PipelineContext {

    private Map<String, Object> contentMap = Maps.newConcurrentMap();

    @Override
    public void set(String contextKey, Object contextValue) {
        contentMap.put(contextKey, contextValue);
    }

    @Override
    public Object get(String contextKey) {
        return contentMap.get(contextKey);
    }
}