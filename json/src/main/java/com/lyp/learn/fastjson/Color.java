package com.lyp.learn.fastjson;

public enum Color {
    RED(1,"红色"),
    GREEN(2,"绿色"),
    YELLOW(3,"黄色");

    private int code;
    private String desc;

    Color(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
