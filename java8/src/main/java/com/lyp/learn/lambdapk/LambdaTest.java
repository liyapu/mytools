package com.lyp.learn.lambdapk;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;


/**
 * 一、Lambda 表达式的基础语法：
 *  Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 	箭头操作符将 Lambda 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 *
 *  如何理解函数式接口
 *      Java从诞生日起就是一直倡导“一切皆对象”，在java里面面向对象(OOP)编程是一切。
 *      但是随着python、scala等语言的兴起和新技术的挑战，java不得不做出调整以便支持更加广泛的技术要求，
 *      也即java不但可以支持OOP还可以支持OOF（面向函数编程）
 *
 *      在函数式编程语言当中，函数被当做一等公民对待。在将函数作为一等公民的编程语言中，Lambda表达式的类型是函数。
 *      但是在Java8中，有所不同。在Java8中，Lambda表达式是对象，而不是函数，它们必须依附于一类特别的对象类型——函数式接口。
 *
 *      简单的说，在Java8中，Lambda表达式就是一个函数式接口的实例。这就是Lambda表达式和函数式接口的关系。
 *      也就是说，只要一个对象是函数式接口的实例，那么该对象就可以用Lambda表达式来表示。
 *      所以以前用匿名内部类表示的现在都可以用Lambda表达式来写。
 */
public class LambdaTest {

    /**
     * 匿名内部类
     */
    @Test
    public void testNiMing(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return  Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * 使用lambda
     * (x,y) 参数列表
     * -> 操作符
     * Integer.compare(x,y) 方法实现
     */
    @Test
    public void testLambda1(){

        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    @Test
    public void testConsumer1(){
        Consumer com = (x) -> System.out.println(x);
        com.accept("consumer 测试");

        Consumer com2 = x -> System.out.println(x);
        com2.accept("consumer 2");

        Consumer<String> com3 = x -> System.out.println(x);
        com3.accept("consumer 3");
    }

    /**
     * 多个参数，Lambda体中多条语句,Lambda必须放在 {} 中
     */
    @Test
    public void testManyParams(){
        Comparator<Integer> comparator = (x,y) ->{
            System.out.println("多条语句");
            return Integer.compare(x,y);
        };

        System.out.println(comparator.compare(3,5));
    }

    /**
     * 只有一条语句，{}与return 都可以不写
     */
    @Test
    public void testOne(){
        Comparator<Integer> comparator = (x,y)->Integer.compare(x,y);
        System.out.println(comparator.compare(5,2));
    }

    @Test
    public void testParam(){
        //原来使用匿名内部类作为参数传递
        TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });

        ts.add("bb");
        ts.add("kkkk");
        ts.add("ppppppp");
        ts.add("ccc");
        ts.add("aaaaaaaaaaaa");
        ts.add("zz");
        ts.add("a");
        ts.add("b");
        ts.add("c");
        ts.add("bbbbb");

        ts.stream().forEach(System.out::println);
        System.out.println("--------------");

        //lambda 表达式作为参数
        TreeSet<String> ts2 = new TreeSet<>(Comparator.comparingInt(String::length));
        ts2.add("bb");
        ts2.add("kkkk");
        ts2.add("ppppppp");
        ts2.add("ccc");
        ts2.add("aaaaaaaaaaaa");
        ts2.add("zz");
        ts2.add("a");
        ts2.add("b");
        ts2.add("c");
        ts2.add("bbbbb");
        ts2.stream().forEach(System.out::println);
    }

}
