package com.lyp.learn.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-21 10:36
 */
public class WriteTest {

    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = getWriteFile();
        System.out.println("fileName ===== " + fileName);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板aaaa").doWrite(data());

//        // 写法2
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//        excelWriter.write(data(), writeSheet);
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();
    }

    private String getWriteFile() {
        String userDir = System.getProperty("user.dir");
        System.out.println("userDir = " + userDir);
        return userDir + File.separator + "target" + File.separator + "classes" + File.separator + "easyexcel" + File.separator + "write" + File.separator + System.currentTimeMillis() +  ".xlsx";
    }


    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
