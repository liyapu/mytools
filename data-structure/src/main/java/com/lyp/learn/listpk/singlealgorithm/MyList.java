package com.lyp.learn.listpk.singlealgorithm;

import java.util.List;

public interface MyList {

    void clear();

    boolean isEmpty();

    int length();

    int length1();

    int length2(Node node);

    Object get(int i) throws Exception;

    void addOfHeadInsertionOfHead(Object object) throws Exception;

    void addOfHeadInsertionOfHeadTail(Object object) throws Exception;

    void addTailInsertionOfHeadTail(Object object) throws Exception;

    void addTailInsertionOfHeadTail(Node node) throws Exception;

    void insert(int i, Object object) throws Exception;

    void set(int i, Object object) throws Exception;

    void remove(int i) throws Exception;

    void remove2(int i) throws Exception;

    void remove(Object object) throws Exception;

    void remove2(Object object) throws Exception;

    int indexOf(Object object);

    /**
     * 获取倒数第index个结点值
     * @param index
     * @return
     */
    Object findLastNodeItem(int index) throws Exception;

    /**
     * 获取倒数第index个结点值
     * 有尾指针
     * @param index
     * @return
     */
    Object findLastNodeItem2(int index) throws Exception;

    /**
     * 获取倒数第index个结点值
     * 有尾指针
     * @param index
     * @return
     */
    Object findLastNodeItem3(int index) throws Exception;

    /**
     * 查找中间结点值
     * @return
     */
    Object findMiddleItem();

    /**
     * 合并两个有序列表，之后依然有序
     * @param list1
     * @param list2
     */
    List<Integer> mergeList(List<Integer> list1, List<Integer> list2);

    /**
     * 合并两个有序单链表，之后依然有序
     * @return
     */
    public Node mergeList(Node head1, Node head2) throws Exception ;

    /**
     * 递归实现
     * 合并两个有序单链表，之后依然有序
     *
     * @return
     */
    public Node mergeListRecursion(Node head1, Node head2) throws Exception ;

    /**
     * 单链表反转
     * @return
     */
    public Node reverseList();

    /**
     * 单链表反转
     * @return
     */
    public Node reverseList1();

    /**
     * 单链表反转
     * @return
     */
    public Node reverseList2();

    /**
     * 单链表递归反转
     * @return
     */
    Node reverseList3(Node head);

    /**
     * 单链表递归反转
     * @return
     */
    Node reverseList4(Node head);

    /**
     * 倒序打印链接
     * @param node
     */
     void reversePrint1(Node node);

    /**
     * 递归倒序打印
     * @param node
     */
    void reversePrint2(Node node);

    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    boolean hasCycle(Node head);

    /**
     * 获取环的长度
     * @param head
     * @return
     */
    int getCycleLength(Node head);

    /**
     * 获取环的起点
     * @param head
     * @return
     */
    Node cycleStartNode(Node head);

    /**
     * 获取环的起点
     * @param head
     * @return
     */
    Node entryLoop(Node head);

    boolean contains(Object object);

    void display();

    void display2();

}
