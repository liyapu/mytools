package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-12 17:35
 *
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class T_235 {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<List<TreeNode>> pathLists = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        viewFindPaths(root,p,q,stack,pathLists);

        List<TreeNode> list1 = pathLists.get(0);
        List<TreeNode> list2 = pathLists.get(1);
        for (int i = list1.size()-1; i >= 0 ; i--) {
            for (int j = 0; j < list2.size(); j++) {
                if(Objects.equals(list1.get(i),list2.get(j))){
                    return list1.get(i);
                }
            }
        }
        return null;
    }

    private static void viewFindPaths(TreeNode root, TreeNode p, TreeNode q, Stack<TreeNode> stack, List<List<TreeNode>> pathLists) {
        if(root == null){
            return;
        }
        stack.push(root);

        if(Objects.equals(root,p) || Objects.equals(root,q)){
            int size = stack.size();
            List<TreeNode> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                tempList.add(stack.get(i));
            }
            pathLists.add(tempList);
            if(pathLists.size() == 2){
                return;
            }
        }
        if(root.left != null && pathLists.size() != 2){
            viewFindPaths(root.left,p,q,stack,pathLists);
        }
        if(root.right != null && pathLists.size() != 2){
            viewFindPaths(root.right,p,q,stack,pathLists);
        }
        stack.pop();
    }

    /**
     * 注意到题目中给出的是一棵「二叉搜索树」，因此我们可以快速地找出树中的某个节点以及从根节点到该节点的路径，例如我们需要找到节点 p：
     *
     * 我们从根节点开始遍历；
     *    如果当前节点就是 p，那么成功地找到了节点；
     *    如果当前节点的值大于 p 的值，说明 p 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *    如果当前节点的值小于 p 的值，说明 p 应该在当前节点的右子树，因此将当前节点移动到它的右子节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-26/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = getPaths(root,p);
        List<TreeNode> qList = getPaths(root,q);
        TreeNode ancestor = null;
        for (int i = 0; i < pList.size() && i < qList.size(); i++) {
            if(pList.get(i) == qList.get(i)){
                ancestor = pList.get(i);
            }else{
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPaths(TreeNode root, TreeNode p) {
        List<TreeNode> paths = new ArrayList<>();
        TreeNode node = root;
        while (node != p){
            paths.add(node);
            if(node.val > p.val){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        paths.add(node);
        return paths;
    }

    /**
     *在方法一中，我们对从根节点开始，通过遍历找出到达节点 pp 和 qq 的路径，一共需要两次遍历。我们也可以考虑将这两个节点放在一起遍历。
     *
     * 整体的遍历过程与方法一中的类似：
     *
     * 我们从根节点开始遍历；
     *
     * 如果当前节点的值大于 pp 和 qq 的值，说明 pp 和 qq 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *
     * 如果当前节点的值小于 pp 和 qq 的值，说明 pp 和 qq 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
     *
     * 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，pp 和 qq 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
     *
     * 可以发现，如果我们将这两个节点放在一起遍历，我们就省去了存储路径需要的空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-26/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        TreeNode ancestor = node;

        while (true){
            if(node.val > p.val && node.val > q.val){
                node = node.left;
            }else if(node.val < p.val && node.val < q.val){
                node = node.right;
            }else{
                ancestor  = node;
                break;
            }
        }
        return ancestor;
    }

    public static void main(String[] args) {

    }
}
