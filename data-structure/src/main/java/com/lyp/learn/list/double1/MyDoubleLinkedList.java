package com.lyp.learn.list.double1;

/**
 *  双向链表   有头结点
 *
 *       头结点
 *        head
 *  null|null|next <---> prev|A|next <---> prev|B|next ---> prev|C|next ---> prev|D|null
 *
 *
 */
public class MyDoubleLinkedList implements MyList {
    //头结点指针
    private Node head;

    //链表实际存储元素长度
    private int curLength;


    public MyDoubleLinkedList(){
        head = new Node();
        curLength = 0;
    }

    @Override
    public void clear() {
        Node next = head.getNext();
        while(next != null){
            Node temp = next.getNext();
            next.setPrev(null);
            next.setItem(null);
            next.setNext(null);
            next = temp;
        }
        head = new Node();
        curLength = 0;
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
     * 从前向后遍历
     * @param i
     * @return
     * @throws Exception
     */
    @Override
    public Object get(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("get 元素下标 错误 i = " + i);
        }
        Node next = head.getNext();
        int length = 0;
        while(next != null){
            if(i == length++){
                return next.getItem();
            }
            next = next.getNext();
        }
        return null;
    }

    /**
     * 采用头插法
     * @param object
     * @throws Exception
     */
    @Override
    public void add(Object object) throws Exception {
        Node next = head.getNext();
        //第一次插入元素
        if(next == null){
            Node newNode = new Node(object);
            head.setNext(newNode);
            newNode.setPrev(head);
        }else{
            Node newNode = new Node(head,object,next);
            head.setNext(newNode);
            next.setPrev(newNode);
        }
        curLength++;
    }

    @Override
    public void insert(int i, Object object) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("insert 元素下标 错误 i = " + i);
        }

        Node prev = head;
        Node next = head.getNext();
        int length = 0;

        //第一次插入元素
        if(next == null){
            Node newNode = new Node(head,object);
            head.setNext(newNode);
        }else{
            while(next != null){
                if(i == length){
                    Node newNode = new Node(prev,object,next);
                    next.setPrev(newNode);
                    prev.setNext(newNode);
                }
                length++;
                prev = next;
                next = next.getNext();
            }
            curLength++;
        }
    }

    @Override
    public void set(int i, Object object) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("set 元素下标 错误 i = " + i);
        }
        Node next = head.getNext();
        int length = 0;
        while(next != null){
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
            throw new Exception("remove 元素下标 错误 i = " + i);
        }
        Node prev = head;
        Node next = head.getNext();
        int length = 0;
        while(next != null){
            if(i == length){
                //删除的是最后一个节点
                if(next.getNext() == null){
                    prev.setNext(null);
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

        while(next != null){
            Object item = next.getItem();
            if(object == null){
                if(item == null){
                    prev.setNext(next.getNext());
                    next.getNext().setPrev(prev);
                    curLength--;
                    break;
                }
            }else{
                if(object.equals(item)){
                    //删除的是最后一个节点
                    if(next.getNext() == null){
                        prev.setNext(null);
                    }else{
                        prev.setNext(next.getNext());
                        next.getNext().setPrev(prev);
                    }
                    curLength--;
                    break;
                }
            }
            prev = next;
            next = next.getNext();
        }
    }


    @Override
    public int indexOf(Object object) {
        Node next = head.getNext();
        int length = 0;
        while(next != null){
            Object item = next.getItem();
            if(object == null){
                if(item == null){
                    return length;
                }
            }else{
                if(object.equals(item)){
                    return length;
                }
            }
            length++;
            next = next.getNext();
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public void display() {
        Node next = head.getNext();
        int length = 0;
        while (next != null){
            length++;
            System.out.print(next.getItem());
            if(length != curLength ){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }
}
