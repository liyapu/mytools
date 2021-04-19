package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-19 09:40
 *
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
 */
public class S_28 {
    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursion(root,root);
    }

    public static  boolean isSymmetricRecursion(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        return (root1.val == root2.val) && isSymmetricRecursion(root1.left,root2.right) && isSymmetricRecursion(root1.right,root2.left);
    }

    public static void main(String[] args) {
        TreeNode r1 = TreeUtils.buildTree(1,2,3,4,5,6);
        System.out.println(isSymmetric(r1));

        TreeNode r2 = TreeUtils.buildTree(1,2,2,3,4,4,3);
        System.out.println(isSymmetric(r2));
    }
}
