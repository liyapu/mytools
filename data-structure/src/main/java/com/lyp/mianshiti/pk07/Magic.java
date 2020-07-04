package com.lyp.mianshiti.pk07;

public class Magic {

    public static void main(String[] args) {
        magic(3);
        System.out.println("-----------------------");
        magic(4);
    }

    public static void magic(int n){
        int [][] arr = new int [n][n];
        int i = 0;
        int j = n/2; //第一个数字，放在第1行中间
        int end = n*n;
        for(int k = 1; k <= end; k++){
            arr[i][j] = k;             //当前位置取值
            if(k % n == 0){            //下一个位置已经有数字
                i = (i + 1 + n) % n;  //下一个位置在下一行
            }else{
                i = (i - 1 + n) % n;  //下一个数字在上一行
                j = (j + 1) % n;      //下一个数字在下一列
            }
        }

        //输出二维矩阵
        for(i = 0; i < arr.length; i++){
            for(j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
