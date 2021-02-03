package com.lyp.learn.poi.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 读取 excel 的表头
 *
 * 生成账号
 */
public class ExcelReaderHeader {

    @Test
    public void createTableByExcel01() throws Exception {
        String path = getClass().getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);

        String fileNamePath = path + "文件导出样式.xlsx";
        System.out.println("fileNamePath = " + fileNamePath);

        InputStream io = new FileInputStream(fileNamePath);
        XSSFWorkbook wb = new XSSFWorkbook(io);
        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow row0 = sheet.getRow(0);
        int firstCellNum = row0.getFirstCellNum();
        short lastCellNum = row0.getLastCellNum();

        XSSFRow row1 = sheet.getRow(1);

        for (int i = firstCellNum; i < lastCellNum; i++) {
            String code = row0.getCell(i).getStringCellValue().trim();
            String name = row1.getCell(i).getStringCellValue().trim();
//            System.out.println(name + " --- " + code);

            System.out.println("headerList.add(Pair.of(\"" + code + "\", \"" + name + "\"));");

        }


    }

    @Test
    public void createTableByExcel02() throws Exception {
        String path = getClass().getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);

        String fileNamePath = path + "账号fu.xlsx";
        System.out.println("fileNamePath = " + fileNamePath);

        InputStream io = new FileInputStream(fileNamePath);
        XSSFWorkbook wb = new XSSFWorkbook(io);
        XSSFSheet sheet = wb.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        int i = 0;
        int id = 2;
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (i == 0) {
                i++;
                //跳过 字符串表头
                continue;
            }

            int code = (int) row.getCell(0).getNumericCellValue();
            String hospital = row.getCell(1).getStringCellValue().trim();
            String name = row.getCell(2).getStringCellValue().trim();
            String p = Md5Utils.generate(row.getCell(3).getStringCellValue());

//            System.out.println(code + "  " + hospital +  " " + name + " " + p);

//            System.out.println("INSERT INTO `hospital` (`id`,`hospital_code`, `name`, `status`, `create_time`, `update_time`) VALUES (" + id + ", '" + code + "', '" + hospital + "', '1', now(), now());\n");

            System.out.println("INSERT INTO `fu_tang`.`user` (`id`, `hospital_code`, `name`, `password`, `admin`, `status`, `create_time`, `update_time`) VALUES (" + id + ", '" + code + "', '" + name + "', '" + p + "', '0', '1', now(), now());\n");
            id++;
        }


    }


    @Test
    public void createSql(){
        for (int i = 4; i <= 37; i++) {
            System.out.println("INSERT INTO `fu_tang`.`user_role_rel` (`user_id`, `role_id`, `create_time`, `update_time`) VALUES ("+i+", '2', '2021-01-28 13:07:13', '2021-01-28 13:07:13');");
        }
    }
}
