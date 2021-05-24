package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-24 09:47
 *
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 */
public class T_144 {
    /**
     *      1
     *    2     3
     *  4  5   6  7
     *
     *  stack  7
     *  root 2
     *  打印 1 2 4 5 6
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        while (node != null || stack.size() > 0) {
            while (node != null) {
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7);
        List<Integer> integerList = preorderTraversal(treeNode);
        System.out.println(integerList);
    }


}
