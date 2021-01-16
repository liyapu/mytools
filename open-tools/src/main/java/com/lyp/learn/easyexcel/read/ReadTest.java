package com.lyp.learn.easyexcel.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * easyexcel 官网
 * https://www.yuque.com/easyexcel/doc/easyexcel
 */
public class ReadTest {

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Hospital}
     * <p>
     * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link HospitalListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        String fileName = getFileName();

        //这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, Hospital.class, new HospitalListener()).sheet().doRead();

        //指定 sheet
//        EasyExcel.read(fileName, Hospital.class, new HospitalListener()).sheet(0).doRead();

//        //指定 sheet ,指定标题行
//        EasyExcel.read(fileName, Hospital.class, new HospitalListener()).sheet(0)
//                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
//                .headRowNumber(1).doRead();

        EasyExcel.read(fileName, Hospital.class, new HospitalListener()).sheet()
                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(3).doRead();
    }

    private String getFileName() {
        String userDir = System.getProperty("user.dir");
        System.out.println("userDir = " + userDir);
        return userDir + File.separator + "target" + File.separator + "classes" + File.separator + "easyexcel" + File.separator + "read" + File.separator + "批量用户准备.xlsx";
    }

    @Test
    public void simpleRead2() {
        String fileName = getFileName();

        ExcelReader excelReader = EasyExcel.read(fileName, Hospital.class, new HospitalListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(3).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }
}
