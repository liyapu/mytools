package com.lyp.learn.base.enums;

import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 20:29
 */
public class EnumTest {

    @Test
    public void enumSwitchCase(){
        Color color = Color.getColorByCode("red");
        switch (color){
            case RED:
                System.out.println("color is red");
                break;
            case YELLOW:
                System.out.println("color is yellow");
                break;
            case GREEN:
                System.out.println("color is green");
                break;
            default:
        }
    }

    @Test
    public void testName(){
        Color red = Color.valueOf("RED");
        System.out.println(red.name());
        System.out.println(red.getCode());
        System.out.println(red.getDesc());
        System.out.println(red.ordinal());
    }
}
