package com.lyp.likou.tree;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-15 17:50
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 * ===============
 * 输入：
 *  *
 *  *      4
 *  *    /   \
 *  *   2     7
 *  *   \   / \
 *  *    3 6   9
 *  * 输出：
 *  *
 *  *      4
 *  *    /   \
 *  *   7     2
 *  *  / \   /
 *  * 9   6 3
 */
public class T_226 {

    /**
     * 思路与算法
     *
     * 这是一道很经典的二叉树问题。显然，我们从根节点开始，递归地对树进行遍历，并从叶子结点先开始翻转。
     * 如果当前遍历到的节点 \textit{root}root 的左右两棵子树都已经翻转，那么我们只需要交换两棵子树的位置，
     * 即可完成以 \textit{root}root 为根节点的整棵子树的翻转。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    public static void main(String[] args) {

    }
}
