package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-19 15:42
 *
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class T_145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursionView(root, result);
        return result;
    }

    void recursionView(TreeNode root, List<Integer> result) {
        if (null == root) {
            return;
        }
        recursionView(root.left, result);
        recursionView(root.right, result);
        result.add(root.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            outStack.push(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }

        }

        while (outStack.size() > 0) {
            result.add(outStack.pop());
        }
        return result;
    }

}
