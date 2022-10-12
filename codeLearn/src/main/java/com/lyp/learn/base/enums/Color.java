package com.lyp.learn.base.enums;

import org.junit.jupiter.api.Test;

public enum Color {
    /**
     *
     */
    RED("red", "红灯停"),
    GREEN("green", "绿灯行"),
    YELLOW("yellow", "黄灯亮了等一等"),
    BLUE("blue", "等");

    private String code;
    private String desc;

    Color(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * values()方法的作用就是获取枚举类中的所有变量，并作为数组返回，
     * valueOf(String name)方法与Enum类中的valueOf方法的作用类似根据名称获取枚举变量，只不过编译器生成的valueOf方法更简洁些只需传递一个参数
     */
    public static Color getColorByCode(String code){
//        Color color1 = Color.valueOf("YELLOW");

        Color[] values = Color.values();
        Color color = null;
        for(Color c : values){
            if(c.getCode().equals(code)){
                color = c;
                break;
            }
        }
        if(color == null){
            throw new RuntimeException();
        }
        return color;
    }



    @Override
    public String toString() {
        return "Color{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Test
    public void testValues(){
        Color[] values = Color.values();
        for(Color color : values){
            System.out.println(color);
        }

    }

}


