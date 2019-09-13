package com.lyp.learn.pk08.huffman;

/**
 * 哈夫曼树的结点类描述
 */
public class HuffmanNode {
    //结点的权值
    public int weight;

    //结点是否加入哈夫曼树的标志  true:已经加入; false 未加入
    public boolean join;

    //父结点及左、右孩子结点
    public HuffmanNode parent,lchild,rchild;

    public HuffmanNode(){
        this(0);
    }

    public HuffmanNode(int weight){
        this.weight = weight;
        join = false;
        parent = lchild= rchild = null;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public HuffmanNode getLchild() {
        return lchild;
    }

    public void setLchild(HuffmanNode lchild) {
        this.lchild = lchild;
    }

    public HuffmanNode getRchild() {
        return rchild;
    }

    public void setRchild(HuffmanNode rchild) {
        this.rchild = rchild;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "weight=" + weight +
                ", join=" + join +
                ", parent=" + parent +
                ", lchild=" + lchild +
                ", rchild=" + rchild +
                '}';
    }
}
