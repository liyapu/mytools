package com.lyp.learn.base.annotation.importt;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: liyapu
 * @Description: 在应用中，有时没有把某个类注入到IOC容器中，但在运用的时候需要获取该类对应的bean，此时就需要用到@Import注解
 * 通过导入的方式实现把实例加入springIOC容器中
 *
 * 通过查看@Import源码可以发现@Import注解只能注解在类上，以及唯一的参数value上可以配置3种类型的值Configuration，ImportSelector，ImportBeanDefinitionRegistrar
 *
 * 接下来就分别来看看三种方式具体使用：
 * a，基于Configuration也就是直接填对应的class数组
 * b，基于自定义ImportSelector的使用
 * c，基于ImportBeanDefinitionRegistrar的使用
 *
 * @create: 2018-11-07 23:55
 */

@Import({Square.class,Circular.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig {
}
