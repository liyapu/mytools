package com.lyp.learn.base.annotation.fruit;
/**
 * @Author: liyapu
 * @Description: 注解使用
 * @create: 2018-11-02 10:49
 */
public class Apple {

    @FruitName(value = "Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.YELLOW)
    private String appleColor;

    @FruitProvider(id=1,name="红富士集团",address = "北京路88号大厦")
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
