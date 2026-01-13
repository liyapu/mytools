package com.lyp.likou.lk;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class A00094_二叉树的中序遍历 {

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
     * 什么是中序遍历？
     * 方法一：递归
     * 思路与算法
     * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，
     * 而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
     * 因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
     *
     * 定义 inorder(root) 表示当前遍历到 root 节点的答案，那么按照定义，
     * 我们只要递归调用 inorder(root.left) 来遍历 root 节点的左子树，
     * 然后将 root 节点的值加入答案，
     * 再递归调用inorder(root.right) 来遍历 root 节点的右子树即可，
     * 递归终止的条件为碰到空节点。
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/solutions/412886/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(result, root);
        return result;
    }

    private void dfs(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(result, node.left); // 左
        result.add(node.val); // 根（这行代码移到前面就是前序，移到后面就是后序）
        dfs(result, node.right); // 右
    }

    /**
     * 迭代，
     * 模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){

            //内部while循环结束时，root 为null
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); //左
            resultList.add(root.val);
            root = root.right;
        }
        return  resultList;
    }
}

