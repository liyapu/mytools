package com.lyp.learn.other.rules;

/**
 * @author liyapu
 * @date 2023-11-17 22:18
 * @description
 */
// 规则抽象
public interface BaseRule {

    boolean execute(RuleDto dto);
}
