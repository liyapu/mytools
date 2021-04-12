package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 14:40
 *
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 */
public class T_111 {

    public static int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        Integer minDepthNum = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepthNum = Math.min(minDepth(root.left), minDepthNum);
        }
        if (root.right != null) {
            minDepthNum = Math.min(minDepthNum, minDepth(root.right));
        }
        return minDepthNum + 1;
    }

    public static void main(String[] args) {
        //构建的树不对，结果肯定不对
        TreeNode t1 = TreeUtils.buildTree(3, 9, 20, null, null, 15, 7);
        System.out.println(minDepth(t1));

        TreeNode t2 = TreeUtils.buildTree(2, null, 3, null, 4, null, 5, null, 6);
        System.out.println(minDepth(t2));

        TreeUtils.printTree(t2);
    }
}
