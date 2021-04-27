package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 14:14
 *
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class T_102 {

    /**
     * 重复建了很多对象
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            Queue<TreeNode> newQueue = new LinkedList<>();
            List<Integer> path = new ArrayList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                path.add(node.val);
                if (node.left != null) {
                    newQueue.add(node.left);
                }
                if (node.right != null) {
                    newQueue.add(node.right);
                }
            }
            result.add(path);
            queue = newQueue;
        }
        return result;
    }

    /**
     * 使用一个队列
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Integer> levelPath;
        int currentLen;

        while (!queue.isEmpty()) {
            levelPath = new ArrayList<>();
            currentLen = queue.size();
            for (int i = 0; i < currentLen; i++) {
                TreeNode node = queue.poll();
                levelPath.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(levelPath);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8, 9);
        levelOrder(treeNode).stream().forEach(s -> System.out.println(s));
        System.out.println("---------------");
        levelOrder2(treeNode).stream().forEach(s -> System.out.println(s));
    }
}
