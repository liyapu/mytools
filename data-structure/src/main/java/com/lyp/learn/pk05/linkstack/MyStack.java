package com.lyp.learn.pk05.linkstack;

public interface MyStack {
    /**
     * 栈置空操作
     * 将一个已经存在的栈置为空战
     */
    void clear();

    /**
     * 判断栈空操作
     * 判断一个栈是否为空，若为空，返回true;否则返回false
     * @return
     */
    boolean isEmpty();

    /**
     * 求栈中数据元素的个数
     * @return
     */
    int length();

    /**
     * 读取栈中元素并返回其值
     * 若栈为空，则返回null
     * @return
     */
    Object peek();

    /**
     * 入栈操作
     * 将数据元素压入栈顶
     * @param object
     * @throws Exception
     */
    void push(Object object) throws Exception;

    /**
     * 出栈操作
     * 删除并返回栈顶元素
     * @return
     */
     Object pop();

    /**
     * 展示栈中元素
     * 栈顶 ---> 栈底
     */
    void displayAsc();

    /**
     * 展示栈中元素
     * 栈底 ---> 栈顶
     */
    void displayDesc();
}
