package com.lyp.learn.pk06.queuealgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrimeNum4 {

    public static void main(String[] args) throws Exception {
        makePrimeRing(8);
    }

    public static List<Integer> makePrimeRing(int n) throws Exception {
        //求n个正整数的素数环，并以顺序表返回
        if (n % 2 != 0) {//n为奇数则素数环不存在
            throw new Exception("素数环不存在");
        }
        List<Integer> L = new LinkedList<>();
        L.add(0, 1);//初始化顺序表的首结点为1
        Queue Q = new LinkedList();
        for (int i = 2; i <= n; i++) {
            Q.offer(i);//初始化队列
        }
        return insertRing(L, Q, 2, n);
    }

    public static List<Integer> insertRing(List<Integer> list, Queue<Integer> queue, int index, int total) throws Exception {
        // 在一个顺序表L中插入第index个数，使之与顺序表中第index-1个数的和为素数，
        // 若index等于total，则还需满足第index个数与1的和也为素数，程序返回顺序表
        int count = 0;// 记录遍历队列中数据元素的个数，防止在一次循环中重复遍历
        while (!queue.isEmpty() && count <= total - index) {
            int p = queue.poll();// 取队列第一个元素
            int q = list.get(list.size() - 1);// 取顺序表最后一个元素
            if (index == total) {// 如果是最后一个元素
                if (isPrime(p + q) && isPrime(p + 1)) {// 而且与顺序表最后一个元素，1都符合
                    list.add(list.size(), p);// 插入到顺序表表尾
                    printList(list); //打印
                    list.remove(list.size() - 1); //从列表里移除此元素
                    queue.offer(p); //复入队列
                } else {
                    queue.offer(p);
                }
            } else if (isPrime(p + q)) {// 如果未遍历到最后一个元素
                list.add(list.size(), p);
                if (insertRing(list, queue, index + 1, total) != null) {// 递归
                    printList(list);
                }
                list.remove(list.size() - 1);// 移除顺序表表尾数据元素
                queue.offer(p);
            } else {
                queue.offer(p);
            }
            ++count;
        }
        return null;
    }

    /**
     * 判断是否是素数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        // 判断是否为素数
        if (num == 1) {
            return false;
        }
        Double n = Math.sqrt(num);// 减小运算量
        for (int i = 2; i <= n.intValue(); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    private static void printList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

}
