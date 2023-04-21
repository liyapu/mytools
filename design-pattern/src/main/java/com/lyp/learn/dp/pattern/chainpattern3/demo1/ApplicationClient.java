package com.lyp.learn.dp.pattern.chainpattern3.demo1;

/**
 * @author liyapu
 * @date 2023-04-20 20:05
 * @description 应用客户端使用样例
 * 遇到合适的过滤器即停止执行
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
