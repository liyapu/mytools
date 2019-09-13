package com.lyp.learn.single;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-05-22 20:45
 */
public class Testss {
    /**
     * 定义单链表结点
     */
    class Node {
        int val;
        Node next;

        Node(){

        }
        Node(int x){
            val = x;
        }
    }


    public Node mergeTwoLists(Node l1, Node l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Node head = null;
        //如果l1结点的值小于等于l2结点的值，由于这两个链表是有序的，
        // 所以合并后最小的结点(head结点)就是它们两者中的小者
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;//后移，用于继续比较选出接下来最小的结点
        }else {
            head = l2;
            l2 = l2.next;
        }
        Node temp = head;
        //接着比较两个链表，对两个链表中的最小的结点进行比较选出最小的，
        // 也就是合并后的第二小的结点，循环直到有一个链表为空
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                //设置临时结点的后继
                temp.next = l1;
                //同时小结点l1往后移动一个
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            //临时结点后移一个
            temp = temp.next;
        }

        if (l1 == null) {
            temp.next = l2;
        }

        if (l2 == null) {
            temp.next = l1;
        }
        return head;
    }

    public Node mergeTwoListMethod(Node l1, Node l2) {
        Node head = new Node();
        Node temp = head;
        Node node1 = l1 == null ? null : l1.next;
        Node node2 = l2 == null ? null : l2.next;
        while(node1 != null || node2 != null){
            if(node1 == null){
                temp.next = node2;
                node2 = node2.next;
            }else if(node2 == null){
                temp.next = node1;
                node1 = node1.next;
            }else{
                if(node1.val > node2.val){
                    temp.next = node2;
                    node2 = node2.next;
                }else{
                    temp.next = node1;
                    node1 = node1.next;
                }
            }
            temp = temp.next;
        }
        return head;
    }


    /**
     * 递归实现合并
     * @param l1
     * @param l2
     * @return 每次返回两个链表中，比较的两个结点中的小结点
     */
    public Node mergeListRecursion(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Node head = null;
        if (l1.val <= l2.val){
            //l1小，赋给head结点
            head = l1;
            //设置head结点的下一个结点
            //递归时，l1结点后移一个
            head.next = mergeListRecursion(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeListRecursion(l1, l2.next);
        }
        return head;
    }


    public static void main(String[] args) {
        Testss t = new Testss();
        t.test();
    }

    public void test(){
        Node l1 = createNode1();
        Node l2 = createNode2();
        Node head1 = mergeTwoListMethod(l1,l2);
        dispaly(head1);

    }

    public Node createNode1(){
        Node l1 = new Node();

        Node n1 = new Node(1);
        Node n2 = new Node(3);
        Node n3 = new Node(6);
        Node n4 = new Node(8);

        l1.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        return l1;
    }

    public Node createNode2(){
        Node l2 = new Node();

        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(5);
        Node n4 = new Node(7);

        l2.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        return l2;
    }

    void dispaly(Node head){
        Node next = head.next;
        while(next != null){
            System.out.print(next.val + ", ");
            next = next.next;
        }
        System.out.println();
    }
    
    


}
