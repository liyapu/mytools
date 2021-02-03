package com.lyp.learn.poi.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.testng.collections.Sets;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * 根据 excel 字段，生成表结构
 *
 CREATE TABLE `hqms_record` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `hospital_code` int(10) unsigned NOT NULL,
 `A01` varchar(22) DEFAULT NULL COMMENT '组织机构代码',
 `A02` varchar(80) DEFAULT NULL COMMENT '医疗机构名称',
 `A48` varchar(50) NOT NULL COMMENT '病案号',
 `A49` int(10) unsigned DEFAULT NULL COMMENT '住院次数',
 `B12` datetime DEFAULT NULL COMMENT '入院时间',
 `B15` datetime NOT NULL COMMENT '出院时间',
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='hqms信息记录'

 */
public class ExcelCreateTable {

    @Test
    public void createTableByExcel01() throws Exception {
        String path = getClass().getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);

        String fileNamePath = path + "文件上传字段校验1207.xlsx";
        System.out.println("fileNamePath = " + fileNamePath);

        InputStream io = new FileInputStream(fileNamePath);
        XSSFWorkbook wb = new XSSFWorkbook(io);
        XSSFSheet sheet = wb.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        StringBuffer tableStr = new StringBuffer();
        int tolal = 0;

        Set<String> requireColumn = Sets.newHashSet("A48", "B12", "B15", "A46C", "A12C", "A13", "C03C", "C04N", "D01");

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int rowNum = row.getRowNum();
//            System.out.println(rowNum);

            if (Objects.equals(0, rowNum) || Objects.equals(1, rowNum)) {
                continue;
            }


//            StringBuffer sb = new StringBuffer();

//            for (int j = 1; j < 6; j++) {
//                if(sb.length() > 0){
//                    sb.append("\t");
//                }
//                sb.append(row.getCell(j));
//            }
//            System.out.println(sb.toString());

            Cell contentCell = row.getCell(1);
            Cell columnNameCell = row.getCell(2);
            Cell columnTypeCell = row.getCell(3);
            Cell columnLengthCell = row.getCell(4);


//            System.out.println(contentCell + "\t" + columnNameCell + "\t" + columnTypeCell + "\t" +  columnLengthCell);

            String content = contentCell.getStringCellValue().trim();
            String columnName = columnNameCell.getStringCellValue().trim();
            String columnType = columnTypeCell.getStringCellValue().trim();
            CellType cellType = columnLengthCell.getCellType();
            int columnLength = 0;

            if (Objects.equals(CellType.NUMERIC, cellType)) {
                double columnLengthDouble = columnLengthCell.getNumericCellValue();
                if (Objects.nonNull(columnLengthDouble)) {
                    columnLength = (int) columnLengthDouble;
                }
            } else if (Objects.equals(CellType.STRING, cellType)) {
                String columnLengthStr = columnLengthCell.getStringCellValue();
                if (StringUtils.isNotBlank(columnLengthStr)) {
                    int index = columnLengthStr.indexOf(".");
                    if (index > 0) {
                        columnLength = Integer.parseInt(columnLengthStr.substring(index));
                    }
                    //System.out.println("---columnLengthStr " + columnLengthStr);
                }
            } else if (Objects.equals(CellType.BOOLEAN, cellType)) {

            }

            tolal += columnLength;


           // System.out.println(content + "\t" + columnName + "\t" + columnType + "\t" + columnLength);

            StringBuffer tableTemp = new StringBuffer();
            String unsigned = null;
            String columnTypeDetail = "";
            String defaultNull = "DEFAULT NULL";

            if (Objects.equals("字符", columnType)) {
                if (Objects.equals(1, columnLength)) {
                    unsigned = " unsigned ";
                    columnTypeDetail = "tinyint(2)";
                }else if (Objects.equals(2, columnLength)) {
                    columnTypeDetail = "char(" + columnLength + ")";
                } else {
                    columnTypeDetail = "varchar(" + columnLength + ")";
                }
            } else if (Objects.equals("数字", columnType)) {
                if(columnLength == 0){
                    String columnLengthStr = columnLengthCell.getStringCellValue();
                    int start = columnLengthStr.indexOf("(");
                    int end = columnLengthStr.indexOf(",");
                    String columnLengthNew = columnLengthStr.substring(start+1,end);
                    columnTypeDetail = "varchar(" + columnLengthNew + ")";
                }else if (columnLength == 1) {
                    unsigned = " unsigned ";
                    columnTypeDetail = "tinyint(2)";
                } else if (Objects.equals(2,columnLength) || Objects.equals(3,columnLength)) {
                    unsigned = " unsigned ";
                    columnTypeDetail = "int(2)";
                }else{
                    unsigned = " unsigned ";
                    columnTypeDetail = "int(10)";
                }
            } else if (Objects.equals("日期时间", columnType)) {
                columnTypeDetail = "datetime";
            } else if (Objects.equals("日期", columnType)) {
                columnTypeDetail = "date";
            }else if(Objects.equals("集合",columnType)){
                columnTypeDetail = "varchar(" + 100 + ")";
            }

            if (requireColumn.contains(columnName)) {
                defaultNull = "NOT NULL";
            }

            tableTemp.append("`" + columnName.toLowerCase() + "` ")
                    .append(columnTypeDetail);
            if (StringUtils.isNotBlank(unsigned)) {
                tableTemp.append(unsigned);
            }

            tableTemp.append(" ")
                    .append(defaultNull);
            tableTemp.append(" COMMENT '" + content + "',").append("\r\n");
            //System.out.println(tableTemp.toString());

            tableStr.append(tableTemp);
        }

        System.out.println();
        System.out.println("total = " + tolal);

        System.out.println(tableStr.toString());


    }
}
