package com.lyp.learn.dp.pattern.builderpattern2;

public class DellBuilder implements Builder {
    Dell dell = new Dell();

    @Override
    public void buildCpu() {
        dell.setCpu("ARM 10");
    }

    @Override
    public void buildRam() {
        dell.setRam("Kingston DDR4 ");
    }

    @Override
    public void buildHardDisk() {
        dell.setHardDisk("250G 固态硬盘");
    }

    @Override
    public void buildOs() {
        dell.setOs("Centos 7");
    }

    @Override
    public void buildGraphicCard() {
        //无显卡，什么也不做
    }

    @Override
    public Computer getResult() {
        return dell;
    }
}
