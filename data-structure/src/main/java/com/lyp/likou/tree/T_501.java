package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-16 11:58
 *
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 */
public class T_501 {

    /**
     * 首先，我们考虑在寻找出现次数最多的数时，不使用哈希表。 这个优化是基于二叉搜索树中序遍历的性质：一棵二叉搜索树的中序遍历序列是一个非递减的有序序列。例如：
     *
     *
     *       1
     *     /   \
     *    0     2
     *   / \    /
     * -1   0  2
     * 这样一颗二叉搜索树的中序遍历序列是 {−1,0,0,1,2,2}。我们可以发现重复出现的数字一定是一个连续出现的，
     * 例如这里的 0 和 2，它们都重复出现了，并且所有的 0 都集中在一个连续的段内，所有的 2 也集中在一个连续的段内。
     * 我们可以顺序扫描中序遍历序列，
     *      用 base 记录当前的数字，
     *      用 count 记录当前数字重复的次数，
     *      用 maxCount 来维护已经扫描过的数当中出现最多的那个数字的出现次数，
     *      用 answer 数组记录出现的众数。每次扫描到一个新的元素：
     *
     * 首先更新 base 和 count:
     *       如果该元素和 base 相等，那么 count 自增 1；
     *       否则将  base 更新为当前数字，count 复位为 1。
     * 然后更新 maxCount：
     *      如果 count=maxCount，那么说明当前的这个数字（base）出现的次数等于当前众数出现的次数，将 base 加入 answer 数组；
     *      如果 count>maxCount，那么说明当前的这个数字（base）出现的次数大于当前众数出现的次数，因此，我们需要将 maxCount 更新为count，清空 answer 数组后将base 加入 answer 数组。
     * 我们可以把这个过程写成一个 update 函数。这样我们在寻找出现次数最多的数字的时候就可以省去一个哈希表带来的空间消耗。
     *
     * 然后，我们考虑不存储这个中序遍历序列。 如果我们在递归进行中序遍历的过程中，访问当了某个点的时候直接使用上面的update 函数，就可以省去中序遍历序列的空间，代码如下
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-zhong-shu-by-leetcode-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    List<Integer> answerList = new ArrayList<>();
    int base,count,maxCount;

    public int[] findMode(TreeNode root) {
        inOrder(root);
        int [] answerArr  = new int [answerList.size()];
        for (int i = 0; i < answerArr.length; i++) {
            answerArr[i] = answerList.get(i);
        }
        return answerArr;
    }

    private void inOrder(TreeNode root) {
        if(root != null){
            inOrder(root.left);
            update(root.val);
            inOrder(root.right);
        }
    }

    private void update(Integer val) {
        if(base == val){
            count++;
        }else{
            base = val;
            count = 1;
        }

        if(count == maxCount){
            answerList.add(val);
        }else if(count > maxCount){
            answerList.clear();
            answerList.add(base);
            maxCount = count;
        }
    }

    public static void main(String[] args) {
    }

}
