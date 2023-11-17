package com.lyp.learn.other.rules;

/**
 * @author liyapu
 * @date 2023-11-17 22:18
 * @description
 */
// 规则模板
public abstract class AbstractRule implements BaseRule {

    protected <T> T convert(RuleDto dto) {
        return (T) dto;
    }

    @Override
    public boolean execute(RuleDto dto) {
        return executeRule(convert(dto));
    }

    protected <T> boolean executeRule(T t) {
        return true;
    }
}