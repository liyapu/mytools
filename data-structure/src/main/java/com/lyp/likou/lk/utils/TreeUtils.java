package com.lyp.likou.lk.utils;


import com.lyp.likou.lk.po.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 树构建辅助类
 */
public class TreeUtils {


    /**
     * 层次遍历：先输出根结点，然后分层次的进行遍历。
     * 层次遍历数组，构建树
     *
     * @param arrs
     * @return
     */
    public static TreeNode buildTree(Integer... arrs) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < arrs.length; i++) {
            Integer num = arrs[i];
            if (num == null) {
                map.put(i, null);
            } else {
                map.put(i, new TreeNode(num));
            }
        }

        for (int i = 0; i < arrs.length; i++) {
            TreeNode root = map.get(i);
            TreeNode left = map.get(2 * i + 1);
            TreeNode right = map.get(2 * i + 2);
            if (root != null) {
                root.left = left;
                root.right = right;
            }
        }
        return map.get(0);
    }


    /**
     * 打印输出树形结构
     *
     * 按照树形结构直观地打印出一棵二叉树(Java)
     * https://www.cnblogs.com/liulaolaiu/p/11744409.html
     *
     * @param root
     */
    public static void printTree(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
            System.out.println();
        }
    }

    // 用于获得树的层数
    private static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static void main(String[] args) {
        Integer[] nodeArray1 = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode1 = buildTree(nodeArray1);
        printTree(treeNode1);

        Integer[] nodeArray2 = {1, 2, 2, 3, 3, null, null, 4, 4};
        TreeNode treeNode2 = buildTree(nodeArray2);
        printTree(treeNode2);

    }
}
