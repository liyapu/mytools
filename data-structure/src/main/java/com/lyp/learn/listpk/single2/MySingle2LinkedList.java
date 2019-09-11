package com.lyp.learn.listpk.single2;

/**
 *  单链表   有头指针，尾指针
 *
 *      头指针                              尾指针
 *      head                                 tail
 *  null|next  ---> A|next  ---> B|next --->  C|null
 *
 */
public class MySingle2LinkedList implements MyList {

    private Node head;
    private Node tail;
    private int curLength;

    public MySingle2LinkedList(){
        head = new Node();
        tail = head;
        curLength = 0;
    }

    @Override
    public void clear() {
        Node next = head.getNext();
        while(next != null){
            Node temp = next.getNext();
            next.setItem(null);
            next.setNext(null);
            next = temp;
        }
        head = new Node();
        tail = head;
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
            throw  new Exception("get 元素，下标错误 i = " + i);
        }
        Node next = head.getNext();
        int length = 0;
        while(next != null){
            if(length == i){
                return next.getItem();
            }
            length ++;
            next = next.getNext();
        }
        return null;
    }

    /**
     * 尾插法
     * @param object
     * @throws Exception
     */
    @Override
    public void add(Object object) throws Exception {
        Node temp = new Node(object);
        tail.setNext(temp);
        tail = temp;
        curLength++;
    }

    @Override
    public void insert(int i, Object object) throws Exception {
        if(i < 0 || i > curLength){
            throw new Exception("insert 元素下标错误 i = " + i);
        }
        if(curLength == i){
            add(object);
        }else{
            Node prev = head;
            Node next = head.getNext();
            int length = 0;
            while (next != null){
                if(length == i){
                    Node temp = new Node(object,next);
                    prev.setNext(temp);
                }
                length++;
                prev = next;
                next = next.getNext();
            }
        }

    }

    @Override
    public void set(int i, Object object) throws Exception {
        Node next = head.getNext();
        int length = 0;
        while (next != null){
            if(length == i){
                Object item = next.getItem();
                if(object == null){
                    if(item != null){
                        next.setItem(object);
                        break;
                    }
                }else{
                    if(!object.equals(item)){
                        next.setItem(object);
                        break;
                    }
                }
            }
            length++;
            next = next.getNext();
        }
    }

    @Override
    public void remove(int i) throws Exception {
        Node prev = head;
        Node next = head.getNext();
        int length = 0;
        while(next != null){
            if(length == i){
                if(next == tail){
                    tail = prev;
                }
                prev.setNext(next.getNext());
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
        while (next != null){
            Object item = next.getItem();
            if(object == null){
                if(item == null){
                    if(tail == next){
                        tail = prev;
                    }
                    prev.setNext(next.getNext());
                    curLength--;
                    break;
                }
            }else{
                if(object.equals(item)){
                    if(tail == next){
                        tail = prev;
                    }
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
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public int indexOf(Object object) {
        Node next = head.getNext();
        int length = 0 ;
        while (next != null){
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
        Node next = head.getNext();
        while(next != null){
            Object item = next.getItem();
            if(object == null){
                if(item == null){
                    return true;
                }
            }else{
                if(object.equals(item)){
                    return true;
                }
            }
            next = next.getNext();
        }
        return false;
    }

    @Override
    public void display() {
        Node next = head.getNext();
        while (next != null){
            System.out.print(next.getItem());
            if(next != tail){
                System.out.print(",");
            }
            next = next.getNext();
        }
        System.out.println();
    }
}
