package com.lyp.learn.base.spi.springfactories;

/**
 * @author liyapu
 * @date 2023-04-20 15:51
 * @description springboot中META-INF/spring.factories解析加载类
 *
 * springboot中META-INF/spring.factories解析加载类  https://blog.csdn.net/qq_34484062/article/details/111589411
 *
 * SpringFactoriesLoader类是解析所有Spring.factories实现。方法主要是loadFactoryNames()和loadSpringFactories()
 * spring.factories就像是工厂一样配置了大量的接口对应的实现类，我们通过这些配置 + 反射处理就可以拿到相应的实现类。
 * 这种类似于插件式的设计方式，只要引入对应的jar包，那么对应的spring.factories就会被扫描到，对应的实现类也就会被实例化，如果不需要的时候，直接把jar包移除即可。
 */
public interface Info {

}
