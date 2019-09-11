package com.lyp.learn.dp.pattern.iteratorpattern;

/**
 * 具体迭代器
 */
public class ConcreteIterator implements Iterator {
    private int index = 0;

    private int size = 0;

    private ConcreteAggregate concreteAggregate;

    public ConcreteIterator(ConcreteAggregate concreteAggregate){
        this.concreteAggregate = concreteAggregate;
        this.index = 0;
        this.size = concreteAggregate.size();
    }

    //返回下一个元素
    @Override
    public Object next() {
        if(hasNext()){
            return concreteAggregate.getElement(index++);
        }
        return null;
    }

    //是否还有一个元素，即还没有遍历结束
    @Override
    public boolean hasNext() {
        return index < size;
    }
}
