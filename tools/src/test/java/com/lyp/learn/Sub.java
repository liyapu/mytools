package com.lyp.learn;


/**
 两链表相减
 举个例子：
 a:   3 -> 8 -> 9   (983)
 b:   6 -> 2         (26)
 output: 7 -> 5 -> 9  (957)

 1. 低位在前
 2. 不能直接把链表转成数字相减得到结果，要直接在链表上指针操作计算得到结果链表
 3. 边界条件尽量周全
 liuqingliang@doublefs.com
 **/

class Node {
    int value;
    Node next;

    public Node(){
    }
    public Node(int v){
        this.value = v;
    }
    public Node(int v,Node next){
        this.value = v;
        this.next = next;
    }
    public void setNext(Node next){
        this.next = next;
    }
}

public class Sub{

    public static Node head1 = null;
    public static Node head2 = null;

    private static void buildHead1(){
        head1 = new Node();
        Node a1 = new Node(3);
        Node a2 = new Node(8);
        Node a3 = new Node(9);
        head1.setNext(a1);
        a1.setNext(a2);
        a2.setNext(a3);
    }

    private static void buildHead2(){
        head2 = new Node();
        Node a1 = new Node(6);
        Node a2 = new Node(2);
        head2.setNext(a1);
        a1.setNext(a2);
    }

    public static void printLinkNode(Node head){
        if(head == null){
            System.out.println("head 为null");
            return;
        }
        Node next = head.next;
        StringBuilder sb = new StringBuilder();
        while (next != null){
            //System.out.print(next.value);
            sb.append(next.value);
            next = next.next;
        }
        System.out.println(sb.reverse());
    }



    public static void main(String [] args){
        buildHead1();
        buildHead2();
        Node n1 = head1.next;
        Node n2 = head2.next;
        subNum(n1,n2);
    }

    private static void subNum(Node n1,Node n2) {
        Node outNode = new Node();
        Node outTail = outNode;

        boolean borrowFlag = false;

        while(n1 != null || n2 != null){
            if(n1 == null){
                if(borrowFlag){
                    Node node = new Node(n2.value -1,n2.next);
                    outTail.setNext(node);
                    break;
                }else{
                    outTail.setNext(n2);
                    break;
                }
            }else if(n2 == null){
                if(borrowFlag){
                    Node node = new Node(n1.value -1,n1.next);
                    outTail.setNext(node);
                    break;
                }else{
                    outTail.setNext(n1);
                    break;
                }
            }else{
                if(borrowFlag){
                    int newN1Value = n1.value-1;

                    if(newN1Value > n2.value){
                        Node node = new Node(newN1Value - n2.value);
                        outTail.setNext(node);
                        outTail = node;
                        borrowFlag = false;
                    }else{
                        Node second = n1.next;
                        if(second == null){
                            throw new IllegalArgumentException("小数减大数了");
                        }
                        borrowFlag = true;
                        Node node = new Node((newN1Value + 10) - n2.value);
                        outTail.setNext(node);
                        outTail = node;
                        borrowFlag = true;
                    }
                }else{
                    if(n1.value > n2.value){
                        Node node = new Node(n1.value - n2.value);
                        outTail.setNext(node);
                        outTail = node;
                        borrowFlag = false;
                    }else{
                        Node second = n1.next;
                        if(second == null){
                            throw new IllegalArgumentException("小数减大数了");
                        }
                        borrowFlag = true;
                        Node node = new Node((n1.value + 10) - n2.value);
                        outTail.setNext(node);
                        outTail = node;
                        borrowFlag = true;
                    }
                }
            }

            n1 = n1.next;
            n2 = n2.next;
        }
        printLinkNode(outNode);
    }
}

