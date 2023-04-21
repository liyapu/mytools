package com.lyp.learn.dp.pattern.chainpattern3.demo3;

/**
 * @author liyapu
 * @date 2023-04-20 20:29
 * @description 每个过滤器都处理
 */
public class ApplicationClient {

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.addHandler(new HandlerC());
        chain.handle();
    }
}
