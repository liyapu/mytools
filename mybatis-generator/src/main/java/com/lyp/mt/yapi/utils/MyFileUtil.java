package com.lyp.mt.yapi.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-10 11:28
 */
public class MyFileUtil {

    /**
     * 输出文件到当前根目录
     * @param object
     * @param fileName
     */
    public  static void toFileJson(Object object,String fileName){
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, JSON.toJSONString(object, true), "utf-8");
        } catch (IOException e) {
            System.out.println("toFileJson error" + e);
        }
    }
}

