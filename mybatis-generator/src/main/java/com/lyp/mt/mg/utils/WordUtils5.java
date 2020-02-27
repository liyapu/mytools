package com.lyp.mt.mg.utils;

import com.lyp.mt.yapi.entity.FieldEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 删除合并单元格
 */
public class WordUtils5 {
    public static void main(String[] args) throws Exception {
        WordUtils5 t = new WordUtils5();
        XWPFDocument doc = new XWPFDocument();


        //获取当前数据库的所有表名
        List<String> tableNames = MySqlMetaDataUtil.getAllTables();
        for(String tn : tableNames){
            System.out.println(tn);
            String[] tableNameArr = tn.split("\\|");
            String tableName = tableNameArr[0];
            String tableRemark = "";
            if(tableNameArr.length > 1 && StringUtils.isNotBlank(tableNameArr[1])){
                tableRemark = tableNameArr[1];
            }

            List<FieldEntity> fieldEntities = MySqlMetaDataUtil.listByTableName(tableName);
//            for(FieldEntity fe : fieldEntities){
//                System.out.println(fe);
//            }
            // 测试表格样式
            t.creatTableStyle(doc,tableName,tableRemark,fieldEntities);
//            System.out.println("---------------");
        }



        String filePath = WordUtils5.class.getClassLoader().getResource("").getPath();
        String filePathName = filePath + "testword5.docx";
        System.out.println("filePathName = " + filePathName);

        t.saveDocument(doc, filePathName);
    }



    public void creatTableStyle(XWPFDocument doc,String tableName,String tableRemark, List<FieldEntity> fieldEntities) {
        XWPFParagraph paragraph2 = doc.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("   ");

        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("   " + tableRemark);
        run.setBold(true);

        String rowBgColor = "E7E6E6";

        List<String> columnList = new ArrayList<String>();
        columnList.add(" |序号");
        columnList.add(tableName + "|字段名");
        columnList.add(" |数据类型");
        columnList.add(" |长度");
        columnList.add(tableRemark + "|说明");
        XWPFTable table = doc.createTable(fieldEntities.size() + 2, columnList.size());
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
                setCellText(firstCell, str, 800, false, 0, rowBgColor,null,null);
                firstCellIndex++;
            } else {
                String[] strArr = str.split("\\|");
                for (int i = 1; i < strArr.length; i++) {
                    firstCell = firstRow.getCell(firstCellIndex);
                    setCellText(firstCell, strArr[0], 1600, false, 0, rowBgColor,null,null);
                    secondCell = secondRow.getCell(firstCellIndex);
                    setCellText(secondCell, strArr[i], 1600, false, 0, rowBgColor,null,null);
                    firstCellIndex++;
                }
            }
        }



        int k = 0;
        int rowNum = 1;
        // 数据
        for (int i = 2; i < fieldEntities.size()+2; i++) {
            firstRow = table.getRow(i);
            firstRow.setHeight(380);
//            for (int j = 0; j < 5; j++) {
//                firstCell = firstRow.getCell(j);
//                if(j == 0){
//                    setCellText(firstCell, rowNum+"", 800, false, 0, null,null,"center");
//                }else{
//                    setCellText(firstCell, "测试", 1600, false, 0, null,null,null);
//                }
//                k++;
//            }
            FieldEntity entity = fieldEntities.get(i - 2);
            setCellText(firstRow.getCell(0), rowNum+"", 800, false, 0, null,null,"center");
            setCellText(firstRow.getCell(1), entity.getField(), 1600, false, 0, null,null,null);
            setCellText(firstRow.getCell(2), entity.getType().toLowerCase(), 1600, false, 0, null,null,null);
            setCellText(firstRow.getCell(3), entity.getTypeSize(), 1600, false, 0, null,null,null);
            setCellText(firstRow.getCell(4), entity.getComment(), 1600, false, 0, null,null,null);

            rowNum++;
        }
    }

    /**
     *
     * @param cell
     * @param text
     * @param width
     * @param isShd
     * @param shdValue
     * @param shdColor
     * @param vertical  top,center,both,bottom，也可以传 null,赋默认值 center
     * @param position  left,center,right,both,mediumKashida,distribute,numTab,highKashida,lowKashida,thaiDistribute,也可以传 null,赋默认值 left
     *
     *
     */
    public void setCellText(XWPFTableCell cell, String text, int width, boolean isShd, int shdValue, String shdColor,String vertical,String position) {
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
            ctshd.setColor(shdColor);
        }

//        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
//        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);

        STVerticalJc.Enum stVerticalJc = STVerticalJc.CENTER;
        if(StringUtils.isNotBlank(vertical)){
            stVerticalJc = STVerticalJc.Enum.forString(vertical);
        }

        STJc.Enum stjc = STJc.LEFT;
        if(StringUtils.isNotBlank(position)){
            stjc = STJc.Enum.forString(position);
        }

        ctPr.addNewVAlign().setVal(stVerticalJc);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(stjc);

        cell.setText(text);
    }

    /**
     * @Description: 跨列合并
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
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
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
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