package com.lyp.learn.dp.pattern.factory6.f2;

import com.lyp.learn.dp.pattern.factory6.classs.IRuleConfigParser;

/**
 * @author liyapu
 * @date 2023-03-08 19:25
 * @description 实际上，这就是工厂方法模式的典型代码实现。这样当我们新增一种parser的时候，只需要新增一个实现了IRuleConfigParserFactory接口的Factory类即可。
 * 所以，工厂方法模式比起简单工厂模式更加符合开闭原则。
 */
public interface IRuleConfigParserFactory {

    IRuleConfigParser createParser();
}
