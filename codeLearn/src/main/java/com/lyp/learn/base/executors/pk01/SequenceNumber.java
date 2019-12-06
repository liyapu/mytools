package com.lyp.learn.base.executors.pk01;

public class SequenceNumber {
    // 定义匿名子类创建ThreadLocal的变量
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        // 覆盖初始化方法
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    // 下一个序列号
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}
