package com.lyp.learn.tree.ercha2;

public class BiTreeTest {
    public static void main(String[] args) throws Exception {
        BiTree bitree = new BiTree();
        //插入操作
        bitree.insert(5);
        bitree.insert(2);
        bitree.insert(8);
        bitree.insert(1);
        bitree.insert(3);
        bitree.insert(6);
        bitree.insert(10);

        BiTreeNode root = bitree.getRoot();

        //前序遍历
        System.out.println("前序遍历：");
        bitree.preOrder(root);
        System.out.println();
        //中序遍历
        System.out.println("中序遍历：");
        bitree.inOrder(root);
        System.out.println();
        //后序遍历
        System.out.println("后序遍历：");
        bitree.postOrder(root);
        System.out.println();
        //查找结点
        BiTreeNode findNode1 = bitree.find(10);
        System.out.println("找到结点，其值为：" + ((findNode1 == null) ? " 没找到" : findNode1.getData()));

        //查找结点
        BiTreeNode findNode2 = bitree.find(9);
        System.out.println("找到结点，其值为：" + ((findNode2 == null) ? " 没找到" : findNode2.getData()));
        //删除结点
        System.out.println("获取最小值: " + bitree.getMinValue());
        System.out.println("获取最大值:" + bitree.getMaxValue());
        bitree.delete2(8);
        System.out.print("删除结点8,中序遍历：");
        bitree.preOrder(root);
    }
}
