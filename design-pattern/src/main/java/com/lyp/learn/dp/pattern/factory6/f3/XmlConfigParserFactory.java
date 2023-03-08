package com.lyp.learn.dp.pattern.factory6.f3;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;
import com.lyp.learn.dp.pattern.factory6.classs.XmlRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:36
 * @description
 */
public class XmlConfigParserFactory implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
