package com.lyp.learn.dp.pattern.chainpattern3.demo4;

/**
 * @author liyapu
 * @date 2023-04-20 20:40
 * @description
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();
        filterChain.addFilter(new AdsWordFilter());
        filterChain.addFilter(new SexyWordFilter());
        filterChain.addFilter(new PoliticalWordFilter());

        boolean legal = filterChain.filter(new Content());
        if (!legal) {
            // 不发表
            System.out.println("不发表");
        } else {
            // 发表
            System.out.println("发表");
        }
    }

}