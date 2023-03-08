package com.lyp.learn.dp.pattern.factory6.f3;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:33
 * @description 抽象工厂（Abstract Factory）
 * <p>
 * 在简单工厂和工厂方法中，类只有一种分类方式。比如，在规则配置解析那个例子中，解析器类只会根据配置文件格式（Json、Xml、Yaml……）来分类。
 * 但是，如果类有两种分类方式，比如，我们既可以按照配置文件格式来分类，也可以按照解析的对象（Rule规则配置还是System系统配置）来分类，那就会对应下面这8个parser类。
 * <p>
 * 针对规则配置的解析器：基于接口IRuleConfigParser
 * JsonRuleConfigParser
 * XmlRuleConfigParser
 * YamlRuleConfigParser
 * PropertiesRuleConfigParser
 * <p>
 * 针对系统配置的解析器：基于接口ISystemConfigParser
 * JsonSystemConfigParser
 * XmlSystemConfigParser
 * YamlSystemConfigParser
 * PropertiesSystemConfigParser
 * 针对这种特殊的场景，如果还是继续用工厂方法来实现的话，我们要针对每个parser都编写一个工厂类，也就是要编写8个工厂类。
 * 如果我们未来还需要增加针对业务配置的解析器（比如IBizConfigParser），那就要再对应地增加4个工厂类。
 * 而我们知道，过多的类也会让系统难维护。这个问题该怎么解决呢？
 * <p>
 * 抽象工厂就是针对这种非常特殊的场景而诞生的。我们可以让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser等），
 * 而不是只创建一种parser对象。这样就可以有效地减少工厂类的个数。具体的代码实现如下所示：
 */
public interface IConfigParserFactory {

    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}
