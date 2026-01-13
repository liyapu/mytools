package com.lyp.likou.my;

import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 * 解释：
 *
 *
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * 输出：[4,6,7,5,2,9,8,3,1]
 * 解释：
 *
 *
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 4：
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 *
 * 提示：
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class A00145_二叉树的后序遍历 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        dfs(resultList,root);
        return resultList;
    }

    private static void dfs(List<Integer> resultList, TreeNode node) {
        if(node == null){
            return;
        }
        dfs(resultList,node.left);
        dfs(resultList,node.right);
        resultList.add(node.val);
    }
}
