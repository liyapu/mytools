package com.lyp.learn.base.exception;

public class MyAutoClosure2 implements AutoCloseable{
    @Override
    public void close() throws Exception{
        System.out.println("MyAutoClosure closed!");
    }

    public void doSomething(){
        System.out.println("Do something!");
    }

    public static void main(String[] args) throws Exception{
        try(
                MyAutoClosure mac = new MyAutoClosure()
        ){
            mac.doSomething();
        }catch(Exception e){
            System.out.println("exe catch");
        }finally{
            System.out.println("exe finally");
        }
    }
}
