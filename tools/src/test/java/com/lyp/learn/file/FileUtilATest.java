package com.lyp.learn.file;

import org.junit.Test;

import java.util.Map;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-11 19:49
 */
public class FileUtilATest {

    @Test
    public void testGetAllIpSegment(){
        Map<String,String> keyMap = new FileUtilA().getAllIpSegment();
        System.out.println("keyMap size is : " + keyMap.size());
        for(Map.Entry<String,String> entry : keyMap.entrySet()){
            System.out.println(entry.getKey() + "  ----- " + entry.getValue());
        }
    }

    @Test
    public void testGetAllIpSegment2(){
        Map<String,String> keyMap = new FileUtilA().getAllIpSegment2();
        System.out.println("keyMap size is : " + keyMap.size());
        for(Map.Entry<String,String> entry : keyMap.entrySet()){
            System.out.println(entry.getKey() + "  ----- " + entry.getValue());
        }
    }


}
