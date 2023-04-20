package com.lyp.learn.base.spi;

/**
 * @author liyapu
 * @date 2023-04-20 14:27
 * @description 首先定义一个接口Registry, 这个接口只有一个功能，就是向注册中心注册一个服务，
 * 但是我现在并不确定我选的是什么注册中心，于是提供了一个统一的接口，由各个厂商进行实现
 */
public interface Registry {

    void registry(String host, int port);
}
