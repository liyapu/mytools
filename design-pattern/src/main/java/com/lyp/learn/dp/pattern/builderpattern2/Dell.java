package com.lyp.learn.dp.pattern.builderpattern2;

public class Dell extends Computer {

    public Dell(){
        this.setType("Dell");
    }

    @Override
    public String toString() {
        return "Dell:\n" +
                "type = " + getType() + "\n" +
                "cpu = " + getCpu() + "\n" +
                "ram = " + getRam() + "\n" +
                "hardDisk =  " + getHardDisk() + "\n" +
                "os = " + getOs() + " \n"
                ;
    }
}
