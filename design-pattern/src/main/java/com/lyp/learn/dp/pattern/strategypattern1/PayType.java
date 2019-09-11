package com.lyp.learn.dp.pattern.strategypattern1;

/**
 * 支付类型枚举
 */
public enum PayType {
    ALI_APY(new AliPay()),
    WX_PAY(new WxPay()),
    UNION_PAY(new UnionPay());

    // 每一个枚举实例都会拥有自己的commonPay
    private CommonPay commonPay;

    PayType(CommonPay commonPay){
        this.commonPay = commonPay;
    }

    public CommonPay getCommonPay() {
        return commonPay;
    }
}
