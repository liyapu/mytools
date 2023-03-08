package com.lyp.learn.dp.pattern.factory6.classs;

/**
 * @author liyapu
 * @date 2023-03-08 19:12
 * @description
 */
public interface IRuleConfigParser {

    /**
     * 解析
     *
     * @param configText
     * @return
     */
    RuleConfig parse(String configText);

}
