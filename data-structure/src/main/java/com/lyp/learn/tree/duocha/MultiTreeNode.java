package com.lyp.learn.tree.duocha;

import java.util.List;

/**
 * 多叉树结点模型
 */
public class MultiTreeNode {
    //数据域
    private Object data;
    //孩子结点域
    private List<MultiTreeNode> childs;

    //构造空结点
    public MultiTreeNode(){
    }

    //构造 叶子结点(只有数据域，没有孩子域)
    public MultiTreeNode(Object data){
        this(data,null);
    }

    //构造有数据域和孩子域的结点
    public MultiTreeNode(Object data,List<MultiTreeNode> childs){
        this.data = data;
        this.childs = childs;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<MultiTreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<MultiTreeNode> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "MultiTreeNode{" +
                "data=" + data +
                ", childs=" + childs +
                '}';
    }
}
