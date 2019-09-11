package com.lyp.learn.dp.pattern.builderpattern2;

/**
 * 建造计算机的 Director
 */
public class ComputerDirector {
    Builder thinkpadBuilder = null;
    Builder dellBuilder = null;

    /**
     * 具体构建Thinkpad
     * @return
     */
    public ThinkPad createThinkPad(){
        thinkpadBuilder =  new ThinkPadBuilder();
        thinkpadBuilder.buildCpu();
        thinkpadBuilder.buildRam();
        thinkpadBuilder.buildHardDisk();
        thinkpadBuilder.buildOs();
        thinkpadBuilder.buildGraphicCard();
        return (ThinkPad) thinkpadBuilder.getResult();
    }

    /**
     * 具体构建Dell
     * @return
     */
    public Dell createDell(){
        dellBuilder = new DellBuilder();
        dellBuilder.buildCpu();
        dellBuilder.buildRam();
        dellBuilder.buildHardDisk();
        dellBuilder.buildOs();
        return (Dell)dellBuilder.getResult();
    }
}
