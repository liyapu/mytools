package com.lyp.learn.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-11 19:43
 */
public class FileUtilA {

    public Map<String,String> getAllIpSegment(){
        List<String> allLines = null;
        Map<String,String> keyMap = null;
        try {
            allLines = Files.readAllLines(Paths.get(FileUtilA.class.getResource("/ip.txt").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        keyMap = new HashMap<>(allLines.size());

        for(String line : allLines){
            String[] items = line.split("\\t");
            keyMap.put(items[0].trim(),items[1].trim());
        }
        return keyMap;
    }


    public Map<String,String> getAllIpSegment2() {
        Map<String,String> result = new HashMap<>();
        File file = null;
        FileReader reader = null;
        BufferedReader bReader = null;
        try {
            file = new File(this.getClass().getResource("/ip.txt").toURI());//定义一个file对象，用来初始化FileReader
            reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String line = "";
            while ((line =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                String[] items = line.split("\\t");
                result.put(items[0].trim(),items[1].trim());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != reader){
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
