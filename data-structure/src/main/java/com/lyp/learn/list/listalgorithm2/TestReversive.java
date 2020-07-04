package com.lyp.learn.list.listalgorithm2;

public class TestReversive {

        private static class Node {
            private Node next;
            private Object value;
            public Node(Object value) {
                super();
                this.value = value;
            }
        }

        public static void main(String[] args){
           // int arr[] = {1, 6, 6, 7, 3, 3, 5, 3, 8, 9, 10, 10};
            int arr[] = {1, 2, 3, 4};
            Node head = new Node(arr[0]);
            Node p = head;
            for(int i = 1; i < arr.length; i++){
                p.next = new Node(arr[i]);
                p = p.next;
            }
            p = head;
            while(p != null){
                System.out.print(p.value+ " ");
                p = p.next;
            }

            System.out.println();

            p = reverseLinkedList(head);
            while(p != null){
                System.out.print(p.value+ " ");
                p = p.next;
            }
        }

        public static Node reverseLinkedList(Node node){
            //通过层层改变节点的指向来进行翻转链表
            if(node.next == null){
                return node;
            }
            //p一直都指向最后一个节点
            Node p = reverseLinkedList(node.next);
            node.next.next = node;
            node.next = null;
            return p;
        }
}
