package com.lyp.learn;


import org.junit.jupiter.api.Test;

/**
 * java 13新特性：TextBlock （预览）
 *
 *   在Java中，通常需要使用String类型表达HTML，XML，SQL或JSON等格式的字符串，
 *   在进行字符串赋值时需要进行 转义和连接操作，然后才能编译该代码，
 *   这种表达方式难以阅读并且难以维护。
 *
 *   文本块就是指多行字符串，
 *   例如一段格式化后的xml、json等。而有了文本块以后，用户不需要转义，Java能自动搞定。
 *   因此，文本块将提高Java程序的可读性和可写性。
 *
 *   目标
 *     简化跨越多行的字符串，避免对换行等特殊字符进行转义，简化编写Java程序。
 *     增强Java程序中字符串的可读性。
 *
 *  基本使用
 *    文本块是Java语言中的一种新文字。它可以用来表示任何字符串，并且提供更大的表现力和更少的复杂性。
 *    文本块由零个或多个字符组成，由开始和结束分隔符括起来。
 *
 *    开始分隔符是由三个双引号字符(""")，后面可以跟零个或多个空格，最终以行终止符结束。
 *    文本块内容 以开始分隔符的行终止符后的第一个字符开始。
 *
 *    结束分隔符也是由三个双引号字符(""")表示，文本块内容以结束分隔符的第一个双引号之前的最后一个 字符结束。
 *
 *    文本块中的内容可以直接使用"，"，但不是必需的。
 *    文本块中的内容可以直接包括行终止符。
 *    允许在文本块中使用 \n，但不是必需的。
 *
 *    在运行时，文本块将被实例化为String的实例，就像字符串一样。
 *    从文本块派生的String实例与从字符串派生的实例 是无法区分的。
 *    具有相同内容的两个文本块将引用相同的String实例，就像字符串一样。
 *
 *    请注意，在文本块内自由使用 " 是合法的
 *    但是，三个"字符的序列需要进行转义至少一个"以避免模仿结束分隔符
 *
 */
public class TextBlockTest {

    @Test
    public void test1(){
        String html = "<html>\n" +
                "  <body>\n" +
                "      <p>Hello, 尚硅谷</p>\n" +
                "  </body>\n" +
                "</html>";
        System.out.println(html);

        System.out.println();

        String html1 = """
<html>
  <body>
      <p>Hello, 尚硅谷</p>
  </body>
</html>
""";
        System.out.println(html1);
    }

    @Test
    public void test2(){
        String sql = "select employee_id,last_name,salary,department_id\n" +
                "from employees\n" +
                "where department_id in (40,50,60)\n" +
                "order by department_id asc";

        System.out.println(sql);
        String sql1 = """
select employee_id,last_name,salary,department_id
from employees
where department_id in (40,50,60)
order by department_id asc
""";
        System.out.println(sql1);
    }

    //关于TextBlock的基本使用
    @Test
    public void test3(){
        //以开始分隔符的行终止符后的第一个字符开始
        //以结束分隔符的第一个双引号之前的最后一个字符结束
        String text1 = """
abc""";
        String text2 = "abc";
        System.out.println(text1 == text2);//text1和text2都指向了字符串常量池中唯一定义的abc字面量

        String text3 = """
abc
""";
        System.out.println(text1.length());//3
        System.out.println(text3.length());//4
    }

    //空字符串的表示
    @Test
    public void test4(){
        String text1 = "";
        System.out.println(text1.length());

        String text2 = """
""";
        System.out.println(text2.length());
    }

    //错误的写法
    @Test
    public void text5(){
//        String a = """""";   // 开始分隔符后没有行终止符
//        String b = """ """;  // 开始分隔符后没有行终止符
//        String c = """
//           ";        // 没有结束分隔符
//String d = """
//        abc \ def
//        """;      // 含有未转义的反斜线（请参阅下面的转义处理）
//
//        String e = "abc \ def";
    }

    //编译器在编译时会删除掉这些多余的空格
    @Test
    public void test6(){
        String text1 = """
    abc   //此处结尾的空格，会无条件的删除
  // abc 前面的空格，是否删除，以及删除多少，以 结尾的三个逗号为依据
  """; // 结尾三个逗号为依据
        System.out.println(text1.length());//4
    }

    @Test
    public void test61(){
        String text1 = """
       abc
    """;
        System.out.println(text1.length());
    }

    @Test
    public void test62(){
        String text1 = """
       abc
""";
        System.out.println(text1.length());
    }

    // 转义字符
    @Test
    public void test7(){
        String html = """
              <html>
                  <body>\n
                      <p>Hello, world</p>\n
                  </body>\n
              </html>\n
              """;
        System.out.println(html);
    }

    //在文本块内自由使用"是合法的
    @Test
    public void test8(){
        String story = """
    "When I use a word," Humpty Dumpty said,
    in rather a scornful tone, "it means just what I
    choose it to mean - neither more nor less."
    "The question is," said Alice, "whether you
    can make words mean so many different things."
    "The question is," said Humpty Dumpty,
    "which is to be master - that's all."
""";
        System.out.println(story);

        String code =
                """
    String text = \"""
        A text block inside a text block
    \""";
    """;
        System.out.println(code);
    }

    //文本块连接
    @Test
    public void test9(){
        String type = "String";
        String code = """
              public void print(""" + type + """
               o) {
                  System.out.println(Objects.toString(o));
              }
              """;
        System.out.println(code);

        //改进：可读性更好  ---方式1
        String code1 = """
              public void print($type o) {
                  System.out.println(Objects.toString(o));
              }
              """.replace("$type", type);
        System.out.println(code1);

        //方式2
        String code2 = String.format("""
              public void print(%s o) {
                  System.out.println(Objects.toString(o));
              }
              """, type);
        System.out.println(code2);

        //方式3
        String code3 = """
                public void print(%s object) {
                    System.out.println(Objects.toString(object));
                }
                """.formatted(type);
        System.out.println(code3);
    }
}
