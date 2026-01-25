package com.lyp.likou.lk;

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
     *
     * 二叉树有三种常见的遍历方式：
     * 前序遍历：根-左-右。先获取根节点值，再访问根的左子树，最后访问根的右子树。
     * 中序遍历：左-根-右。先访问根的左子树，再获取根节点值，最后访问根的右子树。
     * 后序遍历：左-右-根。先访问根的左子树，再访问根的右子树，最后获取根节点值。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/solutions/3844031/tu-jie-morris-bian-li-xian-suo-er-cha-sh-cm2s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     *
     * 二叉树的遍历可以分为两大类：
     *
     * 1.深度优先遍历
     *      所谓深度优先(depth first search，DFS)，顾名思义就是偏向于纵深，“一头扎到底”的访问方式。
     *      深度优先遍历又根据遍历顺序的不同分为三种：前序遍历、中序遍历、后序遍历。
     *
     *      a.前序遍历: 先输出父节点，再遍历左子树和右子树
     *      b.中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
     *      c.后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
     *      小结: 看输出父节点的顺序，就确定是前序，中序还是后序
     *
     * 2.广度优先遍历
     *     广度优先遍历(Breadth First Search，BFS)也叫层序遍历，就是按照二叉树中的层次从左到右依次遍历每层中的结点。
     *     层序遍历的实现思路是利用队列来实现。先将树的根结点入队，然后再让队列中的结点出队。队列中每一个结点出队的时候，都要将该结点的左子结点和右子结点入队。
     *     当队列中的所有结点都出队，树中的所有结点也就遍历完成。此时队列中结点的出队顺序就是层次遍历的最终结果
     *
     *     a.层次遍历：先输出根结点，然后分层次的进行遍历
     *
     */
    /**
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

            //（注意代码中空节点不入栈）
            // 为什么要先加入 右孩子，再加入左孩子呢？ 因为这样出栈的时候才是中左右的顺序。
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

/**
 * 相似题型参考
 *
 * A00144_二叉树的前序遍历
 * A00094_二叉树的中序遍历
 * A00145_二叉树的后序遍历
 *
 */
