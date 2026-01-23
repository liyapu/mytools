package com.lyp.likou.lk;

import com.lyp.likou.lk.po.TreeNode;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 */
public class A00101_对称二叉树 {

    public boolean isSymmetric(TreeNode root) {
        return isSamePair(root.left,root.right);
    }

    private boolean isSamePair(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 != null && node2 != null){
            return node1.val == node2.val && isSamePair(node1.left,node2.right) && isSamePair(node1.right,node2.left);
        }
        return false;
    }


}
