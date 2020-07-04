package com.lyp.learn.tree.ercha;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BiTreeTest {

    public static BiTree create(){
        BiTreeNode d = new BiTreeNode("D");
        BiTreeNode g = new BiTreeNode("G");
        BiTreeNode h = new BiTreeNode("H");
        BiTreeNode e = new BiTreeNode("E",g,null);
        BiTreeNode f = new BiTreeNode("F",null,h);
        BiTreeNode b = new BiTreeNode("B",d,e);
        BiTreeNode c = new BiTreeNode("C",f,null);
        BiTreeNode a = new BiTreeNode("A",b,c);
        return new BiTree(a);   //创建根结点为a的二叉树
    }

    public static BiTree create2(){
        BiTreeNode d = new BiTreeNode("D");
        BiTreeNode g = new BiTreeNode("G");
        //BiTreeNode h = new BiTreeNode("H");
        //BiTreeNode i = new BiTreeNode("I");
        BiTreeNode e = new BiTreeNode("E",g,null);
        //BiTreeNode f = new BiTreeNode("F",null,h);
        BiTreeNode b = new BiTreeNode("B",d,e);
        //BiTreeNode c = new BiTreeNode("C",f,i);
        BiTreeNode c = new BiTreeNode("C",null,null);
        BiTreeNode a = new BiTreeNode("A",b,c);
        return new BiTree(a);   //创建根结点为a的二叉树
    }

    public static void printList(List<String> list){
        for(String str : list){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        BiTree biTree = create();
        BiTreeNode root = biTree.getRoot();  //获取树的根结点

        System.out.print("先根遍历(递归)   ");
        biTree.preRootTraverse(root);
        System.out.println();
        System.out.print("先根遍历(非递归) ");
        biTree.preRootTraverse();
        System.out.println();
        System.out.print("先根遍历(非递归) ");
        biTree.preOrder();
        System.out.println();
        System.out.print("先根遍历(非递归) ");
        biTree.preOrder2();

        System.out.println();
        System.out.println();

        System.out.print("中根遍历(递归)   ");
        biTree.inRootTraverse(root);
        System.out.println();
        System.out.print("中根遍历(非递归) ");
        biTree.inOrder();
        System.out.println();
        System.out.print("中根遍历(非递归) ");
        biTree.inOrder2();
        System.out.println();
        System.out.print("中根遍历(非递归) ");
        biTree.inRootTraverse();
        System.out.println();


        System.out.println();
        System.out.println();

        System.out.print("后根遍历(递归)   ");
        biTree.postRootTraverse(root);
        System.out.println();
        System.out.print("后根遍历(非递归) ");
        biTree.postRootTraverse();
        System.out.println();
        System.out.print("后根遍历(非递归) ");
        biTree.postRootTraverse2();
        System.out.println();
        System.out.print("后根遍历(非递归) ");
        biTree.postOrder();
        System.out.println();
        System.out.print("后根遍历(非递归) ");
        biTree.postOrder2();

        System.out.println();
        System.out.println();
        System.out.print("层次遍历(递归)   ");
        biTree.levelOrder(Arrays.asList(root));
        System.out.println();
        System.out.print("层次遍历(非递归) ");
        biTree.levelTraverse();
        System.out.println();
        System.out.print("层次遍历(非递归) ");
        biTree.levelOrder();

        System.out.println();
        System.out.println();
        System.out.println("获取节点总个数(递归)   : " + biTree.getNodeCount(root));
        System.out.println("获取节点总个数(递归)   : " + biTree.getNodeCountPreRoot(root));
        System.out.println("获取节点总个数(递归)   : " + biTree.getNodeCount(root,0));
        System.out.println("获取节点总个数(非递归) : " + biTree.getNodeCount());
        System.out.println("获取节点总个数(非递归) : " + biTree.getNodeCount2());
        System.out.println("获取节点总个数(非递归) : " + biTree.getNodeCount3());

        System.out.println();
        System.out.println("获取叶子节点总个数(递归)  : " + biTree.getLeafNodeCount(root));
        System.out.println("获取叶子节点总个数(递归)  : " + biTree.getLeafNodeCount1(root));
        System.out.println("获取叶子节点总个数(非递归): " + biTree.getLeafNodeCount());
        System.out.println("获取叶子节点总个数(非递归): " + biTree.getLeafNodeCount2());

        System.out.println();
        System.out.println("获取第level层的节点个数(递归)   : " + biTree.getLevelNodeCount(root,4));
        System.out.println("获取第level层的节点个数(非递归) : " + biTree.getLevelNodeCount(4));

        System.out.println();
        System.out.println("获取树的深度(递归)  : " + biTree.getDepth(root));
        System.out.println("获取树的深度(非递归): " + biTree.getDepth());

        System.out.println();
        System.out.println("查找值为 F 的结点 :" + biTree.searchNode(root,"F"));
        System.out.println("查找值为 M 的结点 :" + biTree.searchNode(root,"M"));

        System.out.println();
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual(root,root));
        BiTree biTree2 = create2();
        BiTreeNode root2 = biTree2.getRoot();
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual(root,root2));
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual2(root,root));
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual2(root,root2));
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual3(root,root));
        System.out.println("判断两棵树是否相等 : " + biTree.isEqual3(root,root2));

        System.out.println("--------------------------");
        System.out.println("获取从根结点到叶子结点的所有路径");
        List<String> pathList = biTree.listAllPath(root);
        printList(pathList);

        System.out.println();
        System.out.println("计算二叉树根节点到叶子节点的最短路径" );
        Stack<BiTreeNode> minPathStack = biTree2.findMinPath(root2);
        System.out.println("最短路径长度是 : " + minPathStack.size());
        for(int i = 0 ; i < minPathStack.size(); i++){
            System.out.print(minPathStack.get(i).getData());
        }

        System.out.println();
        System.out.println();
        System.out.println("获取每层的结点");
        List<List<String>> levelList = biTree.levelElement(root);
        for(List<String> ls : levelList){
            for(String str : ls){
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("-----由先根，中根遍历去构建树----");
        String preOrder = "ABDEGCFH"; //先根遍历序列
        String inOrder = "DBGEAFHC"; //中根遍历序列

        BiTree preInOrderTree = new BiTree(preOrder,inOrder,0,0,preOrder.length());
        System.out.print("后根遍历序列 : " );
        preInOrderTree.postOrder();
        System.out.println();

        System.out.println();
        System.out.println("-----由标明空子树的先根构建树");
        String preOrderNotice = "AB##CD###";
        BiTree preOrderNoticeTree = new BiTree(preOrderNotice);
        System.out.print("先根遍历 : " );
        preOrderNoticeTree.preOrder();
        System.out.println();
        System.out.print("中根遍历 : ");
        preOrderNoticeTree.inOrder();
        System.out.println();
        System.out.print("后根遍历 : ");
        preOrderNoticeTree.postOrder();
        System.out.println();

        System.out.println("----由完全二叉树的顺序存储构建二叉树--------");
        BiTreeNode rootNode = biTree.createBiTree("ABCDEFGH",0);
        BiTree rootNodeBiTree = new BiTree(rootNode);
        System.out.print("先根遍历 : " );
        rootNodeBiTree.preOrder();
        System.out.println();
        System.out.print("中根遍历 : ");
        rootNodeBiTree.inOrder();
        System.out.println();
        System.out.print("后根遍历 : ");
        rootNodeBiTree.postOrder();
        System.out.println();
    }


























}
