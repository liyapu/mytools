package com.lyp.mt.mg.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *  https://blog.csdn.net/doujinlong1/article/details/80780823
 *
 *  在日常工作中，经常可能会使用到poi来进行数据导出，但是在导出的过程中，
 *  如果对poi类使用不当，则可能会出现一些问题，比较严重，下面对poi中三种不同的类来进行说明。
 *
 *  1. HSSFWorkbook（excel 2003）
 *     HSSFWorkbook 针对是 EXCEL2003 版本，扩展名为 .xls
 *     所以 此种的局限就是 导出的行数 至多为 65535 行，每行 256列，此种 因为行数不够多 所以一般不会发生OOM。
 *
 * 2.  XSSFWorkbook （excel 2007）
 *     这种形式的出现 是由于 第一种HSSFWorkbook 的局限性而产生的，因为其所导出的行数比较少，
 *     所以 XSSFWookbook应运而生 其 对应的是EXCEL2007+(1048576行，16384列)扩展名 .xlsx，最多可以 导出 104 万行，
 *
 *     不过 这样 就伴随着一个问题---OOM 内存溢出，
 *     原因是 你所 创建的 book sheet row cell 等 此时是存在 内存的 并没有 持久化，
 *     那么 随着 数据量增大 内存的需求量也就增大，那么很大可能就是要 OOM了。
 *
 * 3. SXSSFWorkbook（excel 2007后，poi使用3.8+版本）
 *     因为数据量过大 导致内存吃不消 那么 可以 让内存 到量持久化 吗？
 *     答案是 肯定的，
 *
 *     此种的情况 就是 设置 最大 内存条数 比如  设置 最大内存量为5000 rows
 *     --new SXSSFWookbook（5000），此时 当 行数 达到 5000 时，把 内存 持久化 写到 文件中，以此逐步写入避免OOM,
 *     那么这样 就完美解决了大数据下导出的问题；
 */
public class ExcelUtilsThree {

    /**
     * 下面给出一个使用 SXSSFWorkbook 来进行excel导出的模版工具类：
     *
     * 这里需要注意的是导出的 SXSSFWorkbook 是一个Workbook类，可以直接使用write方法写入OutputStream;
     *
     * 其次吗，传入的headers这个Map是list对象中的列名与字段名的对应，如：
     *   map.put("num", "序号");
     *   map.put("applyId", "申请编号");
     */


    /**
     * @param title sheet名
     * @param headers 标题
     * @param list 数据列表
     */
    public static <T> SXSSFWorkbook export(String title, Map<String, String> headers, List<T> list) {

        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        generateSheet(title, headers, list, workbook);
        return workbook;
    }


    public static <T> void generateSheet(String title, Map<String, String> header, List<T> list, SXSSFWorkbook workbook) {
        SXSSFSheet sheet = workbook.createSheet(title);// 生成一个表格
        sheet.setDefaultColumnWidth(15);// 设置表格默认列宽度为15个字节
        SXSSFRow row = sheet.createRow(0);// 产生表格标题行
        generateHeaders(row, header);
        JSONObject json;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            json = JSONObject.parseObject(list.get(i).toString());
            int cellCount = 0;
            Cell cell;
            for (Map.Entry<String, String> column : header.entrySet()) {
                cell = row.createCell(cellCount++);
                if (column.getKey().split("\\.").length == 2) {// Object.name
                    JSONObject subJson = JSONObject.parseObject(json.get(column.getKey().split("\\.")[0]).toString());
                    if (subJson.isEmpty()) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(formatString(subJson.get(column.getKey().split("\\.")[1])));
                    }
                } else {
                    if (column.getKey().equals("num")) {
                        cell.setCellValue(i + 1);
                    } else {
                        cell.setCellValue(formatString(json.get(column.getKey())));
                    }
                }

            }
        }
    }

    private static void generateHeaders(SXSSFRow row, Map<String, String> headers) {
        int i = 0;
        Cell cell;
        XSSFRichTextString text;
        for (Map.Entry<String, String> header : headers.entrySet()) {
            cell = row.createCell(i);
            text = new XSSFRichTextString(header.getValue());
            cell.setCellValue(text);
            i++;
        }
    }

    public static String formatString(Object s) {
        if (s == null || "null".equals(String.valueOf(s))) {
            return "";
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        String data = s.toString().replaceAll(",", "，");

        if (pattern.matcher(data).matches() && data.length() > 12) {
            return data + "\t";
        } else {
            return data;
        }
    }

}
