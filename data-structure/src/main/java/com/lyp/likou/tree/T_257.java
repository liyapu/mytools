package com.lyp.likou.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 17:29
 *
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class T_257 {

    /**
     * 使用 栈
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null){
            viewAllPaths(root,stack,paths);
        }

        return paths;
    }

    private static void viewAllPaths(TreeNode root, Stack<TreeNode> stack, List<String> paths) {
        if(null == root){
            return;
        }
        stack.push(root);
        boolean isLeaf = root.left == null && root.right == null;
        if(isLeaf){
            StringBuilder sb = new StringBuilder();
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                sb.append(stack.get(i).val);
                if(i != size-1){
                    sb.append("->");
                }
            }
            paths.add(sb.toString());
        }
        if(root.left != null){
            viewAllPaths(root.left,stack,paths);
        }
        if(root.right != null){
            viewAllPaths(root.right,stack,paths);
        }
        stack.pop();
    }

    /**
     * 使用 StringBuilder
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if(root != null){
            viewAllPaths2(root,new StringBuilder(),paths);
        }

        return paths;
    }

    private static void viewAllPaths2(TreeNode root, StringBuilder sb, List<String> paths) {
        if(null == root){
            return;
        }
        sb.append(root.val);
        boolean isLeaf = root.left == null && root.right == null;
        if(isLeaf){
            paths.add(sb.toString());
            return;
        }
        sb.append("->");
        viewAllPaths2(root.left,new StringBuilder(sb),paths);
        viewAllPaths2(root.right,new StringBuilder(sb),paths);
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtils.buildTree(1, 2, 3, 4, 5);
        List<String> t1Result = binaryTreePaths2(t1);
        System.out.println(t1Result);
    }



}
