package com.lyp.learn.base.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-12-31 20:29
 */
public class ReadFile {
    public static void main(String[] args) {
        BufferedReader reader = null;
        String buffer = null;
        try {
            reader = new BufferedReader(new FileReader("src/testRead.txt"));
            do {
                buffer = reader.readLine();
                System.out.println(buffer);
            } while (reader.read() != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 问题一：需要显示的调用close，也要对close再加一层try catch 还有个问题是
                // 问题二：close函数也有可能抛异常，如果这里抛出异常，try块里面的异常信息就会被丢弃
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
