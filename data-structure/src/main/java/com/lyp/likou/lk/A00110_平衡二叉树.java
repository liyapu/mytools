package com.lyp.likou.lk;

import com.lyp.likou.lk.po.TreeNode;
import com.lyp.likou.lk.utils.TreeUtils;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是 平衡二叉树
 * (平衡二叉树 是指该树所有节点的左右子树的高度相差不超过 1。)
 *
 * 这道题中的平衡二叉树的定义是：二叉树的每个节点的左右子树的高度差的绝对值不超过 1，则二叉树是平衡二叉树。
 * 根据定义，一棵二叉树是平衡二叉树，当且仅当其所有子树也都是平衡二叉树，
 * 因此可以使用递归的方式判断二叉树是不是平衡二叉树，递归的顺序可以是自顶向下或者自底向上。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 *
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class A00110_平衡二叉树 {

    public static boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        int leftHigh = deepHigh(root.left);
        int rightHigh = deepHigh(root.right);
        return Math.abs(leftHigh - rightHigh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 递归求出单个结点的高度
     *
     * @param node
     * @return
     */
    public static int deepHigh(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(deepHigh(node.left), deepHigh(node.right)) + 1;
    }

    public static void main(String[] args) {
        Integer[] nodeArray1 = {3,9,20,null,null,15,7};
        TreeNode treeNode1 = TreeUtils.buildTree(nodeArray1);
        TreeUtils.printTree(treeNode1);
        System.out.println(isBalanced(treeNode1));

        Integer[] nodeArray2 = {1,2,2,3,3,null,null,4,4};
        TreeNode treeNode2 = TreeUtils.buildTree(nodeArray2);
        TreeUtils.printTree(treeNode2);
        System.out.println(isBalanced(treeNode2));
    }


}
