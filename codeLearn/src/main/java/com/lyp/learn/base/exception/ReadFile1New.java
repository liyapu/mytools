package com.lyp.learn.base.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile1New {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(new File("test"))) {
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
