package com.lyp.learn.fenzhi;

/**
 *   汉诺塔的传说
 *   汉诺塔:汉诺塔(又称河内塔)问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，
 *   在一根柱子上从下往上按照大小顺序摞着 64 片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小 顺序重新摆放在另一根柱子上。
 *   并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘
 *
 *
 */
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
            move(1,A,C);
        }else{
            hanoi(n-1,A,C,B); //递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            move(n,A,C);//把编号为n的圆盘从A塔上移到C上
            hanoi(n-1,B,A,C);//递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
        }
    }

    //移动操作，将编号为n的圆盘，从A移动到C
    private static void move(int n,char A, char C) {
        System.out.println("第 " + (++times) + " 次移动:  " + n + " 号圆盘，" + A + " ->" + C);
    }

    public static void main(String[] args) {
//        hanoi(3,'A','B','C');
//        System.out.println();

//        hanoi(4,'A','B','C');
//        System.out.println();

        System.out.println();
        hanoi(5,'A','B','C');
    }
}
