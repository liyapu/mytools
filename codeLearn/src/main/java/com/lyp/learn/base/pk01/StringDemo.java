package com.lyp.learn.base.pk01;


public class StringDemo {
    public static void main(String[] args) {
        String str = "abcaabe fa  gMBcabca";
        System.out.println("str : " + str);
        //返回字符串的长度
        System.out.println("str.length() : " + str.length());
        //返回给定data type类型x参数的字符串表示形式
        System.out.println("String.valueOf(100) :" + String.valueOf(100));
        //测试此字符串是否以指定的前缀开始
        System.out.println("str.startsWith(ab) :" + str.startsWith("ab"));
        //测试此字符串从指定索引开始的子字符串是否以指定前缀开始
        System.out.println("str.startsWith(c,2) :" + str.startsWith("c",2));
        //测试此字符串是否以指定的后缀结束
        System.out.println("str.endsWith(Aa) : " + str.endsWith("Aa"));
        //使用默认语言环境的规则将此字符串中所有字符转换成小写后的新串
        System.out.println("str.toLowerCase() :" + str.toLowerCase());
        //使用默认语言环境的规则将此字符串中所有字符转换成大写后的新串
        System.out.println("str.toUpperCase() :" + str.toUpperCase());
        //返回一个新的字符串，它是此字符串的一个子字符串
        //字符串 str 中字符的索引从0开始，范围为 0 到 str.length()-1
        System.out.println("str.substring(2) : " + str.substring(2));
        //返回一个新字符串，它是此字符串的一个子字符串
        // 使用 substring(beginIndex , endIndex) 进行字符串截取时，包括 beginIndex 位置的字符，不包括 endIndex 位置的字符
        System.out.println("str.substring(2,5) : " + str.substring(2,5));
        //返回指定索引处的 char 值
        System.out.println("str.charAt(1) : " + str.charAt(1));
        System.out.println("str.charAt(2) : " + str.charAt(2));
        //返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的
        System.out.println("str.replace('a','A') :" + str.replace('a','A'));
        System.out.println("str.replace(\"a\",\"A\") :" + str.replace("a","A"));
        System.out.println("str.replace(\"ca\",\"CA\"): " + str.replace("ca","CA"));
        //使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串
        System.out.println("str.replaceAll(\"a\",\"A\") :" + str.replaceAll("a","A"));
        //使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
        System.out.println("str.replaceFirst(\"a\",\"A\") : " + str.replaceFirst("a","A"));
        System.out.println("str.contains(\"aa\") :" + str.contains("aa"));
        //将指定字符串连接到此字符串的结尾
        System.out.println("str.concat(\"PinJie\"):" + str.concat("PinJie"));
        //使用'+'操作符来连接字符串
        //当将一个字符串与一个非字符串的值进行拼接时，后者被转换成字符串（
        System.out.println("str + \"PinjIe\" : " + str + "PinjIe");
        System.out.println("str + 56 : " + str + 56);
        //返回指定字符在此字符串中第一次出现处的索引
        //使用 indexOf 进行字符或字符串查找时，如果匹配返回位置索引；如果没有匹配结果，返回 -1
        System.out.println("str.indexOf(98):" + str.indexOf(98));
        //返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索
        System.out.println("str.indexOf(98,3) :" + str.indexOf(98,3));
        System.out.println("str.indexOf(98,20) :" + str.indexOf(98,20));
        // 返回指定子字符串在此字符串中第一次出现处的索引
        System.out.println("str.indexOf(\"b\") :" + str.indexOf("b"));
        System.out.println("str.indexOf(\"aa\") :" + str.indexOf("aa"));
        //返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
        System.out.println("str.indexOf(\"aa\",10) :" + str.indexOf("aa",10));
        //返回指定字符在此字符串中最后一次出现处的索引
        System.out.println("str.lastIndexOf(99):" + str.lastIndexOf(99));
        //返回指定字符在此字符串中最后一次出现处的索引，从指定的索引处开始进行反向搜索
        System.out.println("str.lastIndexOf(99,10): " + str.lastIndexOf(99,10));
        //返回指定子字符串在此字符串中最右边出现处的索引
        System.out.println("str.lastIndexOf(\"M\") :" + str.lastIndexOf("M"));
        // 返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索
        System.out.println("str.lastIndexOf(\"M\",10) :" + str.lastIndexOf("M",10));
        //使用分隔符|，拼接字符串
        System.out.println("String.join(|,a,b,2,3) : " + String.join("|","a","b","2","3"));
        System.out.println();

        String strr = "aBc";
        System.out.println("strr : " + strr);
        //将此字符串与指定的对象比较
        System.out.println("strr.equals(abc): " + "abc".equals(strr));
        //将此 String 与另一个 String 比较，不考虑大小写
        System.out.println("strr.equalsIgnoreCase(abc) : " + "abc".equalsIgnoreCase(strr));
        //int compareTo(String other)
        //按照字典顺序
        // 如果字符串位于 other 之前， 返回一个负数；
        // 如果字符串位于 other 之后，返回一个正数；
        // 如果两个字符串相等，返回 0
        System.out.println("strr.compareTo(abc) : " + strr.compareTo("abc"));
        //按字典顺序比较两个字符串，不考虑大小写
        System.out.println("strr.compareToIgnoreCase(abc) :" + strr.compareToIgnoreCase("abc"));
        //告知此字符串是否匹配给定的正则表达式
        System.out.println(strr.matches("\\w+"));
        System.out.println();

        //PrintStream 的 printf 格式化字符串
        System.out.printf("浮点型 %f, 整型变量 %d, 字符串 %s", 6.6F, 100, "test");
        System.out.println();
        //String 的 format 静态方法格式化字符串
        System.out.println(String.format("浮点型 %f, 整型变量 %d, 字符串 %s", 6.6F, 100, "test"));

        String strBlank = "     aa   bb  c  ";
        System.out.println("strBlank :" + strBlank);
        //返回字符串的副本，忽略前导空白和尾部空白
        System.out.println("strBlank.trim() :" + strBlank.trim());

        //根据给定正则表达式的匹配拆分此字符串
        //用String[] split(String regex, int limit)进行字符串拆分时，
        // 如果按字符串没有的字符分隔则不进行分隔，即返回长度为1的数组内容就是原来的字符串
        String [] strArr = strBlank.split("");
        System.out.println("strArr.length :" + strArr.length);
        for(String s : strArr){
            System.out.println(s);
        }
        System.out.println("----------");

        String [] strArrr = strBlank.split("\\s+");
        System.out.println("strArrr.length :" + strArrr.length);
        for(String s : strArrr){
            System.out.println(s);
        }
        System.out.println("---------");

        String [] strArrrr = strBlank.split("\\s*");
        System.out.println("strArrrr.length : " + strArrrr.length);
        for(String s : strArrrr){
            System.out.println(s);
        }
        System.out.println("----------------");

        String strArrdemo = " ab Ca   a";
        // 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中
        byte[] byteArr = strArrdemo.getBytes();
        for(byte b : byteArr){
            System.out.println(b);
        }
        System.out.println();

        //将此字符串转换为一个新的字符数组
        char [] charArr = strArrdemo.toCharArray();
        for(char c : charArr){
            System.out.println(c);
        }
        System.out.println();
        System.out.println("=======================");
        /**
         * intern方法使用：一个初始为空的字符串池，它由类String独自维护
         * 当调用 intern方法时，如果池已经包含一个等于此String对象的字符串（用equals(oject)方法确定），则返回池中的字符串
         * 否则，将此String对象添加到池中，并返回此String对象的引用
         *
         * 它遵循以下规则：对于任意两个字符串 s 和 t，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true
         *
         * String.intern();
         * 再补充介绍一点：存在于.class文件中的常量池，在运行期间被jvm装载，并且可以扩充
         * String的intern()方法就是扩充常量池的一个方法；
         * 当一个String实例str调用intern()方法时，java查找常量池中是否有相同unicode的字符串常量，
         * 如果有，则返回其引用，
         * 如果没有，则在常量池中增加一个unicode等于str的字符串并返回它的引用
         */
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = new String("kvill");
        System.out.println("===========test11============");
        System.out.println( s0 == s1 ); //false
        System.out.println( "**********" );
        s1.intern(); //虽然执行了s1.intern(),但它的返回值没有赋给s1
        s2 = s2.intern(); //把常量池中"kvill"的引用赋给s2
        System.out.println( s0 == s1); //flase
        System.out.println( s0 == s1.intern() ); //true//说明s1.intern()返回的是常量池中"kvill"的引用
        System.out.println( s0 == s2 ); //true
    }
}
