package com.lyp.learn.tree.duocha;

import java.util.*;

/**
 * 多叉树
 */
public class MultiTree {

    //根结点
    private MultiTreeNode root;

    //构造一课空树
    public MultiTree(){

    }

    //根据根结点构造树
    public MultiTree(MultiTreeNode root){
        this.root = root;
    }

    public MultiTreeNode getRoot() {
        return root;
    }

    public void setRoot(MultiTreeNode root) {
        this.root = root;
    }


    /**
     *  遍历结点(递归)
     * @param node
     */
    public static void visitRecursion(MultiTreeNode node) {
        System.out.print(node.getData() + " ");
        List<MultiTreeNode> childs = node.getChilds();
        if(childs != null){
            for (MultiTreeNode mtn : node.getChilds()) {
                visitRecursion(mtn);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param node
     */
    public static void breadthFirstSearch(MultiTreeNode node){
        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            node = queue.poll();
            System.out.print(node.getData() + " ");
            List<MultiTreeNode> childs = node.getChilds();
            if(childs != null){
                for(MultiTreeNode mtn : childs){
                    queue.offer(mtn);
                }
            }
        }
    }

    /**
     * 深度优先遍历
     * @param node
     */
    public static void depthFirstSearch(MultiTreeNode node){
        if(node == null){
            return;
        }
        Stack<MultiTreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node =stack.pop();
            System.out.print(node.getData() + " ");
            List<MultiTreeNode> childs = node.getChilds();
            if(childs != null){
                //孩子结点倒序入栈，正序访问
                for(int i = childs.size() - 1 ; i >= 0; i--){
                    stack.push(childs.get(i));
                }
            }
        }
    }

    /**
     * 获取每层的结点
     * @param node
     * @return
     */
    public List<List<String>> levelElement(MultiTreeNode node){
        List<List<String>>  levelList = new LinkedList<>();
        if(node == null){
            return levelList;
        }

        Queue<MultiTreeNode> queue = new LinkedList<>();
        //根结点进入队列
        queue.offer(node);
        MultiTreeNode tempNode;

        //若队列非空，循环执行
        while(!queue.isEmpty()){
            //获取当前层的节点数.
            int size = queue.size();
            List<String> tempList = new LinkedList<>();
            //遍历栈中同一级别的结点
            for(int i = 0; i < size; i++){
                //队首出队并将值加入子list
                tempNode = queue.poll();
                tempList.add(tempNode.getData().toString());

                //孩子结点入栈，组成下一层结点栈
                List<MultiTreeNode> childs = tempNode.getChilds();
                if(childs != null){
                    for(MultiTreeNode mtn : childs){
                        queue.offer(mtn);
                    }
                }
            }
            // 添加一层
            levelList.add(tempList);
        }
        return levelList;
    }

    /**
     * 获取 从根结点到叶子结点的所有路径
     * @param root
     * @return
     */
    public List<String> listAllPath(MultiTreeNode root){
        List<String> pathList = new LinkedList<>();
        if(root != null){
            List<Object> stack = new LinkedList<>();
            listAllPathRecursion(stack,root,pathList);
        }
        return pathList;
    }

    /**
     * 该算法使用递归方式实现，采用深度优先遍历树的节点，同时记录下已经遍历的节点保存在栈中。
     * 当遇到叶子节点时，输出此时栈中的所有元素，即为当前的一条路径；
     * 然后pop出当前叶子节点
     * @param stack 为深度优先遍历过程中存储节点的栈
     * @param root 为树的要节点或子树的根节点
     * @param pathList 为树中所有从根到叶子节点的路径的列表
     */
    private void listAllPathRecursion(List<Object> stack, MultiTreeNode root, List<String> pathList) {
        if (root != null) {
            stack.add(root.getData());
            List<MultiTreeNode> childs = root.getChilds();
            if (childs == null) {
                changeToPath(stack, pathList); // 把值栈中的值转化为路径
            } else {
                for (int i = 0; i < childs.size(); i++) {
                    listAllPathRecursion(stack, childs.get(i), pathList);
                }
            }
            stack.remove(stack.size() - 1);
        }
    }

    /**
     * @param path
     * @param pathList
     */
    private void changeToPath(List<Object> path, List<String> pathList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) != null) {
                sb.append(path.get(i) + " ");
            }
        }
        pathList.add(sb.toString().trim());
    }


    /**
     * 获取 从根结点到叶子结点的所有路径
     * @param root
     * @return
     */
    public List<String> listAllPath2(MultiTreeNode root){
        List<String> pathList = new LinkedList<>();
        if(root != null){
            Stack<Object> stack = new Stack<>();
            listAllPathRecursion2(root,stack,pathList);
        }
        return pathList;
    }


    /**
     * 该算法使用递归方式实现，采用深度优先遍历树的节点，同时记录下已经遍历的节点保存在栈中。
     * 当遇到叶子节点时，输出此时栈中的所有元素，即为当前的一条路径；
     * 然后pop出当前叶子节点
     * @param root 为树的要节点或子树的根节点
     * @param stack 为深度优先遍历过程中存储节点的栈
     * @param pathList 为树中所有从根到叶子节点的路径的列表
     */
    private void listAllPathRecursion2(MultiTreeNode root, Stack<Object> stack, List<String> pathList) {
        if(root == null){
            return;
        }
        stack.push(root.getData());
        List<MultiTreeNode> childs = root.getChilds();
        if(childs == null){
            //孩子结点为空，说明是叶子结点，构造输出路径
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < stack.size(); i++){
                sb.append(stack.get(i)+" ");
            }
            pathList.add(sb.toString());
        }else{
            //递归遍历孩子结点
            for(int i =0 ; i < childs.size(); i++){
                listAllPathRecursion2(childs.get(i),stack,pathList);
            }
        }
        //执行到此，栈顶是叶子结点，出栈叶子结点
        stack.pop();
    }


    /**
     * 获取 从根结点到叶子结点的所有路径
     * (非递归)
     */
    public List<String> listAllPath3(MultiTreeNode root){
        List<String> pathList = new LinkedList<>();
        if(root == null){
            return pathList;
        }
        Stack<MultiTreeNode> stack = new Stack<>();
        MultiTreeNode node = root;
        stack.push(node);

        while(!stack.isEmpty()){
            node = stack.peek();
            List<MultiTreeNode> childs = node.getChilds();
            if(childs != null){
                for(int i = 0 ; i < childs.size(); i++){

                }
            }else{

            }
        }

        return pathList;
    }


}
