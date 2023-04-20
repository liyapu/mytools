package com.lyp.learn.base.spi.impl;

import com.lyp.learn.base.spi.Registry;

/**
 * @author liyapu
 * @date 2023-04-20 14:31
 * @description
 */
public class ZookeeperRegistry implements Registry {

    @Override
    public void registry(String host, int port) {
        System.out.println(this.getClass().getName() + "----- host:" + host + " --port:" + port);
    }
}
