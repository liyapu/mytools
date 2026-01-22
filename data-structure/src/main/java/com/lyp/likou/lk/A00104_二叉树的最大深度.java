package com.lyp.likou.lk;

import com.lyp.likou.lk.po.TreeNode;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：root = [1,null,2]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 */
public class A00104_二叉树的最大深度 {

    /**
     * 递归，新建方法进行递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        int d = deep(root);
        return d;
    }

    public static int deep(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(deep(root.right) + 1,deep(root.left) + 1);
    }

    /**
     * 递归，直接使用自身方法
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth2(root.left),maxDepth2(root.right)) + 1;
    }

    }
