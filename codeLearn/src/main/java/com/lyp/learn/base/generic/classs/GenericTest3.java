package com.lyp.learn.base.generic.classs;

public class GenericTest3 {
    public static void main(String[] args) {
        Generic<Number> gNumber = new Generic<Number>(456);
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<String> gString = new Generic<>("aaa");

        showKeyValue(gNumber);

        // showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
        // cannot be applied to Generic<java.lang.Number>
        //showKeyValue(gInteger);
        //Generic<java.lang.Number>) cannot be applied to (Generic<java.lang.String>)
        //showKeyValue(gString);
    }

    public static void showKeyValue(Generic<Number> obj){
        System.out.println("泛型测试 key value is " + obj.getKey());
    }
}
