package com.lyp.learn.dp.pattern.factory6.f2;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;
import com.lyp.learn.dp.pattern.factory6.classs.XmlRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:26
 * @description
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {

    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}