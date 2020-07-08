package com.lyp.learn.sort;

public class ShellSort {

    public static void printArray(int [] array){
        int length = array.length;
        for(int i = 0; i < length; i++){
            if(i != 0){
                System.out.print(" , ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    /**
     * 希尔排序
     * @param array
     * @return
     */
    public static int [] shellSort(int [] array){
        int length = array.length;
        if(length == 0){
            return array;
        }
        //gap控制两数之间的间隔
        int temp,gap = length /2;
        while (gap > 0){
            for(int i = gap ; i < length; i++){
                //每次遍历开始下标位置的值
                temp = array[i];
                //开始第一次为0
                int preIndex = i - gap;
                //preIndex >= 0;防止数组越界
                //preIndex >= 0 时, array[preIndex] 不大于 temp 时，停止遍历
                // array[preIndex] 之前，间隔gap的数，都是有序的
                while (preIndex >= 0 && array[preIndex] > temp){
                    //preIndex + gap 与 preIndex 间隔 gap个数，此两数相邻
                    array[preIndex + gap] = array[preIndex];
                    //preIndex - gap ,向前推进
                    preIndex -= gap;
                }
                //到此表示给 temp 找到了插入了位置
                //temp原位置不动的设置一次 或者是 把temp设置到前面的位置
                // while 循环中  preIndex 减去了 gap ,此处要加回来
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = new int [] {8,9,1,7,2,3,5,4,6,0};
        printArray(array);
        array = shellSort(array);
        printArray(array);
    }
}
