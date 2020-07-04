package com.lyp.learn.graph.lianjiejuzhen;

/**
 * 普里姆算法构造最小生成树
 * 辅助记录从顶点集U到 V-U的代价最小的边
 */
public class CloseEdge {
    private Object adjVex;
    int lowCost;

    public CloseEdge(Object adjVex,int lowCost){
        this.adjVex = adjVex;
        this.lowCost = lowCost;
    }

    public Object getAdjVex() {
        return adjVex;
    }

    public void setAdjVex(Object adjVex) {
        this.adjVex = adjVex;
    }

    public int getLowCost() {
        return lowCost;
    }

    public void setLowCost(int lowCost) {
        this.lowCost = lowCost;
    }
}
