package com.lyp.learn.dp.pattern.builderpattern1;

public class Product {
    private String partA;
    private String partB;
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public String toString() {
        return "Product \n" +
                "partA = " + partA + '\n' +
                "partB = " + partB + '\n' +
                "partC = " + partC + '\n' ;
    }
}
