package com.lyp.likou.lk;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

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
        if (root == null) {
            return resultList;
        }
        dfs(resultList, root);
        return resultList;
    }

    private static void dfs(List<Integer> resultList, TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(resultList, node.left);
        dfs(resultList, node.right);
        resultList.add(node.val);
    }


    /**
     * 方法二：迭代
     * 思路与算法
     *
     * 我们也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同，具体可以参考下面的代码。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/solutions/431066/er-cha-shu-de-hou-xu-bian-li-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                resultList.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
       return resultList;
    }
}
