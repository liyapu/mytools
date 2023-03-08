package com.lyp.learn.dp.pattern.factory6.f3;

import com.lyp.learn.dp.pattern.factory6.classs.RuleConfig;

/**
 * @author liyapu
 * @date 2023-03-08 19:35
 * @description
 */
public interface ISystemConfigParser {

    /**
     * 解析
     *
     * @param configText
     * @return
     */
    RuleConfig parse(String configText);
}
