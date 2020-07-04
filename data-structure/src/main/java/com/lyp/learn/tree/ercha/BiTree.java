package com.lyp.learn.tree.ercha;

import java.util.*;

public class BiTree {
    //树的根结点
    private BiTreeNode root;

    //构造一课空树
    public BiTree(){
        this.root = null;
    }

    //构造一棵树
    public BiTree(BiTreeNode root){
        this.root = root;
    }

    /**
     * 由先根遍历和中根遍历次序创建一课二叉树的算法
     * @param preOrder 是正棵树的先根遍历序列
     * @param inOrder  是正棵树的中根遍历序列
     * @param preIndex 是先根遍历序列在 preOrder中的开始位置
     * @param inIndex  是中根遍历序列在 inOrder中的开始位置
     * @param count    表示树中结点的个数
     */
    public BiTree(String preOrder,String inOrder,int preIndex,int inIndex,int count){
        if(count > 0){
            char r = preOrder.charAt(preIndex);
            int i = 0;
            for(; i < count; i++){
                if(r == inOrder.charAt(inIndex + i)){
                    break;
                }
            }
            root = new BiTreeNode(r);
            root.setLchild(new BiTree(preOrder,inOrder,preIndex + 1,inIndex,i).root);
            root.setRchild(new BiTree(preOrder,inOrder,preIndex + i + 1,inIndex + i + 1,count - i - 1).root);
        }
    }

    /**
     * 由标明空子树的先根遍历序列创建一课二叉树
     * 用于记录preStr的索引值
     */
    private static int index = 0;
    public BiTree(String preStr){
        //取出字符串索引为index的字符，且index增1
        char c = preStr.charAt(index++);
        if(c != '#'){
            root = new BiTreeNode(c);
            root.setLchild(new BiTree(preStr).root); //建立树的左子树
            root.setRchild(new BiTree(preStr).root); //建立树的右子树
        }else{
            root = null;
        }
    }

    public BiTreeNode createBiTree(String seqStr,int index){
        BiTreeNode r = null;
        if(index < seqStr.length()){
            r = new BiTreeNode(seqStr.charAt(index));
            r.setLchild(createBiTree(seqStr,2*index + 1));
            r.setRchild(createBiTree(seqStr,2*index + 2));
        }
        return r;
    }

    /**
     * 先根遍历二叉树(递归算法)
     */
    public void preRootTraverse(BiTreeNode t){
        if(t != null){
            System.out.print(t.getData()); //访问根结点
            preRootTraverse(t.getLchild());//先根遍历左子树
            preRootTraverse(t.getRchild());//先根遍历右子树
        }
    }

    /**
     * 先根遍历二叉树 (非递归)
     */
    public void preRootTraverse(){
        BiTreeNode t = root;
        if(t != null){
            Stack stack = new Stack();   //构造栈
            stack.push(t);              //根结点入栈
            while(!stack.isEmpty()){
                t = (BiTreeNode) stack.pop();   //移除栈顶元素
                System.out.print(t.getData());  //输出根结点，或者 右孩子结点
                while(t != null){
                    if(t.getLchild() != null){    //访问左孩子结点结点
                        System.out.print(t.getLchild().getData()); //输出左孩子结点
                    }
                    if(t.getRchild() != null){
                        stack.push(t.getRchild());   //右孩子结点不为空，则入栈
                    }
                    t = t.getLchild();    //左孩子结点层层递进
                }
            }
        }
    }

    /**
     * 前序遍历(非递归)
     * 先放 右孩子getRchild
     * 再放 左孩子getLchild
     */
    public void preOrder(){
        if(root == null){
            return;
        }
        Stack<BiTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BiTreeNode node = stack.pop();
            System.out.print(node.getData());
            if(node.getRchild() != null){
                stack.push(node.getRchild());
            }
            if(node.getLchild() != null){
                stack.push(node.getLchild());
            }
        }
    }

    /**
     * 先序遍历(非递归)
     */
    public  void preOrder2() {
        Stack<BiTreeNode> stack = new Stack<>();
        BiTreeNode node = root;
        while (node != null || stack.size() > 0) {
            while (node != null) {//压入所有的左节点，压入前访问它
                System.out.print(node.getData());
                stack.push(node);
                node = node.getLchild();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getRchild();
            }
        }
    }

    /**
     * 中根遍历二叉树的递归算法
     */
    public void inRootTraverse(BiTreeNode t){
        if(t != null){
            inRootTraverse(t.getLchild()); //中根遍历左子树
            System.out.print(t.getData());//访问根结点
            inRootTraverse(t.getRchild());//中根遍历右子树
        }

    }

    /**
     * 中序遍历(非递归)
     */
    public void inOrder(){
        Stack<BiTreeNode> stack = new Stack<>();
        BiTreeNode node = root;
        while(node != null || stack.size() > 0){
            // 把当前节点的所有左侧子结点压入栈
            while(node != null){
                stack.push(node);
                node = node.getLchild();
            }
            // 访问节点，处理该节点的右子树
            if(stack.size() > 0){
                node = stack.pop();
                System.out.print(node.getData());
                node = node.getRchild();
            }
        }
    }

    /**
     * 中序遍历(非递归)
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内，
     * 然后输出栈顶元素，再处理栈顶元素的右子树
     */
    public void inOrder2(){
        if(root == null){
            return;
        }
        Stack<BiTreeNode> stack = new Stack<>();
        BiTreeNode node = root;
        while (true){
            while (node != null){
                stack.push(node);
                node = node.getLchild();
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                System.out.print(node.getData());
                node = node.getRchild();
            }else {
                return;
            }

        }
    }


    /**
     * 中根遍历二叉树的非递归算法
     */
    public void inRootTraverse(){
        BiTreeNode t = root;
        if(t != null){
            Stack stack = new Stack();
            stack.push(t);                   //根结点入栈
            while(!stack.isEmpty()){        //循环的条件是: 栈不为空
                while(stack.peek() != null){    //将栈顶结点的左孩子结点相继入栈
                    stack.push(((BiTreeNode)stack.peek()).getLchild());
                }
                stack.pop();  //弹出循环添加左孩子结点时或者下面添加右孩子为null时 ，最后一次添加的null
                if(!stack.isEmpty()){               //栈不为空，输出栈中数据，同时右孩子入栈
                    t = (BiTreeNode) stack.pop();   //移除栈顶结点，并返回其值
                    System.out.print(t.getData());  //访问结点
                    stack.push(t.getRchild());     //结点的右孩子入栈
                }
            }
        }
    }



    /**
     * 后根遍历二叉树的递归算法
     */
    public void postRootTraverse(BiTreeNode t){
        if(t != null){
            postRootTraverse(t.getLchild()); //后根遍历左子树
            postRootTraverse(t.getRchild());//后根遍历右子树
            System.out.print(t.getData());//访问根结点
        }

    }

    /**
     * 后根遍历二叉树的非递归算法
     */
    public void postRootTraverse(){
        BiTreeNode t = root;
        if(t != null){
            Stack stack = new Stack();       //构造栈
            stack.push(t);                  //根结点进栈
            boolean flag = false;           //是否访问的标记
            BiTreeNode p = null;           //p指向刚被访问的最新结点
            while(!stack.isEmpty()){
                while(stack.peek() != null){  //将栈顶结点的左孩子相继入栈
                    stack.push(((BiTreeNode)stack.peek()).getLchild());
                }
                stack.pop();        //空结点退栈
                while(!stack.isEmpty()){
                    t = (BiTreeNode) stack.peek();    //查看栈顶元素
                    if(t.getRchild() == null || t.getRchild() == p){
                        System.out.print(t.getData());  //访问结点
                        flag = true;                    //设置访问标记
                        stack.pop();                    //移除栈顶元素
                        p = t;                          //p指向刚被访问的结点
                    }else{
                        stack.push(t.getRchild());      //右孩子结点入栈
                        flag = false;                   //设置未被访问标记
                    }
                    if(!flag){
                        break;
                    }
                }
            }
        }
    }

    /**
     * 后根遍历二叉树的非递归算法
     */
    public void postRootTraverse2(){
        BiTreeNode t = root;
        if(t != null){
            Stack stack = new Stack();       //构造栈
            stack.push(t);                  //根结点进栈
            //访问根节点时判断其右子树是够被访问过
            BiTreeNode p = null;           //p指向刚被访问的最新结点
            while(!stack.isEmpty()){
                while(stack.peek() != null){  //将栈顶结点的左孩子相继入栈
                    stack.push(((BiTreeNode)stack.peek()).getLchild());
                }
                stack.pop();        //空结点退栈
                while(!stack.isEmpty()){
                    t = (BiTreeNode) stack.peek();    //查看栈顶元素
                    if(t.getRchild() == null || t.getRchild() == p){
                        System.out.print(t.getData());  //访问结点
                        stack.pop();                    //移除栈顶元素
                        p = t;                          //p指向刚被访问的结点
                    }else{
                        stack.push(t.getRchild());      //右孩子结点入栈
                        break;
                    }
                }
            }
        }
    }

    /**
     * 后序遍历(非递归)
     */
    public void postOrder(){
        if(root == null){
            return;
        }
        Stack<BiTreeNode> stack = new Stack<>();
        Stack<BiTreeNode> ouputStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BiTreeNode node = stack.pop();
            ouputStack.push(node);
            if(node.getLchild() != null){
                stack.push(node.getLchild());
            }
            if(node.getRchild() != null){
                stack.push(node.getRchild());
            }
        }
        while (!ouputStack.isEmpty()){
            System.out.print(ouputStack.pop().getData());
        }
    }

    /**
     * 后序遍历(非递归)
     */
    public void postOrder2(){
        Stack<BiTreeNode> stack = new Stack<>();
        BiTreeNode node = root;
        // 访问根节点时判断其右子树是够被访问过
        BiTreeNode preNode = null;
        while(node != null || stack.size() > 0){
            // 把当前节点的左侧节点全部入栈
            while(node != null){
                stack.push(node);
                node = node.getLchild();
            }
            if(stack.size() > 0){
                // 一个根节点被访问的前提是：无右子树或右子树已被访问过
                BiTreeNode temp = stack.peek().getRchild();
                if(temp == null || preNode == temp){
                    temp = stack.pop();

                    System.out.print(temp.getData());
                    // 记录刚被访问过的节点
                    preNode = temp;
                    //顶层循环用了 node,此处需要置空
                    node = null;
                }else{
                    // 处理右子树
                    node = temp;
                }
            }
        }
    }

    /**
     * 层次遍历(递归)
     */
    public void levelOrder(List<BiTreeNode> nodeList){
        if(nodeList == null || nodeList.size() == 0) {
            return;
        }
        List<BiTreeNode> childList = new LinkedList<>();
        for (BiTreeNode node : nodeList){
            System.out.print(node.getData());
            if(node.getLchild() != null){
                childList.add(node.getLchild());
            }
            if(node.getRchild() != null){
                childList.add(node.getRchild());
            }
        }
        nodeList = null;
        levelOrder(childList);
    }

    /**
     * 层次遍历二叉树的算法(自左向右)
     * 非递归，利用队列的先进先出特性实现
     */
    public void levelTraverse(){
        BiTreeNode t = root;
        if(t != null){
            Queue queue = new LinkedList();       //构造队列
            queue.offer(t);                       //根结点入队列
            while(!queue.isEmpty()){
                t = (BiTreeNode) queue.poll();    //队首结点出队
                System.out.print(t.getData());    //访问结点
                if(t.getLchild() != null){        //左孩子非空，入队列
                    queue.offer(t.getLchild());
                }
                if(t.getRchild() != null){       //右孩子非空，入队列
                    queue.offer(t.getRchild());
                }
            }

        }
    }

    /**
     * 层次遍历二叉树的算法(自左向右)
     * 非递归，利用队列的先进先出特性实现
     */
    public void levelOrder(){
        if(root == null) {
            return;
        }
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        BiTreeNode node = null;
        while ((node = queue.poll()) != null){
            System.out.print(node.getData());
            if(node.getLchild() != null){
                queue.offer(node.getLchild());
            }
            if(node.getRchild() != null){
                queue.offer(node.getRchild());
            }
        }
    }

    public BiTreeNode getRoot() {
        return root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }

    /**
     * 获取节点总个数(递归)
     * @param node
     * @return
     */
    public int getNodeCount(BiTreeNode node){
        if(node == null){
            return 0;
        }else{
            return 1 + getNodeCount(node.getLchild()) + getNodeCount(node.getRchild());
        }
    }

    /**
     * 获取节点总个数(递归)
     * @param node
     * @param count
     * @return
     */
    public int getNodeCount(BiTreeNode node,int count){
        if(node == null){
            return count;
        }else{
            count ++;
        }
        count = getNodeCount(node.getLchild(),count);
        count = getNodeCount(node.getRchild(),count);
        return count;
    }

    /**
     * 获取节点总个数(递归)
     * (先根递归)
     * @param node
     * @return
     */
    public int getNodeCountPreRoot(BiTreeNode node){
        int count = 0 ;
        if(node != null){
            count++;
            count += getNodeCountPreRoot(node.getLchild());
            count += getNodeCountPreRoot(node.getRchild());
        }
        return count;
    }


    /**
     * 获取节点总个数(非递归)
     * @return
     */
    public int getNodeCount(){
        if(root == null){
            return 0;
        }
        int count = 0;
        Queue<BiTreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            count ++;
            BiTreeNode node = nodeQueue.remove();
            if(node.getLchild() != null){
                nodeQueue.add(node.getLchild());
            }
            if(node.getRchild() != null){
                nodeQueue.add(node.getRchild());
            }
        }
        return count;
    }


    /**
     * 获取节点总个数(非递归)
     * (层次遍历法)
     * @return
     */
    public int getNodeCount2(){
        if(root == null){
            return 0;
        }
        int count = 0;
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);              //结点进队
        count++;                        //都是在放的时候，统计结点个数
        while(!queue.isEmpty()){
            BiTreeNode node = queue.poll();
            if(node.getLchild() != null){
                queue.offer(node.getLchild());
                count++;
            }
            if(node.getRchild() != null){
                queue.offer(node.getRchild());
                count++;
            }
        }
        return count;
    }

    /**
     * 获取节点总个数(非递归)
     * (层次遍历法)
     * @return
     */
    public int getNodeCount3(){
        if(root == null){
            return 0;
        }
        int count = 0;
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BiTreeNode node = queue.poll();
            count++;                           //弹出的时候，统计结点个数
            if(node.getLchild() != null){     //root根结点，左右孩子结点，结点为空时，都放不进去
                queue.offer(node.getLchild());
            }
            if(node.getRchild() != null){
                queue.offer(node.getRchild());
            }
        }
        return count;
    }


    /**
     * 获取叶子节点总个数(递归)
     * @param node
     * @return
     */
    public int getLeafNodeCount(BiTreeNode node){
        if(node == null){
            return 0;
        }
        if(node.getLchild() == null && node.getRchild() == null) {
            return 1;
        }
        int count = getLeafNodeCount(node.getLchild()) + getLeafNodeCount(node.getRchild());
        return count;
    }

    /**
     * 获取叶子节点总个数(递归)
     * @param node
     * @return
     */
    public int getLeafNodeCount1(BiTreeNode node){
        int count = 0;
        if(node == null){
            return count;
        }
        if(node.getLchild() == null && node.getRchild() == null){
            count++;
        }else{
            count += getLeafNodeCount1(node.getLchild()) + getLeafNodeCount1(node.getRchild());
        }
        return count;
    }

    /**
     * 获取叶子节点总个数(非递归)
     * 用栈
     * @return
     */
    public int getLeafNodeCount(){
        if(root == null) {
            return 0;
        }
        int count = 0;
        Stack<BiTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            BiTreeNode node = stack.pop();
            if(node.getLchild() == null && node.getRchild() == null){
                count ++;
            }else {
                if(node.getLchild() != null){
                    stack.push(node.getLchild());
                }
                if (node.getRchild() != null){
                    stack.push(node.getRchild());
                }
            }
        }
        return count;
    }

    /**
     * 获取叶子节点总个数(非递归)
     * 用队列
     * @return
     */
    public int getLeafNodeCount2(){
        int count = 0;
        if(root == null){
            return count;
        }
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BiTreeNode node = queue.poll();
            if(node.getLchild() == null && node.getRchild() == null){
                count++;
            }else {
                if(node.getLchild() != null){
                    queue.offer(node.getLchild());
                }
                if(node.getRchild() != null){
                    queue.offer(node.getRchild());
                }
            }
        }
        return count;
    }


    /**
     * 获取第level层的节点个数(递归)
     * @param root
     * @param level
     * @return
     */
    public int getLevelNodeCount(BiTreeNode root,int level){
        if(root == null || level < 1){
            return 0;
        }
        if(level == 1){
            return 1;
        }
        int leftCount = getLevelNodeCount(root.getLchild(),level-1);
        int rightCount = getLevelNodeCount(root.getRchild(),level-1);
        return leftCount + rightCount;
    }

    /**
     * 获取第level层的节点个数(非递归)
     * @param level
     * @return
     */
    public int getLevelNodeCount(int level){
        if(root == null || level < 1){
            return 0;
        }
        int currentLevel = 1;
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            if(level == currentLevel) {
                return size;
            }
            for(int i=0;i<size;i++){
                BiTreeNode node = queue.poll();
                if(node.getLchild() != null){
                    queue.offer(node.getLchild());
                }
                if(node.getRchild() != null){
                    queue.offer(node.getRchild());
                }
            }
            currentLevel++;
        }
        return 0;
    }

    /**
     * 获取树的深度(递归)
     * @param node
     * @return
     */
    public int getDepth(BiTreeNode node){
        if(node == null){
            return 0;
        }
        int leftDepth = getDepth(node.getLchild());
        int rightDepth = getDepth(node.getRchild());
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
        //或者
        //return 1 + Math.max(leftDepth,rightDepth);
        //或者
       // return 1 + Math.max(getDeep(node.getLchild()),getDeep(node.getRchild()));
    }

    /**
     * 获取树的深度(非递归)
     * @return
     */
    public int getDepth(){
        if(root == null) {
            return 0;
        }
        int depth = 0;
        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                BiTreeNode node = queue.poll();
                if(node.getLchild() != null){
                    queue.offer(node.getLchild());
                }
                if(node.getRchild() != null){
                    queue.offer(node.getRchild());
                }
                size--;
            }
            depth ++;
        }
        return depth;
    }

    /**
     * 查找值为 x 的结点
     * 找到返回结点，找不到返回null
     */
    public BiTreeNode searchNode(BiTreeNode node,Object x){
        if(node == null){
            return null;
        }
        if(node.getData().equals(x)){
            return node;
        }else{
            BiTreeNode lResult = searchNode(node.getLchild(),x);
            return lResult == null ? searchNode(node.getRchild(),x) : lResult;
        }
    }

    /**
     * 判断两棵树是否相等
     * 若相等,则返回true;
     * 否则，返回false
     * @param t1
     * @param t2
     * @return
     */
    public boolean isEqual(BiTreeNode t1,BiTreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if((t1 == null && t2 != null) || (t2 == null && t1 != null)){
            return false;
        }
        if(!(t1.getData().equals(t2.getData()))){
            return false;
        }
        return isEqual(t1.getLchild(),t2.getLchild()) && isEqual(t1.getRchild(),t2.getRchild());
    }

    /**
     * 判断两棵树是否相等
     * 若相等,则返回true;
     * 否则，返回false
     * @param t1
     * @param t2
     * @return
     */
    public boolean isEqual2(BiTreeNode t1,BiTreeNode t2){
        if(t1 == null && t2 == null){      //同时为空进行判断
            return true;
        }
        if(t1 != null && t2 != null){      //同时非空进行比较 ，防止内部下面的空指针
            if(!(t1.getData().equals(t2.getData()))){  //结点的值是否相等
                return false;
            }
            return isEqual(t1.getLchild(),t2.getLchild()) && isEqual(t1.getRchild(),t2.getRchild());
        }else{
            return false;
        }
    }

    /**
     * 判断两棵树是否相等
     * 若相等,则返回true;
     * 否则，返回false
     * @param t1
     * @param t2
     * @return
     */
    public boolean isEqual3(BiTreeNode t1,BiTreeNode t2){
        if(t1 == null && t2 == null){      //同时为空进行判断
            return true;
        }
        if(t1 != null && t2 != null){      //同时非空进行比较 ，防止内部下面的空指针
            return t1.getData().equals(t2.getData()) &&
                    isEqual(t1.getLchild(),t2.getLchild()) && isEqual(t1.getRchild(),t2.getRchild());
        }else{
            return false;
        }
    }


    /**
     * 获取 从根结点到叶子结点的所有路径
     * @param node
     * @return
     */
    public List<String> listAllPath(BiTreeNode node){
        List<String> pathList = new LinkedList<>();
        if(node != null){
            Stack<BiTreeNode> stack = new Stack<>();
            listAllPathRecursion(node,stack,pathList);
        }
        return pathList;
    }

    /**
     * 获取 从根结点到叶子结点的所有路径
     * (递归实现)
     * @param node
     * @param stack
     * @param pathList
     */
    private void listAllPathRecursion(BiTreeNode node, Stack<BiTreeNode> stack, List<String> pathList) {
        if(node == null){
            return;
        }
        //把当前结点入栈
        stack.push(node);
        boolean isLeaf = node.getLchild() == null && node.getRchild() == null;
        if(isLeaf){
            //叶子节点，构造输出路径
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < stack.size(); i++){
                sb.append(stack.get(i).getData() + " ");
            }
            pathList.add(sb.toString());
        }

        //左子树不为空，递归调用
        if(node.getLchild() != null){
            listAllPathRecursion(node.getLchild(),stack,pathList);
        }
        //右子树不为空，递归调用
        if(node.getRchild() != null){
            listAllPathRecursion(node.getRchild(),stack,pathList);
        }
        //叶子结点出栈
        stack.pop();
    }


    /**
     * 计算二叉树根节点到叶子节点的最短路径
     * @param node
     * @return 返回的是 静态变量 minPathStack
     *          minPathStack.size() 可求长度
     */
    public static Stack<BiTreeNode> minPathStack = new Stack<>();
    public Stack<BiTreeNode> findMinPath(BiTreeNode node){
        if(node != null){
            //递归时，临时用的栈
            Stack<BiTreeNode> pathStack = new Stack<>();
            findMinPathRecursion(node,pathStack);
        }
        return minPathStack;
    }

    /**
     * 递归查找最短路径
     * @param node
     * @param pathStack
     */
    private void findMinPathRecursion(BiTreeNode node, Stack<BiTreeNode> pathStack) {
        if(node == null){
            return;
        }
        //入栈不为空的结点
        pathStack.push(node);
        boolean isLeaf = node.getLchild() == null && node.getRchild() == null;
        if(isLeaf){
            if(minPathStack.size() == 0){
                //此处需要使用 clone 方法，复制出一份，指向不同的栈，不然有问题
                minPathStack = (Stack<BiTreeNode>)pathStack.clone();
            }else{
                if(minPathStack.size() > pathStack.size()){
                    minPathStack = (Stack<BiTreeNode>)pathStack.clone();
                }
            }
        }else{
            //左孩子不为空，递归
            if(node.getLchild() != null){
                findMinPathRecursion(node.getLchild(),pathStack);
            }
            //右孩子不为空，递归
            if(node.getRchild() != null){
                findMinPathRecursion(node.getRchild(),pathStack);
            }
        }
        pathStack.pop();
    }


    /**
     * 获取每层的结点
     * @param node
     * @return
     */
    public List<List<String>> levelElement(BiTreeNode node){
        List<List<String>> levelList = new LinkedList<>();
        if(node == null){
            return levelList;
        }

        Queue<BiTreeNode> queue = new LinkedList<>();
        queue.offer(node);
        BiTreeNode tempNode;

        //队列不为空，一直遍历
        while(!queue.isEmpty()){
            //算出同一层的栈长度
            int size = queue.size();
            List<String> tempList = new LinkedList<>();
            //遍历同一级的结点元素
            for(int i = 0; i < size; i++){
                //出栈，入队列
                tempNode = queue.poll();
                tempList.add(tempNode.getData().toString());

                //左孩子不为空，入栈
                if(tempNode.getLchild() != null){
                    queue.offer(tempNode.getLchild());
                }
                //右孩子不为空，入栈
                if(tempNode.getRchild() != null){
                    queue.offer(tempNode.getRchild());
                }
            }
            //此层结点，保存起来
            levelList.add(tempList);
        }
        return levelList;
    }

}
