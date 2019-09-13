package com.lyp.learn.base.demo.pk03;

import java.io.BufferedReader;

public class FileUtilTest {
    public static void main(String[] args) {

        try {
            String oneLine = FileUtil.processFile((BufferedReader br) -> br.readLine());
            System.out.println(oneLine);
            System.out.println();

            String twoLine = FileUtil.processFile((BufferedReader br) -> br.readLine() + "\t" + br.readLine());
            System.out.println(twoLine);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
