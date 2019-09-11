package com.lyp.learn.dp.pattern.iteratorpattern;

import java.util.Vector;

/**
 * 具体聚集
 */
public class ConcreteAggregate implements Aggregate {

    private Vector vector = new Vector();

    @Override
    public void add(Object obj) {
        vector.add(obj);
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int size(){
        return vector.size();
    }

    public Object getElement(int index){
        if(index < size()){
            return vector.get(index);
        }else{
            return null;
        }
    }
}
