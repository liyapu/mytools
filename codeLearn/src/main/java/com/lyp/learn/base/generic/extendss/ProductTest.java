package com.lyp.learn.base.generic.extendss;

public class ProductTest {

    public static void main(String[] args) {
        Product<Integer> product1 = new Product<Integer>(2222);
        Product<Float> product2 = new Product<Float>(2.4f);
        Product<Double> product3 = new Product<Double>(2.56);
        //下一行编译错误
        //Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number'
       // Product<String> product4 = new Product<String>("11111");


        showKeyValue1(product1);
        showKeyValue1(product2);
        showKeyValue1(product3);
    }


    public static void showKeyValue1(Product<? extends Number> obj){
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

}
