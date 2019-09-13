package com.lyp.learn.base.exception;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        System.out.println("main method 开始------");
        new ExceptionDemo2().test();
        System.out.println("main method 结束------");
    }

    public void test(){
        System.out.println("test method 开始....");
        try {
            divideZero();
            //除0异常，导致下面的这句不会执行
            System.out.println("结束 1 ........");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println("-------------------------");
            e.printStackTrace();
            System.out.println("=========================");
        }catch (Exception e){
            //除0异常被 catch ArithmeticException 捕获，下面这句不会执行
            System.out.println("Exception ......");
        }finally {
            //finally 块中的代码，一定会被执行
            System.out.println("finally.............");
        }
        System.out.println("异常处理完成之后，从此处开始执行 ........");        System.out.println("test method 结束....");
    }

    public void divideZero(){
        int a = 10;
        int b = 0;
        int result = a / b;
        System.out.println("result is :" + result);
    }


}
