package com.lyp.learn.dp.pattern.builderpattern2;

public interface  Builder {

    void buildCpu(); //建造cpu

    void buildRam(); //建造内存

    void buildHardDisk();//建造硬盘

    void buildOs();//建造操作系统

    void buildGraphicCard();//建造显卡

    Computer getResult();//得到建造好的对象

}
