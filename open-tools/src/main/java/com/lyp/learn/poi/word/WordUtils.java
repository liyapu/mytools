package com.lyp.learn.poi.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Apache POI Word - Tables  https://www.tutorialspoint.com/apache_poi_word/apache_poi_word_tables.htm
 */
public class WordUtils {

    public static void main(String[] args)throws Exception {

        //Blank Document
        XWPFDocument document = new XWPFDocument();

        File file = new File(WordUtils.class.getClassLoader().getResource("").getPath() + "create_table.docx");
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(file);

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("col one, row one");
        tableRowOne.addNewTableCell().setText("col two, row one");
        tableRowOne.addNewTableCell().setText("col three, row one");


        //create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("col one, row two");
        tableRowTwo.getCell(1).setText("col two, row two");
        tableRowTwo.getCell(2).setText("col three, row two");

        //create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("col one, row three");
        tableRowThree.getCell(1).setText("col two, row three");
        tableRowThree.getCell(2).setText("col three, row three");

        document.write(out);
        out.close();
        System.out.println("create_table.docx written successully");
    }


    public void test() throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("test.docx")));
        List<XWPFTable> tables = doc.getTables();
        for (XWPFTable table:tables){
            // <w:tbl>
            CTTbl ctTbl = table.getCTTbl();

            // 表格属性<w:tblPr>
            CTTblPr tblPr = ctTbl.getTblPr();
            // 表格边框<w:tblBorders>
            CTTblBorders tblBorders = tblPr.getTblBorders();

            // 表格列定义<w:tblGrid>
            CTTblGrid tblGrid = ctTbl.getTblGrid();

            // 获取表格所有行
            List<CTRow> trList = ctTbl.getTrList();
            //<w:tr>
            for(CTRow row:trList){
                // 当前行属性 <w:trPr>
                CTTrPr trPr = row.getTrPr();
                // 获取当前行内所有单元格 <w:tc>
                List<CTTc> tcList = row.getTcList();
                for (CTTc tc:tcList){
                    // 当前单元格属性  <w:tcPr>
                    CTTcPr tcPr = tc.getTcPr();
                    // 获取当前单元格内所有段落 <w:p>
                    List<CTP> pList = tc.getPList();
                    for (CTP p:pList){
                        // 当前段落属性 <w:pPr>
                        CTPPr pPr = p.getPPr();
                        // 获取当前段落所有文本区域 <w:r>
                        List<CTR> rList = p.getRList();
                        for (CTR r:rList){

                            // 获取所有文本
                            List<CTText> tList = r.getTList();
                            for (CTText text:tList){
                                // 获取文字
                                String stringValue = text.getStringValue();
                            }
                        }
                    }
                }
            }
        }
    }
}