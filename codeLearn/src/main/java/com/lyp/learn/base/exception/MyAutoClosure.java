package com.lyp.learn.base.exception;


public class MyAutoClosure implements AutoCloseable {
    @Override
    public void close() throws Exception{
        System.out.println("MyAutoClosure closed!");
        throw new RuntimeException("throw Excpetion");
    }

    public void doSomething(){
        System.out.println("Do something!");
    }

    public static void main(String[] args) throws Exception{
        try( Another another = new Another();
             MyAutoClosure mac = new MyAutoClosure()
        ){
            mac.doSomething();
        }catch(Exception e){
            System.out.println();
            System.out.println("exe catch");
        }finally{
            System.out.println("exe finally");
        }
    }
}

class Another implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("Another closed!");
    }
}
