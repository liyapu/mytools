package com.lyp.learn.pk08.ercha2;

/**
 * 二叉树的一些操作
 */
public class BiTree {

    //根结点
    private BiTreeNode root;

    public BiTree(){

    }

    public BiTreeNode getRoot() {
        return root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }

    /**
     * 插入结点
     * @param data
     */
    public void insert(int data){
        BiTreeNode newNode = new BiTreeNode(data);
        //当前结点
        BiTreeNode currNode = root;
        //当前结点的父结点
        BiTreeNode parentNode = null;
        //如果是空树
        if(root == null){
            root = newNode;
            return;
        }

        while (true){
            parentNode = currNode;
            if(data > currNode.getData()){
                //向右搜寻
                currNode = currNode.getRchild();
                if(currNode == null){
                    parentNode.setRchild(newNode);
                    return;
                }
            }else{
                //向左搜寻
                currNode = currNode.getLchild();
                if(currNode == null){
                    parentNode.setLchild(newNode);
                    return;
                }
            }
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(BiTreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.getData() + " ");
        preOrder(node.getLchild());
        preOrder(node.getRchild());
    }


    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(BiTreeNode node){
        if(node == null){
            return;
        }
        inOrder(node.getLchild());
        System.out.print(node.getData() + " ");
        inOrder(node.getRchild());
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(BiTreeNode node){
        if(node  == null){
            return;
        }
        postOrder(node.getLchild());
        postOrder(node.getRchild());
        System.out.print(node.getData() + " ");
    }


    /**
     * 查找结点
     * @param data
     * @return
     */
    public BiTreeNode find(int data){
        BiTreeNode currNode = root;
        while(currNode != null){
            if(data > currNode.getData()){
                currNode = currNode.getRchild();
            }else if(data < currNode.getData()){
                currNode = currNode.getLchild();
            }else{
                return currNode;
            }
        }
        return null;
    }

    /**
     * 删除结点 分为3种情况
     * 1.叶子结点
     * 2.该节点有一个子节点
     * 3.该节点有二个子节点
     * @param data
     */
    public boolean delete(int data) throws Exception {
        BiTreeNode currNode = root;
        //保持一个父节点的引用
        BiTreeNode parentNode = currNode;
        //删除结点是左子结点还是右子结点，
        boolean isLeft = true;
        while(currNode != null && currNode.getData() != data){
            parentNode = currNode;
            if(data > currNode.getData()){
                currNode = currNode.getRchild();
                isLeft = false;
            }else{
                currNode = currNode.getLchild();
                isLeft = true;
            }
        }
        if(currNode==null){
            //要删除的结点不存在
            return false;
        }

        //第一种情况,要删除的结点为叶子结点
        if(currNode.getLchild() == null && currNode.getRchild() == null){
            if(currNode == root){
                root = null;
                return true;
            }
            if(isLeft){
                parentNode.setLchild(null);
            }else{
                parentNode.setRchild(null);
            }
        }else if(currNode.getLchild() == null){
            //第二种情况，要删除的结点有一个子节点且是右子结点
            if(currNode == root){
                root = currNode.getRchild();
                return true;
            }
            if(isLeft){
                parentNode.setLchild(currNode.getRchild());
            }else{
                parentNode.setRchild(currNode.getRchild());
            }
        }else if(currNode.getRchild() == null){
            //第二种情况，要删除的结点有一个子节点且是左子结点
            if(currNode == root){
                root = currNode.getLchild();
                return true;
            }
            if(isLeft){
                parentNode.setLchild(currNode.getLchild());
            }else{
                parentNode.setRchild(currNode.getLchild());
            }
        }else{
            //第三种情况，也是最复杂的一种情况，要删除的结点有两个子节点，需要找寻中序后继结点
            BiTreeNode succeeder = getSucceeder(currNode);
            if(currNode == root){
                root = succeeder;
                return  true;
            }
            if(isLeft){
                parentNode.setLchild(succeeder);
            }else{
                parentNode.setRchild(succeeder);
            }
            //当后继结点为删除结点的右子结点
            succeeder.setLchild(currNode.getLchild());

        }
        return true;
    }

    /**
     * 查找中序遍历时指定结点的后继结点
     * @param delNode
     * @return
     */
    private BiTreeNode getSucceeder(BiTreeNode delNode){
        //后继结点
        BiTreeNode succeeder = delNode;
        BiTreeNode parent = delNode;
        BiTreeNode currNode = delNode.getRchild();
        //寻找后继结点
        while(currNode != null){
            parent = succeeder;
            succeeder = currNode;
            currNode = currNode.getLchild();
        }

        //如果后继结点不是要删除结点的右子结点
        if(succeeder != delNode.getRchild()){
            parent.setLchild(succeeder.getRchild());
            //将后继结点的左右子结点分别指向要删除结点的左右子节点
            succeeder.setLchild(delNode.getLchild());
            succeeder.setRchild(delNode.getRchild());
        }

        return succeeder;
    }


    /**
     * 删除结点 分为3种情况
     * 1.叶子结点
     * 2.该节点有一个子节点
     * 3.该节点有二个子节点
     * @param data
     */
    public boolean delete2(int data) {
        BiTreeNode current = root;    //需要删除的节点
        BiTreeNode parent = null;     //需要删除的节点的父节点
        boolean isLeftChild = true;   //需要删除的节点是否父节点的左子树

        while (true) {
            if (data == current.getData()) {
                break;
            } else if (data < current.getData()) {
                isLeftChild = true;
                parent = current;
                current = current.getLchild();
            } else {
                isLeftChild = false;
                parent = current;
                current = current.getRchild();
            }

            //找不到需要删除的节点，直接返回
            if (current == null)
                return false;
        }

        //分情况考虑
        //1、需要删除的节点为叶子节点
        if (current.getLchild() == null && current.getRchild() == null) {
            //如果该叶节点为根节点，将根节点置为null
            if (current == root) {
                root = null;
            } else {
                //如果该叶节点是父节点的左子节点，将父节点的左子节点置为null
                if (isLeftChild) {
                    parent.setLchild(null);
                } else { //如果该叶节点是父节点的右子节点，将父节点的右子节点置为null
                    parent.setRchild(null);
                }
            }
        }
        //2、需要删除的节点有一个子节点，且该子节点为左子节点
        else if (current.getRchild() == null) {
            //如果该节点为根节点，将根节点的左子节点变为根节点
            if (current == root) {
                root = current.getLchild();
            } else {
                //如果该节点是父节点的左子节点，将该节点的左子节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.setLchild(current.getLchild());
                } else {  //如果该节点是父节点的右子节点，将该节点的左子节点变为父节点的右子节点
                    parent.setRchild(current.getLchild());
                }
            }
        }
        //2、需要删除的节点有一个子节点，且该子节点为右子节点
        else if (current.getLchild() == null) {
            //如果该节点为根节点，将根节点的右子节点变为根节点
            if (current == root) {
                root = current.getRchild();
            } else {
                //如果该节点是父节点的左子节点，将该节点的右子节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.setLchild(current.getRchild());
                } else {  //如果该节点是父节点的右子节点，将该节点的右子节点变为父节点的右子节点
                    parent.setRchild(current.getRchild());
                }
            }
        }
        //3、需要删除的节点有两个子节点，需要寻找该节点的后续节点替代删除节点
        else {
            BiTreeNode successor = getSuccessor2(current);
            //如果该节点为根节点，将后继节点变为根节点，并将根节点的左子节点变为后继节点的左子节点
            if (current == root) {
                root = successor;
            } else {
                //如果该节点是父节点的左子节点，将该节点的后继节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.setLchild(successor);
                } else {  //如果该节点是父节点的右子节点，将该节点的后继节点变为父节点的右子节点
                    parent.setRchild(successor);
                }
            }
        }
        current = null;
        return true;
    }

    /**
     *
     * 得到后继节点，即删除节点的左后代
     */
    private BiTreeNode getSuccessor2(BiTreeNode delNode) {
        BiTreeNode successor = delNode;
        BiTreeNode successorParent = null;
        BiTreeNode current = delNode.getRchild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLchild();
        }

        //如果后继节点不是删除节点的右子节点时，
        if (successor != delNode.getRchild()) {
            //要将后继节点的右子节点指向后继结点父节点的左子节点，
            successorParent.setLchild(successor.getRchild());
            //并将删除节点的右子节点指向后继结点的右子节点
            successor.setRchild(delNode.getRchild());
        }
        //任何情况下，都需要将删除节点的左子节点指向后继节点的左子节点
        successor.setLchild(delNode.getLchild());
        return successor;
    }

    /**
     * 得到最小值：依次向左直到空为止
     * @return
     */
    public int getMinValue(){
        BiTreeNode currNode = root;
        while(true){
            if(currNode.getLchild() == null){
                return currNode.getData();
            }
            currNode = currNode.getLchild();
        }
    }

    /**
     * 得到最大值：依次向右直到空为止
     * @return
     */
    public int getMaxValue(){
        BiTreeNode currNode = root;
        while(true){
            if(currNode.getRchild() == null){
                return currNode.getData();
            }
            currNode = currNode.getRchild();
        }
    }

}
