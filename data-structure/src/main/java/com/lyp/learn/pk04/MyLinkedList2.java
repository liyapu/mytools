package com.lyp.learn.pk04;

public class MyLinkedList2 {

    public static void main(String[] args) {
        //侧重算法，没有实现链表部分
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;
        Node node11 = new Node(0);
        Node node22 = new Node(9);
        Node node33 = new Node(8);
        node11.next = node22;
        node22.next = node33;
        node33.next = node3;

        Node result = getIntersectNode(node1,node11);
        if(result == null){
            System.out.println("没有交点");
        }else{
            System.out.println("有交点，交点 = " + result.value);
        }
    }

    /**
     * 判断是否相交，如果相交，得到第一个相交点
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
    /**
     * 判断是否存在环，如果存在，则找出环的入口点。
     * 入口点找法：快慢指针，快指针走两步，慢指针走一步，
     *              如果存在循环，则在慢指针走完环前，总会和快指针相遇。
     * 从头指针和相遇点同时向后走，相遇的点必定是入口点。
     */
    public static Node getLoopNode(Node head) {
        Node n1 = head;
        Node n2 = head;
        Node n3 = head;
        boolean hasLoop = false;
        while(null != n2 && null != n2.next && null != n2.next.next){
            n1 = n1.next;
            n2 = n2.next.next;
            if(n1 == n2){
                hasLoop =true;
                break;
            }
        }

        if(!hasLoop){
            return null;
        }

        while(n3 != n1){
            n1 = n1.next;
            n3 = n3.next;
        }
        return n3;
    }

    /**
     * 无环时的判断方法
     * 返回两链表的交点
     * Y 型的两条链表
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n1 = 0;
        int n2 = 0;
        while (cur1.next != null) {
            n1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n2++;
            cur2 = cur2.next;
        }

        cur1 = head1;
        cur2 = head2;
        int cha = 0;
        if(n1 > n2){
            cha = n1 - n2;
            for(int i = 0; i < cha; i++){
                cur1 = cur1.next;
            }
        }else{
            cha = n2 - n1;
            for(int i = 0; i < cha; i++){
                cur2 = cur2.next;
            }
        }
        while(cur1 != null && cur2 != null){
            cur1 = cur1.next;
            cur2 = cur2.next;
            if(cur1 == cur2){
                break;
            }
        }
        return cur1;
    }

    /**
     * 有环时的判断方法
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n1 = 0;
            int n2 = 0;
            int cha = 0;
            while (cur1 != loop1) {
                n1++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n2++;
                cur2 = cur2.next;
            }

            //重新指向头指针
            cur1 = head1;
            cur2 = head2;
            if(n1 > n2){
                cha = n1 - n2;
                for(int i = 0 ; i < cha; i++){
                    cur1 = cur1.next;
                }
            }else{
                cha = n2 - n1;
                for(int i = 0; i < cha; i++){
                    cur2 = cur2.next;
                }
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

}
