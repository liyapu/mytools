package com.lyp.learn.dp.pattern.builderpattern1;

public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        Product product = director.build();
        System.out.println(product);
    }
}
