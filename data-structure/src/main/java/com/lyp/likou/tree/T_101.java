package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 13:24
 *
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class T_101 {

    public static boolean isSymmetric(TreeNode root) {
        if(null == root) return true;
        TreeNode left = root.left;
        TreeNode right =root.right;
//        return isMirrorTree(root,root);
        return isMirrorTree(left,right);
    }

    public  static Boolean isMirrorTree(TreeNode t1,TreeNode t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        return t1.val == t2.val && isMirrorTree(t1.left,t2.right) && isMirrorTree(t1.right,t2.left);
    }

    public static void main(String[] args) {
        TreeNode r1 = TreeUtils.buildTree(1, 2, 2, 3, 4, 4, 3);
        System.out.println(isSymmetric(r1));

        TreeNode r2 = TreeUtils.buildTree(2,3,3,4,5,5,4,null,null,8,9,null,null,9,8);
        System.out.println(isSymmetric(r2));
    }



}
