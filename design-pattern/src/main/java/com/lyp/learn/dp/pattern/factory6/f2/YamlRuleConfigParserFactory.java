package com.lyp.learn.dp.pattern.factory6.f2;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;
import com.lyp.learn.dp.pattern.factory6.classs.YamlRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:27
 * @description
 */
public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {

    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
