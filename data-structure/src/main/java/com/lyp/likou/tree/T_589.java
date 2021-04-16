package com.lyp.likou.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-15 18:24
 * 589. N 叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 *
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *
 *
 * 进阶：
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 *
 * 提示：
 *
 * N 叉树的高度小于或等于 1000
 * 节点总数在范围 [0, 10^4] 内
 */
public class T_589 {

    /**
     * 递归写法
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
            preOrderRecursion(result,root);

        return result;
    }

    private void preOrderRecursion(List<Integer> result, Node root) {
        if(null != root){
            result.add(root.val);
            List<Node>  childrenList = root.children;
            if(childrenList != null && childrenList.size() > 0){
                for (int i = 0; i < childrenList.size(); i++) {
                    preOrderRecursion(result,childrenList.get(i));
                }
            }
        }
    }

    public List<Integer> preorder2(Node root) {
       List<Integer> result = new ArrayList<>();
       if(root == null) return result;
       //使用的是队列
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
       while (!stack.isEmpty()){
           //当前栈顶节点出栈
           Node pop = stack.pop();
           //将值加入列表
           result.add(pop.val);
           List<Node> nodes = pop.children;
           if(nodes != null && nodes.size() > 0){
               //将当前节点的孩子们从右到左入栈
               for (int i = nodes.size() - 1; i >=0 ; i--) {
                   stack.push(nodes.get(i));
               }
           }
       }
       return result;
    }

        public static void main(String[] args) {

    }
}
