package com.lyp.likou.tree;

import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-15 15:25
 *
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class T_404 {

    /**
     * 一个节点为「左叶子」节点，当且仅当它是某个节点的左子节点，并且它是一个叶子结点
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int sum = 0;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.val + " ");
                stack.push(node);
                node = node.left;
                if (node != null && (node.left == null && node.right == null)) {
                    sum += node.val;
                }
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
        return sum;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves2(TreeNode root) {
        if(root == null) return 0;
        int sum = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        return sum + sumOfLeftLeaves2(root.left) + sumOfLeftLeaves2(root.right);
    }

        public static void main(String[] args) {
        TreeNode t1 = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7);
        int t1Sum = sumOfLeftLeaves(t1);
        System.out.println(t1Sum);
    }
}
