package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 14:16
 *
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3
 */
public class T_104 {

    public static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtils.buildTree(3, 9, 20, null, null, 15, 7);
        System.out.println(maxDepth(t1));
    }
}
