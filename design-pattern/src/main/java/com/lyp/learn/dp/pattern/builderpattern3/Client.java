package com.lyp.learn.dp.pattern.builderpattern3;

/**
 * @author liyapu
 * @date 2023-03-09 09:18
 * @description
 */
public class Client {

    public static void main(String[] args) {
        SkuReturnReasonBO skuReason = SkuReturnReasonBO.builder()
            .firstValue(1)
            .secondValue(2)
            .reasonText("我是来找bug的")
            .build();
        System.out.println("skuReason = " + skuReason);
    }
}
