package com.lyp.learn.dp.pattern.builderpattern2;

public class ThinkPad extends Computer {

    private String graphicCard; //显卡

    public ThinkPad(){
        this.setType("ThinkPad T480");
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }

    @Override
    public String toString() {
        return "ThinkPad:\n" +
                "type = " + getType() + "\n" +
                "cpu = " + getCpu() + "\n" +
                "ram = " + getRam() + "\n" +
                "hardDisk =  " + getHardDisk() + "\n" +
                "os = " + getOs() + " \n" +
                "graphicCard = " + graphicCard + "\n"
                ;
    }
}
