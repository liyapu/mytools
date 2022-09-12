package com.lyp.learn.base.jucatguigu.cf04;

import com.lyp.learn.base.executors.pk01.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyapu
 * @date 2022-09-12 14:13
 * @description
 */
@Data
@NoArgsConstructor
public class NetMall {


    /**
     * 网店商城名称
     */
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    /**
     * 计算价格
     *
     * @param productName 商品名称
     * @return 商品价格
     */
    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
