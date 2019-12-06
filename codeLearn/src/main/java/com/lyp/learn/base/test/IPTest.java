package com.lyp.learn.base.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-08 15:35
 */
public class IPTest {
    public static void main(String[] args) {
//        System.out.println(IPUtils.getCountry("27.106.204.0"));
//        System.out.println(IPUtils.getCountry("42.62.180.0"));
//        System.out.println(IPUtils.getCountry("14.192.76.1"));
//        System.out.println(IPUtils.getCountry("203.0.42.1"));
        try {
            new IPTest().test();
        }catch (Exception e){

        }

    }

    private void test() throws Exception{
        File file = new File(this.getClass().getResource("/data.txt").toURI());//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
          //  System.out.println(s);
//            System.out.println(IPUtils.getCountry(s) + "----" + s);
        }
        bReader.close();
       // String str = sb.toString();
       // System.out.println(str );
    }
}
