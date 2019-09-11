package com.lyp.learn.dp.pattern.builderpattern2;

/**
 * 电脑产品抽象类
 */
public  abstract class Computer {
    private String type;//型号

    private String cpu;//cpu

    private String ram;//内存

    private String hardDisk;//硬盘

    private String os;//操作系统

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
