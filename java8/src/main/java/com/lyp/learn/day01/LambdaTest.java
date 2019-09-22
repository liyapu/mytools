package com.lyp.learn.day01;

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

}
