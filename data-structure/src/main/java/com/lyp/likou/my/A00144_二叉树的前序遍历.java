package com.lyp.likou.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 解释：
 *
 *
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * 输出：[1,2,4,5,6,7,3,8,9]
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
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
public class A00144_二叉树的前序遍历 {


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
     * 参考
     * A00144_二叉树的前序遍历
     * A00094_二叉树的中序遍历
     * A00145_二叉树的后序遍历
     * 递归
     * 史上最全遍历二叉树详解
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/solutions/87526/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
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
        resultList.add(node.val);//根 （这行代码移到前面下面是中序，移到最后就是后序）
        dfs(resultList, node.left);//左节点
        dfs(resultList, node.right);//右节点
    }

    /**
     * 迭代解法
     * 本质上是在模拟递归，因为在递归的过程中使用了系统栈，所以在迭代的解法中常用 Stack 来模拟系统栈。
     *
     * 前序遍历
     * 首先我们应该创建一个 Stack 用来存放节点，首先我们想要打印根节点的数据，此时 Stack 里面的内容为空，所以我们优先将头结点加入 Stack，然后打印。
     *
     * 之后我们应该先打印左子树，然后右子树。所以先加入 Stack 的就是右子树，然后左子树。
     * 此时你能得到的流程如下:
     *
     * 作者：golandscape
     * 链接：https://leetcode.cn/problems/binary-tree-preorder-traversal/solutions/87526/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //后边操作，都是操作 弹出的 node 结点
            TreeNode node = stack.pop();
            resultList.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return resultList;
    }

}
