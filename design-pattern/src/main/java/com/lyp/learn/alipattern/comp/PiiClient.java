package com.lyp.learn.alipattern.comp;

import java.util.Map;

/**
 * @author liyapu
 * @date 2023-11-17 23:05
 * @description 基于组合/模板的套路
 * 处理器注册器----用于存储处理器的集合
 * 处理器工厂----用于创建处理器
 * 处理器----实际的处理器以及扩展的实现
 * 处理器上下文----处理器上下文，用于参数的传递
 *
 * 适用场景
 * 适合于有共性、后续持续扩展的场景
 */
public class PiiClient {
    /**
     * 入口的测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        PiiHandlerRegistry.init();

        // 遍历处理器
        for (Map.Entry<String, PiiDomainFieldHandler> entryHandler : PiiHandlerRegistry.getPiiDomainFieldHandlerMap().entrySet()) {
            System.out.println("main ---> " + entryHandler.getKey() + "\t" + entryHandler.getValue().getPiiDomainMeta());
        }

        //
        PiiContent piiContent = new PiiContent();
        piiContent.putPiiContext(PiiContent.FORTEST, PiiContent.FORTEST);

        // 请求处理
        System.out.println();
        System.out.println("main ForTestSupportFieldHandler start");
        PiiHandlerRegistry.handlerRead(new ForTestSupportFieldHandler(), null, piiContent);
        System.out.println("main ForTestSupportFieldHandler end");
        System.out.println();

        // 请求处理
        System.out.println("main ForTestNotSupportFieldHandler start");
        PiiHandlerRegistry.handlerRead(new ForTestNotSupportFieldHandler(), null, piiContent);
        System.out.println("main ForTestNotSupportFieldHandler end");

    }
}
/**
 * 常见框架中的应用
 * 这个就太多了，例如spring最核心的BeanPostProcessor机制，通过org.springframework.beans.factory.support.AbstractBeanFactory#beanPostProcessors管理一些列的beanPostProcessors，
 * 在spring上下文org.springframework.context.support.AbstractApplicationContext#refresh的时候，
 * 进行bean的init(InitDestroyAnnotationBeanPostProcessor)、
 * 解析注解(ScheduledAnnotationBeanPostProcessor、AutowiredAnnotationBeanPostProcessor)、
 * 解析aop（AnnotationAwareAspectJAutoProxyCreator）等等。
 */
