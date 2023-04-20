package com.lyp.learn.base.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author liyapu
 * @date 2023-04-20 14:32
 * @description SPI全名Service Provider interface，翻译过来就是“服务提供接口”，
 * 再说简单就是提供某一个服务的接口， 提供给服务开发者或者服务生产商来进行实现。
 *
 * JavaSPI详解  https://blog.csdn.net/Q54665642ljf/article/details/127610418
 * Java SPI详解 https://blog.csdn.net/lukabruce/article/details/124671361
 *
 * SPI和API的使用场景
 * API （Application Programming Interface）在大多数情况下，都是实现方制定接口并完成对接口的实现，调用方仅仅依赖接口调用，且无权选择不同实现。 从使用人员上来说，API 直接被应用开发人员使用。
 * SPI （Service Provider Interface）是调用方来制定接口规范，提供给外部来实现，调用方在调用时则选择自己需要的外部实现。  从使用人员上来说，SPI 被框架扩展人员使用。
 */
public class MainTest {

    public static void main(String[] args) {
        ServiceLoader<Registry> load = ServiceLoader.load(Registry.class);
        Iterator<Registry> iterator = load.iterator();
        while (iterator.hasNext()) {
            Registry registry = iterator.next();
            registry.registry("127.0.0.1", 10086);
        }
    }

}
