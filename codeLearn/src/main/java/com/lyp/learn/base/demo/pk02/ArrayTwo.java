package com.lyp.learn.base.demo.pk02;

public class ArrayTwo {
    public static void main(String[] args) {
       int [] [] arrayInt2 = new int [3][3];
       for(int i = 0 ;i < arrayInt2.length; i++){
           for(int j = 0; j < arrayInt2[i].length; j++){
               arrayInt2[i][j] = i + i*j + j + 1;
           }
       }
        System.out.println("----输出arrayInt2-----");
        printTwoArray(arrayInt2);

       int [] [] arrayInt3 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("----输出arrayInt3-----");
        printTwoArray(arrayInt3);

    }
    public static void printTwoArray(int [] [] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++ ){
                System.out.print(array[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
