package com.lyp.learn.stack.linkstackalgorithm;

public class HanoiTest {
    //全局变量，对搬动次数计数
    private static int times = 0;

    /**
     * 将塔座A上按直径由小到大且自上而下的编号为1至n的n个圆盘按规则移到塔座C上，
     * @param n : 源塔座A上的移动的圆盘
     * @param A : 源塔座
     * @param B : 辅助塔座
     * @param C : 目标塔座
     */
    public static void  hanoi(int n,char A,char B,char C){
        if(n == 1){
            move(A,1,C);
        }else{
            hanoi(n-1,A,C,B); //递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            move(A,n,C);//把A塔上编号为n的圆盘移到C上
            hanoi(n-1,B,A,C);//递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
        }
    }

    //移动操作，将编号为n的圆盘，从A移动到C
    private static void move(char A, int n, char C) {
        System.out.println("第 " + (++times) + " 次移动:  " + n + " 号圆盘，" + A + " ->" + C);
    }

    public static void main(String[] args) {
        hanoi(3,'A','B','C');
    }
}
