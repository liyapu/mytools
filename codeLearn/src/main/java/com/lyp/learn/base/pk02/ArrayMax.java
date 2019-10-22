package com.lyp.learn.base.pk02;
public class ArrayMax {
    public static void main(String[] args) {
        int [] array = {6,2,9,3,1};
        int index = 0; //最大值索引，默认为0
        int max = array[index]; //最大值，初始化为数组的第一个值
        for(int i = 1 ; i < array.length; i++){
            if(array[i] > max){
                index = i;
                max = array[i];
            }
        }
        System.out.println("最大值是 " + max + " , 下标索引是 " + index);
    }
}
