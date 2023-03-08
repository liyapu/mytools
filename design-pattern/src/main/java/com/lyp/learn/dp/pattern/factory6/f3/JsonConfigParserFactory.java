package com.lyp.learn.dp.pattern.factory6.f3;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;
import com.lyp.learn.dp.pattern.factory6.classs.JsonRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:35
 * @description
 */
public class JsonConfigParserFactory implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}