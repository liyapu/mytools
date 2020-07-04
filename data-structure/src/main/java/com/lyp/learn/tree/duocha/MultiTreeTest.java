package com.lyp.learn.tree.duocha;

import java.util.Arrays;
import java.util.List;

public class MultiTreeTest {

    /**
     * 构建多叉树
     * @return
     */
    public static MultiTree create(){
        MultiTreeNode k = new MultiTreeNode("K");
        MultiTreeNode f = new MultiTreeNode("F");
        MultiTreeNode g = new MultiTreeNode("G");
        MultiTreeNode p = new MultiTreeNode("P");
        MultiTreeNode m = new MultiTreeNode("M");
        MultiTreeNode i = new MultiTreeNode("I");
        MultiTreeNode n = new MultiTreeNode("N");
        MultiTreeNode o = new MultiTreeNode("O");

        MultiTreeNode e = new MultiTreeNode("E",Arrays.asList(k));
        MultiTreeNode l = new MultiTreeNode("L",Arrays.asList(p));
        MultiTreeNode h = new MultiTreeNode("H",Arrays.asList(l,m));
        MultiTreeNode j = new MultiTreeNode("J",Arrays.asList(n,o));

        MultiTreeNode b = new MultiTreeNode("B",Arrays.asList(e,f));
        MultiTreeNode c = new MultiTreeNode("C",Arrays.asList(g,h,i));
        MultiTreeNode d = new MultiTreeNode("D",Arrays.asList(j));

        MultiTreeNode a = new MultiTreeNode("A",Arrays.asList(b,c,d));

        return new MultiTree(a);
    }

    private static void printList(List<String> list){
        for(String str : list){
            System.out.println(str);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MultiTree multiTree = create();
        MultiTreeNode root = multiTree.getRoot();

        System.out.print("递归遍历     : ");
        multiTree.visitRecursion(root);
        System.out.println();

        System.out.print("广度优先遍历 : ");
        multiTree.breadthFirstSearch(root);
        System.out.println();

        System.out.print("深度优先遍历 : ");
        multiTree.depthFirstSearch(root);
        System.out.println();
        System.out.println();

        System.out.println("获取每层的结点 ");
        List<List<String>> levelResult = multiTree.levelElement(root);
        for(List<String> ls : levelResult){
            for(String str : ls){
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("获取从根结点到叶子结点的所有路径 : ");
        List<String> pathList = multiTree.listAllPath(root);
        printList(pathList);

        System.out.println();
        System.out.println("获取从根结点到叶子结点的所有路径 : ");
        List<String> pathList2 = multiTree.listAllPath2(root);
        printList(pathList2);


    }
}
