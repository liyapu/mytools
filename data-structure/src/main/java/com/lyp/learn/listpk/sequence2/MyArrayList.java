package com.lyp.learn.listpk.sequence2;

/**
 * 顺序列表实现
 * 底层使用 数组存储
 */
public class MyArrayList<T> implements MyList<T> {

    //默认底层数组长度
    private static final int DEFAULT_LENGTH = 16;
    //当前存储元素长度
    private int curLength;

    Object [] objArray = null;

    public MyArrayList(){
        curLength = 0;
        objArray = new Object[DEFAULT_LENGTH];
    }

    public MyArrayList(int length) throws Exception {
        if(length <= 0){
            throw new Exception("初始化长度不合法 length =" + length);
        }
        curLength = 0;
        objArray = new Object[length];
    }


    @Override
    public void clear() {
        for(int i = 0 ; i < curLength; i++){
            objArray[i] = null;
        }
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
    public T get(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw new Exception("get 元素，下标不合法 i = " + i);
        }
        return (T)objArray[i];
    }

    @Override
    public void add(T t) throws Exception {
        insert(curLength,t);
    }

    @Override
    public void insert(int i, T t) throws Exception {
        if(i < 0 || i > curLength){
            throw  new Exception("insert 元素，下标不合法 i = " + i);
        }
        if(curLength == objArray.length){
            enlargeCapacity();
        }
        for(int j = curLength; j > i; j--){
            objArray[j] = objArray[j-1];
        }
        objArray[i] = t;
        curLength++;
    }

    /**
     * 扩充数组容量
     */
    private void enlargeCapacity(){
        int newLength = objArray.length + objArray.length/2;
        Object [] newObjArray = new Object[newLength];
        for(int i = 0 ; i < curLength; i++){
            newObjArray[i] = objArray[i];
        }
        objArray = newObjArray;
    }

    @Override
    public void set(int i, T t) throws Exception {
        if(i < 0 || i >= curLength){
            throw  new Exception("set 元素下标错误 i = " + i);
        }
        objArray[i] = t;
    }

    @Override
    public void remove(int i) throws Exception {
        if(i < 0 || i >= curLength){
            throw  new Exception("remove 元素下标错误 i = " + i);
        }
        for(int j = i;j < curLength-1; j++){
            objArray[j] = objArray[j+1];
        }
        curLength--;
    }

    @Override
    public void remove(T t) throws Exception {
        int index =indexOf(t);
        if(index >= 0){
            remove(index);
        }
    }


    @Override
    public int lastIndexOf(T t) {
        if(null == t){
            for(int i = curLength - 1; i >= 0; i--){
                if(objArray[i] == null){
                    return i;
                }
            }
        }else{
            for(int i = curLength - 1; i >= 0; i--){
                if(t.equals(objArray[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int indexOf(T t) {
        if(null == t){
            for(int i = 0; i < curLength; i++){
                if(objArray[i] == t){
                    return i;
                }
            }
        }else{
            for(int i = 0 ; i < curLength; i++ ){
                if(t.equals(objArray[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T t) {
        return indexOf(t) >= 0;
    }

    @Override
    public void display() {
        for(int i = 0; i < curLength; i++){
            System.out.print(objArray[i]);
            if(i != curLength-1){
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
