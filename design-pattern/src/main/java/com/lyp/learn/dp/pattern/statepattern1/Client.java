package com.lyp.learn.dp.pattern.statepattern1;

/**
 *  客户端
 */
public class Client {
    public static void main(String[] args) {
        //定义环境角色
        Context context = new Context();

        //执行行为
        context.handle1();
        context.handle2();
    }
}
