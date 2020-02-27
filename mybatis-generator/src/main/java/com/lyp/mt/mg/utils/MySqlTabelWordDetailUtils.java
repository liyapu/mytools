package com.lyp.mt.mg.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 1. 生成数据库表字段的word文档，生成的文档需要使用 office word 打开，使用 WPS打开，格式错乱
 * 2. 使用下面的 mian 函数执行
 * 3. 需要修改自己的数据库名称 dbName
 */
public class MySqlTabelWordDetailUtils {

    // 数据库名称
    private static final String dbName = "ncpcs_user";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root$123";


    /**
     * 获取所有数据库中的所有表名称
     */
    public static List<String> getAllTables(){
        List<String> tableNames = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = getConnection();

            //获取数据库的元数据
            DatabaseMetaData metaData = connection.getMetaData();
            rs = metaData.getTables(dbName, null, null, new String[]{"TABLE"});
            while (rs.next()){
                tableNames.add(rs.getString("TABLE_NAME") + "|" + rs.getString("REMARKS")); //表名和表注释
//                System.out.println(rs.getString("TABLE_CAT") + " , " + rs.getString("TABLE_SCHEM") + " , " +  rs.getString("TABLE_NAME") + " , " + rs.getString("TABLE_TYPE") + " , " +  rs.getString("REMARKS") + " , " + rs.getString("TYPE_CAT") + " , " +  rs.getString("TYPE_SCHEM") + " , " + rs.getString("TYPE_NAME") + " , " + rs.getString("SELF_REFERENCING_COL_NAME") + " , " + rs.getString("REF_GENERATION") );

            }
        } catch (Exception e) {
            System.out.println("getAllTables error." + e);
        } finally {
            close(connection,null,rs);
        }
        return tableNames;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;

        Properties pros = new Properties();
        pros.put("user",USERNAME);
        pros.put("password",PASSWORD);
        //mysql,oracle 公用的
        pros.setProperty("remarks", "true"); //设置可以获取remarks信息

        //mysql获取remark
        pros.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        //oracle获取remark
        pros.setProperty("remarksReporting","true");


        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,pros);
        } catch (Exception e) {
            System.out.println("get connection failure" + e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("close connection failure" + e);
            }
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("close resultSet error." + e);
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("close statement error." + e);
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("close connection error." + e);
            }
        }
    }

    /**
     * 根据表名获取表中所有的字段名称
     * @param tableName
     * @return
     */
    public static List<FieldEntity> listByTableName(String tableName){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        FieldEntity fe = null;
        Connection connection = null;
        try {
            connection = getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet colRet = metaData.getColumns(dbName,"%", tableName,"%");
            while(colRet.next()) {
                fe = new FieldEntity();
                //列名
                String columnName = colRet.getString("COLUMN_NAME");
                //列的类型
                String columnType = colRet.getString("TYPE_NAME");
                int datasize = colRet.getInt("COLUMN_SIZE");
                //列类型的长度
                int digits = colRet.getInt("DECIMAL_DIGITS");
                int nullable = colRet.getInt("NULLABLE");
                //注释
                String remark = colRet.getString("REMARKS");

//                System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable + " " +remark );

                fe.setField(columnName);
                fe.setType(columnType);
                fe.setTypeSize(datasize+"");
                fe.setComment(remark);
                fieldEntities.add(fe);
            }

        } catch (Exception e) {
            System.out.println("listByTableName error." + e);
        } finally {
            closeConnection(connection);
        }

        return fieldEntities;
    }


    public static void main(String[] args) throws Exception {
        MySqlTabelWordDetailUtils tableWord = new MySqlTabelWordDetailUtils();
        XWPFDocument doc = new XWPFDocument();

        //排除的表名
        List<String> excludeTable = Arrays.asList("uni_user_info","sm_param_base","organ_province_map");

        //获取当前数据库的所有表名
        List<String> tableNames = tableWord.getAllTables();
        for(String tn : tableNames){
            System.out.println(tn);

            String[] tableNameArr = tn.split("\\|");
            String tableName = tableNameArr[0];
            String tableRemark = "";
            if(tableNameArr.length > 1 && StringUtils.isNotBlank(tableNameArr[1])){
                tableRemark = tableNameArr[1];
            }

            if(excludeTable.contains(tableName)){
                System.out.println("排除表：" + tableName);
                continue;
            }


                List<FieldEntity> fieldEntities = tableWord.listByTableName(tableName);
//            for(FieldEntity fe : fieldEntities){
//                System.out.println(fe);
//            }
            tableWord.creatTableStyle(doc,tableName,tableRemark,fieldEntities);
        }


        String filePath = MySqlTabelWordDetailUtils.class.getClassLoader().getResource("").getPath();
        String filePathName = filePath + "testword7.docx";
        System.out.println(" word 文档生成路径 =============== " + filePathName);

        tableWord.saveDocument(doc, filePathName);
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


        int rowNum = 1;
        // 数据
        for (int i = 2; i < fieldEntities.size()+2; i++) {
            firstRow = table.getRow(i);
            firstRow.setHeight(380);
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

class FieldEntity {
    private String field;
    private String type;
    private String typeSize;
    private String comment;
    private String tableName;

    public FieldEntity(){

    }

    public FieldEntity(String filed,String comment){
        this.field = filed;
        this.comment = comment;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(String typeSize) {
        this.typeSize = typeSize;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", typeSize='" + typeSize + '\'' +
                ", comment='" + comment + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
