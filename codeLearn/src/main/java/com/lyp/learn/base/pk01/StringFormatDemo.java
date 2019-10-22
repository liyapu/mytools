package com.lyp.learn.base.pk01;

public class StringFormatDemo {
    public static void main(String[] args) {

        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3>7);
        System.out.printf("字符串是 %s %n","test");
        System.out.printf("整数是 %d %n",100);
        System.out.printf("浮点数是 %f %n",1234.5678F);
        System.out.printf("百分号是 %% %n");
        System.out.printf("31的16进制数是：%x %n", 31);
        System.out.printf("31的8进制数是：%o %n", 31);
        System.out.println("------------------");
        int i1 = 1234567;
        System.out.println(String.format("%3d",i1));
        System.out.println(String.format("%9d",i1));
        System.out.println(String.format("%1$3d",i1));
        System.out.println(String.format("%1$9d",i1));
        System.out.println(String.format("%+3d",i1));
        System.out.println(String.format("%+9d",i1));
        System.out.println(String.format("%+d",i1));
        System.out.println(String.format("%+d",i1));
        System.out.println(String.format("%03d",i1));
        System.out.println(String.format("%09d",i1));
        System.out.println(String.format("%,3d",i1));
        System.out.println(String.format("%,9d",i1));
        System.out.println(String.format("%-3d",i1));
        System.out.println(String.format("%-9d",i1));
        System.out.println(String.format("%(3d",i1));
        System.out.println(String.format("%(9d",i1));
        System.out.println(String.format("% 3d",i1));
        System.out.println(String.format("% 9d",i1));
        System.out.println("==============");
        int i2 = -1234567;
        System.out.println(String.format("%3d",i2));
        System.out.println(String.format("%9d",i2));
        System.out.println(String.format("%1$3d",i2));
        System.out.println(String.format("%1$9d",i2));
        System.out.println(String.format("%+3d",i2));
        System.out.println(String.format("%+9d",i2));
        System.out.println(String.format("%3d",i2));
        System.out.println(String.format("%9d",i2));
        System.out.println(String.format("%03d",i2));
        System.out.println(String.format("%09d",i2));
        System.out.println(String.format("%,3d",i2));
        System.out.println(String.format("%,9d",i2));
        System.out.println(String.format("%-3d",i2));
        System.out.println(String.format("%-9d",i2));
        System.out.println(String.format("%(3d",i2));
        System.out.println(String.format("%(9d",i2));
        System.out.println(String.format("% 3d",i2));
        System.out.println(String.format("% 9d",i2));
        System.out.println();
        float f1 = 12345.1234F;
        System.out.println(String.format("%f",f1));
        System.out.println(String.format("%,f",f1));
        System.out.println(String.format("%3f",f1));
        System.out.println(String.format("%10f",f1));
        System.out.println(String.format("%10.2f",f1));
        System.out.println(String.format("%10.10f",f1));
        System.out.println(String.format("%3e",f1));
        System.out.println(String.format("%10e",f1));
        System.out.println(String.format("%3g",f1));
        System.out.println(String.format("%10g",f1));
        System.out.println(String.format("%3a",f1));
        System.out.println(String.format("%10a",f1));

    }
}
