package com.lyp.likou.tree;

import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 17:26
 *
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class T_112 {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        AtomicBoolean ab = new AtomicBoolean();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            calPathSum(root, stack, targetSum, ab);
        }
        return ab.get();
    }

    private static void calPathSum(TreeNode root, Stack<TreeNode> stack, int targetSum, AtomicBoolean ab) {
        if (root == null) {
            return;
        }
        stack.push(root);

        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf) {
            int size = stack.size();
            int tempSum = 0;
            for (int i = 0; i < size; i++) {
                tempSum += stack.get(i).val;
            }
            if (Objects.equals(tempSum, targetSum)) {
                ab.set(true);
                return;
            }
        }
        if(root.left != null && !ab.get()){
            calPathSum(root.left,stack,targetSum,ab);
        }
        if(root.right != null && !ab.get()){
            calPathSum(root.right,stack,targetSum,ab);
        }
        stack.pop();
    }

    /**
     * 首先我们可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
     *
     * 这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if(null == root){
            return false;
        }
        Stack<TreeNode> viewStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        viewStack.push(root);
        sumStack.push(root.val);
        while (!viewStack.isEmpty()){
            TreeNode view = viewStack.pop();
            Integer sum = sumStack.pop();
            if(view.left == null && view.right == null){
                if(sum == targetSum){
                    return true;
                }
                continue;
            }
            if(view.left != null){
                viewStack.push(view.left);
                sumStack.push(sum + view.left.val);
            }
            if(view.right != null){
                viewStack.push(view.right);
                sumStack.push(sum+view.right.val);
            }
        }

        return false;
    }

    /**
     * 方法二：递归
     * 思路及算法
     *
     * 观察要求我们完成的函数，我们可以归纳出它的功能：询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum。
     *
     * 假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
     *
     * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum3(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            if(root.val == targetSum){
                return true;
            }
        }
        return hasPathSum3(root.left,targetSum-root.val) || hasPathSum3(root.right,targetSum-root.val);

    }

        public static void main(String[] args) {
        TreeNode t1 = TreeUtils.buildTree(1,2,3);
        boolean b = hasPathSum3(t1, 3);
        System.out.println(b);
    }
}
