package com.lyp.learn.pk06.queuealgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrimeNum3 {
    private static int n = 6;
    //存放素数环的
    private static List<Integer> list = new ArrayList<>();
    //存放待放入的数字的
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        /**
         * n必须是偶数才可以
         * n 为 奇数，则必有两个素数相邻，两个素数之和为合数
         */
        if(n % 2 != 0){
            System.out.println("对于 " + n + "没有素数环！");
            return;
        }
        list.add(1);
        for(int i = 2; i <= n; i++){
            queue.offer(i);
        }

        insertRing();

    }

    /**
     * 插数入环
     */
    public static void insertRing(){
        if(queue.isEmpty()){
            printList(list);
        }else{
            //用于中断queue中有值，但是无法组成素数环时的情况
            int count = 0;
            while(!queue.isEmpty() && count < queue.size()){
               int lnum = list.get(list.size()-1);
               int qnum = queue.poll();
               if(queue.isEmpty()){//插入素数环中的最后一个数字
                   if(isPrime(lnum+qnum) && isPrime(list.get(0) + qnum)){
                       list.add(qnum);
                       insertRing();
                       list.remove(new Integer(qnum)); //用Integer包装一下，删除对象。不然会当成index下标删除
                       queue.offer(qnum);
                       break;
                   }else{
                       queue.offer(qnum);
                   }
               }else{
                   if(isPrime(lnum+qnum)){
                       list.add(qnum);
                       insertRing();
                       list.remove(new Integer(qnum));
                       queue.offer(qnum);
                   }else{
                       queue.offer(qnum);
                   }
               }
               count++;
            }
        }
    }

    /**
     * 判断是否是素数
     * @param num
     * @return
     */
    private static boolean isPrime(int num){
        boolean isPrimeFlag = true;
        int temp = (int)Math.sqrt(num);
        for(int i = 2; i <= temp; i++){
            if(num % i == 0){
                isPrimeFlag = false;
                break;
            }
        }
        return isPrimeFlag;
    }


    private static void printList(List list){
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }


}
