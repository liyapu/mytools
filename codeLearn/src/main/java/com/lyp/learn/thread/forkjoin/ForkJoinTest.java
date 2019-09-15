package com.lyp.learn.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long start = 0;
        long end = 1000000000;
        ForkJoinSumCalculate forkJoinSumCalculate = new ForkJoinSumCalculate(start,end);

        Long result = pool.invoke(forkJoinSumCalculate);
        System.out.println(result);

        System.out.println("-------验证一下结果--------");
        long sum = 0;
        for(long i = start; i <= end; i++){
            sum += i;
        }
        System.out.println(sum);
    }
}

/**
 * RecursiveTask 可以有返回值
 */
class ForkJoinSumCalculate extends RecursiveTask<Long>{
    private long start;
    private long end;
    private final long THRESHOLD = 10000; //阈值

    public  ForkJoinSumCalculate(long start,long end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){
            System.out.println(Thread.currentThread().getName() + " 开始执行：" + start + "-" + end);
            long sum = 0;
            for(long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end)/2;

            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,middle);
            left.fork(); //fork是拆分的意思

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1,end); //这里middle 加1
            right.fork();

            return  left.join() + right.join(); //join 合并分支结果
        }
    }
}