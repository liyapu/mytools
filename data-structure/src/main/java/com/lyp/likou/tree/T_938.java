package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-27 09:52
 *
 * 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 *
 *
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 *
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *
 *
 * 提示：
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 */
public class T_938 {

    /**
     * 暴力遍历树节点，然后根据范围求和
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if(root == null) return sum;

        if(root.val >= low && root.val <= high){
            sum+= root.val;
        }
        return sum + rangeSumBST(root.left,low,high) + rangeSumBST(root.right,low,high);
    }

    /**
     * 考虑 搜索树的性质
     *     左子树的节点值 都小于 根节点
     *     右子树的节点值 都大于 根节点
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static int rangeSumBST2(TreeNode root, int low, int high) {
        int sum = 0;
        if (root == null) return sum;

        int value = root.val;

        if (value >= low && value <= high) {
            sum += value + rangeSumBST2(root.left, low, high) + rangeSumBST2(root.right, low, high);
        } else if (value > high) {
            sum += rangeSumBST2(root.left, low, high);
        } else if(value < low) {
            sum += rangeSumBST2(root.right, low, high);
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeUtils.buildTree(10, 5, 15, 3, 7, 13, 18, 1, 6);
        System.out.println(rangeSumBST2(treeNode, 6, 10));
    }
}
