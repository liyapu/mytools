package com.lyp.learn;

import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *    局部变量的类型推断  var
 *      var a = "hello"
 *      局部变量类型推断就是左边的类型直接使用 var 定义，而不用写具体的类型
 *      编译器能根据右边的表达式自动推断类型，如上面的 String
 *      等价于 String a = "hello"
 *
 *    类的属性的数据类型不可以使用 var, 仅可以使用在局部变量中，比如方法中
 *
 *
 */
public class VarTest {
    public static void main(String[] args) {
        var a = "aaa";
        var b = 10;
        System.out.println(a);
        System.out.println(b);

        a = "aaaaaaaaa";
        System.out.println(a);

        // a 是字符串类型，此处不能改变类型为 int
//        a  = 20;

        //这样也不行，因为没有给 c 赋值，无法推断
//        var c ;
    }

    /**
     *   在声明隐式类型的 lambda 表达式的形参时允许使用 var
     *   使用 var 的好处是在使用 lambda 表达式时给参数加上注解
     *   (@NonNull var x, @NonNull var y ) -> x.process(y)
     */
    @Test
    public void testVar1(){
        Thread t1 = new Thread(() -> System.out.println("线程1"));
        t1.start();

        Consumer<String> com1 = t -> System.out.println(t.toUpperCase());
        com1.accept("aaa");

        // 通过 var 给 t 添加上类型，推断而得 String
        Consumer<String> com2 = (var t) -> System.out.println(t.toUpperCase());
        com2.accept("bbb");

        Consumer<String> com3 = (@NonNull var t) -> System.out.println(t.toUpperCase());
        com3.accept("ccc");

        BiConsumer<String,String> bicom1 = (var x,var y) -> System.out.println(x.concat(y));
        bicom1.accept("aa","bb");

//        BiConsumer<String,String> bicom2 = (@NonNull  var x,@NonNull var y) -> System.out.println(x.concat(y));
//        bicom2.accept("aa",null);

    }

}



















