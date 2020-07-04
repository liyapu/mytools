package com.lyp.learn.list.doublecircle;

/**
 *
 *  双向循环链表   有头指针，尾指针
 *
 *       头结点                                                                 尾指针
 *        head                                                                  tail
 *   ↑~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~↓
 *  prev|null|next <---> prev|A|next <---> prev|B|next ---> prev|C|next ---> prev|D|next
 *       ↑.........................................................................↓
 *
 */

public class MyDoubleCircleLinkedList implements MyList {

    private Node head;
    private Node tail;
    private int curLength;

    public MyDoubleCircleLinkedList(){
        head = new Node();
        head.setNext(head);
        head.setPrev(head);
        tail = head;
        curLength = 0;
    }

    @Override
    public void clear() {
        Node next = head.getNext();
        while (next != head){
            Node temp = next.getNext();
            next.setPrev(null);
            next.setItem(null);
            next.setNext(null);
            next = temp;
        }
        head = new Node();
        head.setNext(head);
        head.setPrev(head);
        tail = head;
        curLength =  0;
    }

    @Override
    public boolean isEmpty() {
        return curLength == 0;
    }

    @Override
    public int length() {
        return curLength;
    }

    /**
     * 从头到尾，顺序遍历查找
     * @param i
     * @return
     * @throws Exception
     */
    @Override
    public Object get(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw  new Exception("get 元素 下标错误 i = " + i );
        }
        Node next = head.getNext();
        int length = 0;
        while (next != head){
            if(i == length){
                return next.getItem();
            }
            length++;
            next = next.getNext();
        }
        return null;
    }

    /**
     * 判断 i 属于前半部分，还是后半部分
     * 前半部分： 从前往后遍历
     * 后半部分： 从后往前遍历
     * @param i
     * @return
     * @throws Exception
     */
    @Override
    public Object get2(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw  new Exception("get2 元素 下标错误 i = " + i );
        }

        int middle = curLength / 2;
        if(i < middle ){
            Node next = head.getNext();
            int length = 0;
            while (next != head){
                if(i == length){
                    return next.getItem();
                }
                length++;
                next = next.getNext();
            }
        }else{
            int tempi = curLength - i - 1;
            Node last = tail;
            int length = 0;
            while(last != null){
                if(tempi == length){
                    return last.getItem();
                }
                length++;
                last = last.getPrev();
            }
        }
        return null;
    }

    /**
     * 头插法
     * @param object
     * @throws Exception
     */
    @Override
    public void addFirst(Object object) throws Exception {
        //第一次插入元素
        if(head == tail){
            Node newNode = new Node(head,object,head);
            head.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }else{
            Node next = head.getNext();
            Node newNode = new Node(head,object,next);
            next.setPrev(newNode);
            head.setNext(newNode);
        }
        curLength++;
    }

    /**
     * 尾插法
     * @param object
     * @throws Exception
     */
    @Override
    public void addTail(Object object) throws Exception {
        //第一次插入元素
        if(head == tail){
            Node newNode = new Node(head,object,head);
            head.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }else{
            Node newNode = new Node(tail,object,head);
            tail.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }
        curLength++;
    }

    @Override
    public void insert(int i, Object object) throws Exception {
        if(i < 0 || i > curLength){
            throw  new Exception("insert 元素 下标错误 i = " + i);
        }
        //第一次插入节点
        if(head == tail){
            Node newNode = new Node(head,object,head);
            head.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }else if(i == curLength){//在tail位置插入
            Node newNode = new Node(tail,object,head);
            tail.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        } else{ //其它位置插入
            int length = 0;
            Node prev = head;
            Node next = head.getNext();
            while(next != head){
                if(i == length){
                    Node newNode = new Node(prev,object,next);
                    next.setPrev(newNode);
                    prev.setNext(newNode);
                    break;
                }
                length++;
                prev = next;
                next = next.getNext();
            }
        }
        curLength++;
    }

    @Override
    public void set(int i, Object object) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("set 元素，下标错误 i = " + i);
        }
        int length = 0 ;
        Node next = head.getNext();
        while(next != head){
            if(i == length){
                next.setItem(object);
                break;
            }
            length++;
            next = next.getNext();
        }
    }

    @Override
    public void remove(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("remove 元素 下标错误 i = " + i);
        }
        int length = 0;
        Node prev = head;
        Node next = head.getNext();
        while(next != head){
            if(i == length){
                if(tail == next){
                    prev.setNext(head);
                    next.setItem(null);
                    next.setPrev(null);
                    head.setPrev(prev);
                    tail = prev;
                }else{
                    prev.setNext(next.getNext());
                    next.getNext().setPrev(prev);
                }
                curLength--;
                break;
            }
            length++;
            prev = next;
            next = next.getNext();
        }
    }

    @Override
    public void remove(Object object) throws Exception {
        Node prev = head;
        Node next = head.getNext();
        if(object == null){
            while(next != head){
                Object item = next.getItem();
                if(item == null){
                    if(next == tail){
                        tail.setPrev(null);
                        tail.setPrev(null);
                        prev.setNext(head);
                        head.setPrev(prev);
                        tail = prev;
                    }else{
                        prev.setNext(next.getNext());
                        next.getNext().setPrev(prev);
                    }
                    curLength--;
                    break;
                }
                prev = next;
                next = next.getNext();
            }
        }else{
            while(next != head){
                Object item = next.getItem();
                if(object.equals(item)){
                    if(next == tail){
                        tail.setPrev(null);
                        tail.setPrev(null);
                        prev.setNext(head);
                        head.setPrev(prev);
                        tail = prev;
                    }else{
                        prev.setNext(next.getNext());
                        next.getNext().setPrev(prev);
                    }
                    curLength--;
                    break;
                }
                prev = next;
                next = next.getNext();
            }
        }
    }

    @Override
    public int lastIndexOf(Object object) {
        if(head == tail){
            return -1;
        }

        int length = curLength - 1;
        Node prev = tail;
        if(object == null){
            while(prev != null && prev != head){
                Object item = prev.getItem();
                if(item == null){
                    return length;
                }
                length--;
                prev = prev.getPrev();
            }
        }else{
            while(prev != null && prev != head){
                Object item = prev.getItem();
                if(object.equals(item)){
                    return length;
                }
                length--;
                prev = prev.getPrev();
            }
        }
        return -1;
    }

    @Override
    public int indexOf(Object object) {
        Node next = head.getNext();
        int length = 0;
        if(object == null){
            while(next != head){
                Object item = next.getItem();
                if(item == null){
                    return length;
                }
                length++;
                next = next.getNext();
            }
        }else{
            while(next != head){
                Object item = next.getItem();
                if(object.equals(item)){
                    return length;
                }
                length++;
                next = next.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * head ----> tail 遍历
     */
    @Override
    public void display() {
        Node next = head.getNext();
        while(next != head){
            System.out.print(next.getItem());
            if(next != tail){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }

    /**
     *  tail ----> head 遍历
     */
    @Override
    public void displayDesc() {
        Node prev = tail;
        int length = curLength;
        while(prev != null && prev != head){
            System.out.print(prev.getItem());
            length--;
            if(length != 0){
                System.out.print(",");
            }
            prev = prev.getPrev();
        }
        System.out.println();
    }
}
