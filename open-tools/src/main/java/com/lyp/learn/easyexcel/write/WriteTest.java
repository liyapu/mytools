package com.lyp.learn.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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
        return userDir + File.separator + "target" + File.separator + "classes" + File.separator + "easyexcel" + File.separator + "write" + File.separator + System.currentTimeMillis() + ".xlsx";
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


    /**
     * 自定义样式
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 创建一个style策略 并注册
     * <p>3. 直接写即可
     */
    @Test
    public void styleWrite() {
        String fileName = getClass().getClassLoader().getResource("").getPath() + "styleWrite" + System.currentTimeMillis() + ".xlsx";
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        // 设置背景色时，需要设置setFillPatternType，头部默认给设置了
        // 如果想要去除背景颜色，也需要不设置setFillPatternType
        headWriteCellStyle.setFillPatternType(FillPatternType.NO_FILL);

        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

        // 字体样式
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("Frozen");
        headWriteFont.setFontHeightInPoints((short) 20);

        headWriteCellStyle.setWriteFont(headWriteFont);
        //自动换行
        headWriteCellStyle.setWrapped(false);
        // 水平对齐方式
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置 水平居中
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        //设置边框样式
//        contentWriteCellStyle.setBorderLeft(DASHED);
//        contentWriteCellStyle.setBorderTop(DASHED);
//        contentWriteCellStyle.setBorderRight(DASHED);
//        contentWriteCellStyle.setBorderBottom(DASHED);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());

        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);

        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板").doWrite(data());
    }

    @Test
    public void styleWrite2() {
        String fileName = getClass().getClassLoader().getResource("").getPath() + "styleWrite" + System.currentTimeMillis() + ".xlsx";
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        // 设置背景色时，需要设置setFillPatternType，头部默认给设置了
        // 如果想要去除背景颜色，也需要不设置setFillPatternType
        headWriteCellStyle.setFillPatternType(FillPatternType.NO_FILL);

        // 背景设置为红色
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteFont.setColor(IndexedColors.RED.index);

        headWriteCellStyle.setWriteFont(headWriteFont);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());

        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
//        contentWriteFont.setColor(IndexedColors.PINK.index);
        contentWriteFont.setColor(IndexedColors.GREEN.index);

        contentWriteCellStyle.setWriteFont(contentWriteFont);

        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板").doWrite(data());
    }

}
