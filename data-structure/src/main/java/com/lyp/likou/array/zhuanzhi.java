package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2020-09-08 17:10
 */
public class zhuanzhi {
    public static void main(String[] args) {
        int[][] A = {{1,2,3},{4,5,6},{7,8,9}};
        int rows = A.length;
        int columns = A[0].length;

        int [][] newArr = new int [columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newArr[j][i] = A[i][j];
            }
        }

        printArr(newArr);

    }

    public static void printArr(int [] [] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}
