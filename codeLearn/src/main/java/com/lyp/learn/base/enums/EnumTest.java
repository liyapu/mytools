package com.lyp.learn.base.enums;

import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 20:29
 */
public class EnumTest {

    @Test
    public void testInteger3() {
        Integer i1 = 129;
        Integer i2 = 129;
        System.out.println("i1 == i2 :" + (i1 == i2));
        System.out.println("i1 equals i2 :" + (i1.equals(i2)));
    }

    @Test
    public void testInteger2() {
        Integer i1 = 128;
        Integer i2 = 128;
        System.out.println("i1 == i2 :" + (i1 == i2));
        System.out.println("i1 equals i2 :" + (i1.equals(i2)));
    }

    @Test
    public void testInteger() {
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println("i1 == i2 :" + (i1 == i2));
        System.out.println("i1 equals i2 :" + (i1.equals(i2)));
    }

    @Test
    public void testDoubleStr() {
        String str = "1000222.00000";
        Double d = Double.parseDouble(str);
        //Long result = Long.parseLong(str);
        Long result = d.longValue();
        System.out.println("result = " + result);
    }

    @Test
    public void enumSwitchCase() {
        Color color = Color.getColorByCode("red");
        switch (color) {
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

    /**
     * 枚举实现抽象方法测试
     */
    @Test
    public void testEnumAbstract(){
        EnumAbstract[] values = EnumAbstract.values();
        for(EnumAbstract ea : values){
            String str = ea.getInfo();
            System.out.println(str);
        }
    }

    @Test
    public void testFood(){
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        Food.MainCourse padThai = Food.MainCourse.PAD_THAI;
        food  = Food.MainCourse.PAD_THAI;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;
    }


}
