package com.lyp.learn.pk02.singlealgorithm;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MySingleAlgoritmList implements MyList {

    public Node head;

    public Node tail;

    private int curLength;

    public MySingleAlgoritmList() {
        head = new Node();
        tail = head;
        curLength = 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 获取单链表的长度
     */
    @Override
    public int length() {
        return curLength;
    }

    /**
     * 获取单链表的长度
     */
    @Override
    public int length1() {
        Node next = head.getNext();
        int length = 0;
        while (next != null) {
            length++;
            next = next.getNext();
        }
        return length;
    }


    /**
     * 获取单链表的长度
     * 从头指针或者指定结点开始
     *
     * @param head
     * @return
     */
    @Override
    public int length2(Node head) {
        int length = 0;
        if (head == null) {
            return length;
        }
        Node next = head.getNext();
        while (next != null) {
            length++;
            next = next.getNext();
        }
        return length;
    }

    @Override
    public Object get(int i) throws Exception {
        return null;
    }

    /**
     * 只有 头指针 的 头插法
     * 有头结点
     *
     * @param object
     * @throws Exception
     */
    @Override
    public void addOfHeadInsertionOfHead(Object object) throws Exception {
        Node next = head.getNext();
        Node newNode = new Node(object, next);
        head.setNext(newNode);
        curLength++;
    }

    /**
     * 有 头指针，尾指针 的 头插法
     * 有头结点
     *
     * @param object
     * @throws Exception
     */
    @Override
    public void addOfHeadInsertionOfHeadTail(Object object) throws Exception {
        Node next = head.getNext();
        if (tail == next) {
            Node newNode = new Node(object);
            head.setNext(newNode);
            tail = newNode;
        } else {
            Node newNode = new Node(object, next);
            head.setNext(newNode);
        }
        curLength++;
    }

    /**
     * 有 头指针，尾指针 的 尾插法
     * 有头结点
     *
     * @param object
     * @throws Exception
     */
    @Override
    public void addTailInsertionOfHeadTail(Object object) throws Exception {
        Node newNode = new Node(object);
        tail.setNext(newNode);
        tail = newNode;
        curLength++;
    }

    @Override
    public void addTailInsertionOfHeadTail(Node node) throws Exception {
        tail.setNext(node);
        tail = node;
        curLength++;
    }

    @Override
    public void insert(int i, Object object) throws Exception {

    }

    @Override
    public void set(int i, Object object) throws Exception {

    }

    @Override
    public void remove(int i) throws Exception {

    }

    @Override
    public void remove2(int i) throws Exception {

    }

    @Override
    public void remove(Object object) throws Exception {

    }

    @Override
    public void remove2(Object object) throws Exception {

    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public Object findLastNodeItem(int index) throws Exception {
        //获取链表长度
        int length = 0;
        Node next = head.getNext();
        while (next != null) {
            length++;
            next = next.getNext();
        }

        //参数校验
        if (index < 0 || index >= length) {
            throw new Exception("findLastNodeItem 获取倒数元素，下标错误 index = " + index);
        }
        //从前往后数的下标值， 注意 - 1
        int ascIndex = length - index - 1;
        int tempIndex = 0;
        next = head.getNext();
        while (next != null) {
            if (ascIndex == tempIndex) {
                return next.getItem();
            }
            tempIndex++;
            next = next.getNext();
        }
        return null;
    }

    @Override
    public Object findLastNodeItem2(int index) throws Exception {
        if (index < 0 || index >= curLength) {
            throw new Exception("findLastNodeItem2 获取倒数元素，下标错误 index = " + index);
        }
        Node prev = tail;
        int length = 0;
        while (prev != head) {
            if (length == index) {
                return prev.getItem();
            }
            length++;
            //prev = prev.getPrev();
        }
        return null;
    }

    @Override
    public Object findLastNodeItem3(int index) throws Exception {
        Node first = head.getNext();
        Node second = head.getNext();
        for (int i = 0; i < index && second != null; i++) {
            second = second.getNext();
        }
        while (second != null) {
            second = second.getNext();
            first = first.getNext();
        }
        return first.getItem();
    }

    @Override
    public Object findMiddleItem() {
        Node first = head.getNext();
        Node second = head.getNext();
        //空结点
        if (second == null) {
            return null;
        }
        //每次移动时，让second结点移动两位，first结点移动一位
        while (second != null && second.getNext() != null) {
            second = second.getNext().getNext();
            first = first.getNext();
        }
        return first.getItem();
    }

    @Override
    public List<Integer> mergeList(List<Integer> list1, List<Integer> list2) {
        //size1 指向list1的长度
        int size1 = list1.size();
        int size2 = list2.size();
        List<Integer> resultList = new ArrayList<>(size1 + size2);
        //i是list1的下标； j 是list2的下标
        int i = 0;
        int j = 0;
        while (i < size1 || j < size2) {
            //list1 遍历完了，把list2剩下的都放到 resultList中
            if (i >= size1) {
                resultList.add(list2.get(j++)); // list2的下标变量 j++
            } else if (j >= size2) {
                resultList.add(list1.get(i++));
            } else {
                if (list1.get(i) < list2.get(j)) {
                    resultList.add(list1.get(i++));
                } else {
                    resultList.add(list2.get(j++));
                }
            }
        }
        return resultList;
    }

    @Override
    public Node mergeList(Node head1, Node head2) throws Exception {
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }

        Node head = null;
        if(Integer.parseInt(head1.getItem().toString()) <= Integer.parseInt(head2.getItem().toString())){
            head = head1;
            head1 = head1.getNext();
        }else{
            head = head2;
            head2 = head2.getNext();
        }

        Node temp = head;
        while(head1 != null && head2 != null){
            if(Integer.parseInt(head1.getItem().toString()) <= Integer.parseInt(head2.getItem().toString())){
                temp.setNext(head1);
                head1 = head1.getNext();
            }else{
                temp.setNext(head2);
                head2 = head2.getNext();
            }
            temp = temp.getNext();
        }

        if(head1 == null){
            temp.setNext(head2);
        }
        if(head2 == null){
            temp.setNext(head1);
        }

        return head;
    }

    @Override
    public Node mergeListRecursion(Node head1, Node head2) throws Exception {
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        Node head = null;
        if(Integer.parseInt(head1.getItem().toString()) < Integer.parseInt(head2.getItem().toString())){
            head = head1;
            head.setNext(mergeListRecursion(head1.getNext(),head2));
        }else{
            head = head2;
            head.setNext(mergeListRecursion(head1,head2.getNext()));
        }
        return head;
    }

    @Override
    public Node reverseList() {
        Node newReverseHead = new Node();
        if(head == null){
            return newReverseHead;
        }
        newReverseHead.setNext(head.getNext());
        Node prev = newReverseHead.getNext();
        Node pcur = prev.getNext();
        while(pcur != null){
            prev.setNext(pcur.getNext());
            //pcur的后继结点是 newReverseHead的后继结点
            pcur.setNext(newReverseHead.getNext());
            newReverseHead.setNext(pcur);
            pcur = prev.getNext();
        }
        return newReverseHead;
    }

    @Override
    public Node reverseList1() {
        Node newHead = new Node();
        Node next = head.getNext();

        while(next != null){
            if(newHead.getNext() == null){
                newHead.setNext(new Node(next.getItem()));
                next = next.getNext();
            }else{
                Node t = next.getNext();
                next.setNext(newHead.getNext());
                newHead.setNext(next);
                next = t;
            }
        }
        return newHead;
    }

    @Override
    public Node reverseList2() {
        Node newReverseHead = new Node();
        Node pcur = head.getNext();
        while (pcur != null){
            Node pnext = pcur.getNext();
            pcur.setNext(newReverseHead.getNext());
            newReverseHead.setNext(pcur);
            pcur = pnext;
        }
        return newReverseHead;
    }

    @Override
    public Node reverseList3(Node head) {
        if(head.getNext() == null){
            return head;
        }
        //顺序链表的尾结点，反转之后会是链接的首结点，tailNode 指向此结点
        //tailNode 一直都指向末尾结点
        Node tailNode = reverseList3(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return tailNode;
    }

    @Override
    public Node reverseList4(Node head) {
        if(head.getNext() == null){
            return head;
        }
        //顺序链表的尾结点，反转之后会是链接的首结点，tailNode 指向此结点
        //tailNode 一直都指向末尾结点
        Node tailNode = reverseList3(head.getNext());
        //头结点，不反转拼接了
        if(head != this.head){
            head.getNext().setNext(head);
            head.setNext(null);
        }else{
            head.setNext(null);
        }
        return tailNode;
    }

    @Override
    public void reversePrint1(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        //新建一个栈
        Stack<Node> stacks = new Stack<>();
        while(cur != null){
            //将链表的所有结点压栈
            stacks.push(cur);
            cur = cur.getNext();
        }

        //将栈中的结点打印输出即可
        while(stacks.size() > 0){
            //出栈操作
            System.out.print(stacks.pop().getItem() + ",");
        }
    }

    @Override
    public void reversePrint2(Node head) {
        if(head == null){
            return;
        }

        //递归下一个结点
        reversePrint2(head.getNext());
        //打印此结点
        System.out.print(head.getItem() + ",");
    }

    @Override
    public boolean hasCycle(Node head) {
        Node first = head;
        Node second = head;
        while(second != null &&  second.getNext() != null){
            first = first.getNext();
            second = second.getNext().getNext();
            if(first == second){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCycleLength(Node head) {
        Node first = head;
        Node second = head;
        Node meetNode = null;
        while(second != null && second.getNext() != null){
            first = first.getNext();
            second = second.getNext().getNext();
            if(first == second){
                meetNode = first;
                break;
            }
        }

        int length = 0;
        if(meetNode == null){
            return length;
        }

        Node current = meetNode;
        while(current != null){
            length++;
            current  = current.getNext();
            if(current == meetNode){
                break;
            }
        }
        return length;
    }

    @Override
    public Node cycleStartNode(Node head) {
        Node first = head;
        Node second = head;
        Node meetNode = null;
        while(second != null && second.getNext() != null){
            first = first.getNext();
            second = second.getNext().getNext();
            if(first == second){
                meetNode = first;
                break;
            }
        }
        if(meetNode == null){
            return null;
        }

        int length = 0;
        Node current = meetNode;
        while(current != null){
            length++;
            current = current.getNext();
            if(current == meetNode){
                break;
            }

        }

        first = head;
        second = head;
        for(int i = 0; i < length; i++){
            second = second.getNext();
        }

        while(first != second){
            first = first.getNext();
            second = second.getNext();
        }
        return first;
    }

    @Override
    public Node entryLoop(Node head) {
        Node p1 = head;
        Node p2 = head;
        Node p3 = head;

        boolean hasLoop = false;
        while(p2 != null && p2.getNext() != null && p2.getNext().getNext() != null){
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
            if(p1 == p2){
                hasLoop = true;
                break;
            }
        }

        if(hasLoop){
            while(p1 != p3){
                p1 = p1.getNext();
                p3 = p3.getNext();
            }
            return p3;
        }
        return null;
    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public void display() {
        Node next = head.getNext();
        while(next != null){
            System.out.print(next.getItem());
            if(tail != next){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }

    @Override
    public void display2() {

    }
}
