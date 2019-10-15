package com.lyp.learn.base.enums;

import org.junit.jupiter.api.Test;

public enum Color {
    RED("red","红灯停"),
    GREEN("green","绿灯行"),
    YELLOW("yellow","黄灯亮了等一等");

    private String code;
    private String desc;

    Color(String code,String desc){
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


