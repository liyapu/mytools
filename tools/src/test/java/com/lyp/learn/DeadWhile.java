package com.lyp.learn;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-25 13:11
 */
public class DeadWhile {

    public static void main(String[] args) {
        int num = 10 * 1;
        while (true){
            if(num >= 100){
                System.out.println(num);
            }
        }
    }
}
