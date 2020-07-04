package com.lyp.learn.queue.linkedqueue;

public interface MyQueue {

    /**
     * 清空队列
     * 将一个已经存在的对列置成空队列
     */
    void clear();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取队列长度
     * @return
     */
    int length();

    /**
     * 读取队首元素，并返回其值
     * @return
     */
    Object peek();

    /**
     * 将数据元素 object 插入到队列中
     * 使其成为新的队尾元素
     * @param object
     * @throws Exception
     */
    void offer(Object object) throws Exception;

    /**
     * 删除队首元素并返回其值
     * 若队列为空，则返回null
     * @return
     */
    Object poll();

    /**
     * 输出所有队列
     */
    void display();
}
