package com.lyp.learn.base.classs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-13 14:30
 */
public class LoadFile {
    public static void main(String[] args) throws Exception{
        //src目录下，直接加载
        InputStream in1 = null;
        //in1 = this.getClass().getClassLoader().getResourceAsStream("test1.txt");
        //没有getClassLoader，报错
        //in1 = LoadFile.class.getResourceAsStream("test2.txt");
        in1 = LoadFile.class.getClassLoader().getResourceAsStream("test2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in1));
        String line = null;
        StringBuilder sb = null;
        while((line = br.readLine()) !=null){
            System.out.println(line);
        }
        br.close();

        //放在内部文件夹，要写全路径
        InputStream in2 = null;
        //in2 = this.getClass().getClassLoader().getResourceAsStream("com/atguigu/java/fanshe/test2.txt");
        //in2 = LoadFile.class.getClassLoader().getResourceAsStream("com/lyp/learn/classs/test1.txt");
        //in2 = LoadFile.class.getClassLoader().getResourceAsStream("com.lyp.learn.classs.test1.txt");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
        String line2 = null;
        while((line2 = br2.readLine()) !=null){
            System.out.println(line2);
        }
        br2.close();
    }
}
