package com.lyp.learn.pk11.lianjietable;

/**
 * 图的邻接表存储表示中的 边(或弧)的结点类
 */
public class ArcNode {

    //该弧所指向的顶点位置
    private int adjVex;

    //边(或弧)的权值
    private int value;

    //指向下一条弧
    private ArcNode nextArc;

    public ArcNode(int adjVex){
        this(adjVex,0,null);
    }

    public ArcNode(int adjVex,int value){
        this(adjVex,value,null);
    }

    public ArcNode(int adjVex,int value,ArcNode nextArc){
        this.adjVex = adjVex;
        this.value = value;
        this.nextArc = nextArc;
    }

    public int getAdjVex() {
        return adjVex;
    }

    public void setAdjVex(int adjVex) {
        this.adjVex = adjVex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArcNode getNextArc() {
        return nextArc;
    }

    public void setNextArc(ArcNode nextArc) {
        this.nextArc = nextArc;
    }

    @Override
    public String toString() {
        return "ArcNode{" +
                "adjVex=" + adjVex +
                ", value=" + value +
                ", nextArc=" + nextArc +
                '}';
    }
}
