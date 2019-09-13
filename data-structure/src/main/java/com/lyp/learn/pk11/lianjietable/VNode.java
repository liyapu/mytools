package com.lyp.learn.pk11.lianjietable;

/**
 * 图的邻接表存储表示中的顶点结点类
 */
public class VNode {

    //顶点信息
    private Object data;

    //指向第一条依附于该顶点的弧
    private ArcNode firstArc;

    public VNode(){
        this(null,null);
    }

    public VNode(Object data){
        this(data,null);
    }

    public VNode(Object data,ArcNode firstArc){
        this.data = data;
        this.firstArc = firstArc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ArcNode getFirstArc() {
        return firstArc;
    }

    public void setFirstArc(ArcNode firstArc) {
        this.firstArc = firstArc;
    }

    @Override
    public String toString() {
        return "VNode{" +
                "data=" + data +
                ", firstArc=" + firstArc +
                '}';
    }
}
