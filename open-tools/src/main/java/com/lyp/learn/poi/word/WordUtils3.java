package com.lyp.learn.poi.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WordUtils3 {
    public static void main(String[] args) throws Exception {
        WordUtils3 t = new WordUtils3();
        XWPFDocument doc = new XWPFDocument();

        // 测试表格样式
        t.testTableStyle(doc);

        String filePath = WordUtils3.class.getClassLoader().getResource("").getPath();
        String filePathName = filePath + "testword8.docx";
        System.out.println("filePathName = " + filePathName);

        t.saveDocument(doc, filePathName);
    }



    public void testTableStyle(XWPFDocument doc) {
        String rowBgColor = "E7E6E6";

        List<String> columnList = new ArrayList<String>();
        columnList.add("序号");
        columnList.add("姓名信息|姓甚|名谁");
        columnList.add("名刺信息|籍贯|营生");
        columnList.add("字");
        XWPFTable table = doc.createTable(7, 6);
        setTableWidth(table, "8000");

        XWPFTableRow firstRow = table.getRow(0);
        XWPFTableRow secondRow = table.getRow(1);
        firstRow.setHeight(400);
        secondRow.setHeight(400);
        XWPFTableCell firstCell = null;
        XWPFTableCell secondCell = null;
        int firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                firstCell = firstRow.getCell(firstCellIndex);
                setCellText(firstCell, str, 1600, false, 0, rowBgColor);
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                for (int i = 1; i < strArr.length; i++) {
                    firstCell = firstRow.getCell(firstCellIndex);
                    setCellText(firstCell, strArr[0], 1600, false, 0, rowBgColor);
                    secondCell = secondRow.getCell(firstCellIndex);
                    setCellText(secondCell, strArr[i], 1600, false, 0, rowBgColor);
                    firstCellIndex++;
                }
            }
        }

        // 合并行(跨列)
        firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                mergeCellsHorizontal(table, 0, firstCellIndex, firstCellIndex + strArr.length - 2);
                firstCellIndex += strArr.length - 1;
            }
        }

        // 合并列(跨行)
        firstCellIndex = 0;
        for (String str : columnList) {
            if (str.indexOf("|") == -1) {
                mergeCellsVertically(table, firstCellIndex, 0, 1);
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                firstCellIndex += strArr.length - 1;
            }
        }

        int k = 0;
        // 数据
        for (int i = 2; i < 7; i++) {
            firstRow = table.getRow(i);
            firstRow.setHeight(380);
            for (int j = 0; j < 6; j++) {
                firstCell = firstRow.getCell(j);
                setCellText(firstCell, "测试", 1600, false, 0, null);
                k++;
            }
        }
    }

    public void setCellText(XWPFTableCell cell, String text, int width, boolean isShd, int shdValue, String shdColor) {
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        CTShd ctshd = ctPr.isSetShd() ? ctPr.getShd() : ctPr.addNewShd();
        ctPr.addNewTcW().setW(BigInteger.valueOf(width));
        if (isShd) {
            if (shdValue > 0 && shdValue <= 38) {
                ctshd.setVal(STShd.Enum.forInt(shdValue));
            }
        }
        if (shdColor != null) {
            //
             ctshd.setFill(shdColor);
//             ctshd.setColor("auto");
//            ctshd.setColor(shdColor);
        }

        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }

    /**
     * @Description: 跨列合并
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge()
                        .setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge()
                        .setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * @Description: 跨行合并
     */
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge()
                        .setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge()
                        .setVal(STMerge.CONTINUE);
            }
        }
    }

    public void setTableWidth(XWPFTable table, String width) {
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc = tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }


    public void saveDocument(XWPFDocument document, String savePath)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(savePath);
        document.write(fos);
        fos.close();
    }
}