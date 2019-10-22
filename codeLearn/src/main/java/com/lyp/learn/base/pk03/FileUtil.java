package com.lyp.learn.base.pk03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileUtil {

    public static String processFile(BufferedReaderProcessor p) throws IOException,URISyntaxException {
        BufferedReader bufferedReader;
        try {
            File file = new File(FileUtil.class.getResource("/data.txt").toURI());//定义一个file对象，用来初始化FileReader
            FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            bufferedReader = new BufferedReader(reader);
            return p.process(bufferedReader);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return  null;
    }
}
