package com.lyp.learn.tree.ercha;

public class BiTreeNode {
    //结点的数据域
    private Object data;
    //左右孩子域
    private BiTreeNode lchild,rchild;

    //构造一个空结点
    public BiTreeNode(){

    }

    //构造一个左右孩子域为空的二叉树
    public BiTreeNode(Object data){
        this(data,null,null);
    }

    //构造一个数据域和左右孩子域都不为空的二叉树
    public BiTreeNode(Object data,BiTreeNode lchild,BiTreeNode rchild){
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BiTreeNode getLchild() {
        return lchild;
    }

    public void setLchild(BiTreeNode lchild) {
        this.lchild = lchild;
    }

    public BiTreeNode getRchild() {
        return rchild;
    }

    public void setRchild(BiTreeNode rchild) {
        this.rchild = rchild;
    }

    @Override
    public String toString() {
        return "BiTreeNode{" +
                "data=" + data +
                ", lchild=" + lchild +
                ", rchild=" + rchild +
                '}';
    }
}
