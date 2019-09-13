package com.lyp.learn.base.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile1Old {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("test"));
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }
}
