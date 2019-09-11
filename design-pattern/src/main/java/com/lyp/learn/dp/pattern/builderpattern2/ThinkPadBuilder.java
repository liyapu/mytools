package com.lyp.learn.dp.pattern.builderpattern2;

public class ThinkPadBuilder implements Builder {
    ThinkPad thinkPad = new ThinkPad();

    @Override
    public void buildCpu() {
        thinkPad.setCpu("intel i8 8th Gen");
    }

    @Override
    public void buildRam() {
        thinkPad.setRam("8GB DDR4");
    }

    @Override
    public void buildHardDisk() {
        thinkPad.setHardDisk("500G 2000转");
    }

    @Override
    public void buildOs() {
        thinkPad.setOs("Windows 10 操作系统");
    }

    @Override
    public void buildGraphicCard() {
        thinkPad.setGraphicCard("Nvidia 显卡");
    }

    @Override
    public Computer getResult() {
        return thinkPad;
    }
}
