package com.lyp.learn.pk02.singlecircle;

/**
 *   循环单链表，有一个头指针的
 *
 *    有头指针
 *      head
 *  null|next  ---> A|next  ---> B|next --->  C|next
 *      ￪________________________________________↓
 *
 */
public class MySingleCircleLinkedList implements MyList {
    
    private Node head;
    private int curLength;
    
    public MySingleCircleLinkedList(){
        head = new Node();
        head.setNext(head);
        curLength = 0;
    }

    @Override
    public void clear() {
        Node next = head.getNext();
        while (next != head){
            Node temp = next.getNext();

            next.setItem(null);
            next.setNext(null);

            next = temp;
        }
        head = new Node();
        head.setNext(head);
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

    @Override
    public Object get(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("get 元素，下标错误 i = " + i);
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
     * 采用 头插法 添加
     * @param object
     * @throws Exception
     */
    @Override
    public void add(Object object) throws Exception {
        Node next = head.getNext();
        //只有一个头指针，没有元素
        if(next == head){
            Node newNode = new Node(object,head);
            head.setNext(newNode);
        }else{
            Node newNode = new Node(object,next);
            head.setNext(newNode);
        }
        curLength++;
    }

    @Override
    public void insert(int i, Object object) throws Exception {
        if(i < 0 || i > curLength){
            throw new Exception("insert 元素，下标错误 i = " + i);
        }
        if(i == 0){
            add(object);
        }else{
            Node prev = head;
            Node next = head.getNext();
            int length = 0;
            while(next != head){
                if(i == length){
                    Node newNode = new Node(object,next);
                    prev.setNext(newNode);
                    break;
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
        Node next = head.getNext();
        int length = 0;
        while(next != head){
            if(i == length++){
                next.setItem(object);
                break;
            }
            next = next.getNext();
        }
    }

    @Override
    public void remove(int i) throws Exception {
        Node prev = head;
        Node next = head.getNext();
        int length = 0;
        while(next != head){
            if(i == length){
                prev.setNext(next.getNext());
                curLength--;
                //设置好之后，break退出
                break;
            }
            //变量重新赋值，循环向后推进
            length++;
            prev = next;
            next = next.getNext();
        }

    }

    @Override
    public void remove2(int i) throws Exception {
        Node next = head;
        int length = 0;
        while(next.getNext() != head){
            if(i == length){
                next.setNext(next.getNext().getNext());
                curLength--;
                break;
            }
            length++;
            next = next.getNext();
        }

    }

    @Override
    public void remove(Object object) throws Exception {
        Node prev = head;
        Node next = head.getNext();
        while(next != head){
            Object item = next.getItem();
            if(object == null){
                if(item == null){
                    prev.setNext(next.getNext());
                    curLength--;
                    break;
                }
            }else{
                if(object.equals(item)){
                    prev.setNext(next.getNext());
                    curLength--;
                    break;
                }
            }
            prev = next;
            next = next.getNext();
        }

    }

    @Override
    public void remove2(Object object) throws Exception {
        Node next = head;
        while(next.getNext() != head){
            Object item = next.getNext().getItem();
            if(object == null){
                if(item == null){
                    next.setNext(next.getNext().getNext());
                    curLength--;
                    break;
                }
            }else{
                if(object.equals(item)){
                    next.setNext(next.getNext().getNext());
                    curLength--;
                    break;
                }
            }
            next = next.getNext();
        }

    }

    @Override
    public int indexOf(Object object) {
        Node next = head.getNext();
        int length = 0;
        while (next != head){
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
        while(next != head){
            length++;
            System.out.print(next.getItem());
            if(length != curLength){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }

    @Override
    public void display2() {
        Node next = head.getNext();
        for(int i = 1 ;i <= curLength; i++){
            System.out.print(next.getItem());
            if(i != curLength){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }
}
