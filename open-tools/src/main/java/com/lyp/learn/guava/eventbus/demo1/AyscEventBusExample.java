package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.AsyncEventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.BlockListener;
import com.lyp.learn.guava.eventbus.demo1.listeners.SimpleListener;
import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.Executors;

public class AyscEventBusExample {
    public static void main(String[] args) {
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(4));
        asyncEventBus.register(new BlockListener());

        asyncEventBus.post("aa88");
    }
}
