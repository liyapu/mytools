package com.lyp.learn.pk2;

import cn.hutool.json.JSONUtil;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-30 20:34
 */
public class NumDemo {
    //静态变量 prepare 准备阶段 sa 赋值为 0  --->  initial 初始化阶段，才会真正的赋值 sa = 88
    // 这里的静态变量不包含 finial修饰的static,因为 finial 在编译的时候就会分配了，当常量使用了，准备阶段会显式初始化
    static int sa = 88;

    public static void main(String[] args) {
        int a = 6;
        Integer aa = 7;

        System.out.println(sa + a + aa);
    }

}
