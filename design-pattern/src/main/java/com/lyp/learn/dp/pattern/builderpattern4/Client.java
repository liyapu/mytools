package com.lyp.learn.dp.pattern.builderpattern4;

/**
 * @author liyapu
 * @date 2023-03-09 09:25
 * @description
 */
public class Client {

    public static void main(String[] args) {
        // 这段代码会抛出IllegalArgumentException，因为minIdle>maxIdle
        ResourcePoolConfig3 config = new ResourcePoolConfig3.Builder()
            .setName("dbconnectionpool")
            .setMaxTotal(16)
            .setMaxIdle(10)
            .setMinIdle(12)
            .build();

        System.out.println(" config =" + config);
    }

}
