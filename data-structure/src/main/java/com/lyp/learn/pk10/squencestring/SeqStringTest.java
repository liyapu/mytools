package com.lyp.learn.pk10.squencestring;

public class SeqStringTest {
    public static void main(String[] args) {
        IString str1 = new SeqString("abcdefgdeabca");
        System.out.println(str1.isEmpty());
        System.out.println(str1.length());

        try {

            IString str2 = str1.concat(new SeqString("opk"));
            str1.display();
            str2.display();


            System.out.println();
            System.out.println(str1.index(new SeqString("f"),0));
            System.out.println(str1.index(new SeqString("dea"),0));
            System.out.println(str1.index(new SeqString("aagd"),0));
            System.out.println();

            System.out.println(str1.charAt(2));
            System.out.println(str1.length());
            IString str3 = str1.insert(2,new SeqString("uuu"));
            str1.display();
            str3.display();
            System.out.println();

            System.out.println(str1.compareTo(str2));
            System.out.println(str1.compareTo(str3));
            System.out.println(str1.compareTo(new SeqString("abcdefgdeabca")));

            IString str4 = str1.delete(2,5);
            str1.display();
            str4.display();

            System.out.println("-------------");
            System.out.println(str4.length());
            System.out.println(str4.isEmpty());
            str4.clear();
            System.out.println(str4.length());
            System.out.println(str4.isEmpty());

            System.out.println("------------");
            str2.display();
            IString str5 = str2.subString(2,6);
            System.out.println(str5.length());
            str5.display();

            System.out.println("--------------------");
            IString str6 = new SeqString("ababcabdabcabca");
            System.out.println(str6.index(new SeqString("abcabc"),0));
            System.out.println(str6.index(new SeqString("cabca"),0));
            System.out.println();

            System.out.println(str6.index_BF(new SeqString("abcabc"),0));
            System.out.println(str6.index_BF(new SeqString("cabca"),0));
            System.out.println();

            System.out.println(str6.index_KMP(new SeqString("abcabc"),0));
            System.out.println(str6.index_KMP(new SeqString("cabca"),0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
