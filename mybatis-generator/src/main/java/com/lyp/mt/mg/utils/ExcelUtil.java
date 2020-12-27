package com.lyp.mt.mg.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.cnblogs.com/dintalk/p/10891599.html
 * https://www.cnblogs.com/dw3306/p/11357258.html
 *
 * Java中常见的用来操作 Excel 的方式有2种：JXL和POI。
 *
 * JXL只能对 Excel进行操作，且只支持到 Excel 95-2000的版本。
 *
 * 而POI是Apache 的开源项目，由Java编写的跨平台 Java API，可操作 Microsoft Office。
 * 借助POI，可以方便的生成数据报表，数据批量上传，数据备份等工作。
 *
 * 使用API
 * HSSF : 读写 Microsoft Excel XLS 格式文档
 * XSSF : 读写 Microsoft Excel OOXML XLSX 格式文档
 * SXSSF : 读写 Microsoft Excel OOXML XLSX 格式文档
 * HWPF : 读写 Microsoft Word DOC 格式文档
 * HSLF : 读写 Microsoft PowerPoint 格式文档
 * HDGF : 读 Microsoft Visio 格式文档
 * HPBF : 读 Microsoft Publisher 格式文档
 * HSMF : 读 Microsoft Outlook 格式文档
 */

public class ExcelUtil {

    /**
     *
     */
    public void testExcel01() {
        //1.创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
        //2.创建Sheet对象
        Sheet sheet = wb.createSheet();
        //获取sheet页总数
        int numberOfSheets = wb.getNumberOfSheets();
        //获取激活的sheet页签
        int activeSheetIndex = wb.getActiveSheetIndex();
        int firstVisibleTab = wb.getFirstVisibleTab();

        //3.创建行对象(索引从0开始)
        Row nRow = sheet.createRow(0);
        //获取列的总数
        short lastCellNum = nRow.getLastCellNum();
        //获取第一列
        short firstCellNum = nRow.getFirstCellNum();
        //获取第一行的行号
        int firstRowNum = sheet.getFirstRowNum();
        //获取最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        
        //4.设置行高和列宽
        nRow.setHeightInPoints(26.25f);
        //(列的索引,列宽*256(理解为固定写法))
        sheet.setColumnWidth(1, 26 * 256);
        //5.创建单元格对象(索引从0开始)
        Cell nCell = nRow.createCell(0);
        
        //6.设置单元格内容
        nCell.setCellValue("dinTalk");

        //==============================
        //7.创建单元格样式对象
        CellStyle style = wb.createCellStyle();
        //8.创建字体对象
        Font font = wb.createFont();
        //9.设置字体和其大小及效果
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true); //加粗
        //10.设置样式
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);        //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//纵向居中
        style.setBorderTop(BorderStyle.THIN);                //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);                //左细线
        style.setBorderRight(BorderStyle.THIN);                //右细线
        //11.为单元格应用样式
        nCell.setCellStyle(style);
    }

    /**
     * 1.模板打印(下载)
     * 我们通过自定义生成 Excel 报表文件很是麻烦，特别是字体、样式比较复杂的时候。
     * 这时候我们可以考虑使用准备好的 Excel 模板，这样我们只需关注模板中的数据即可。
     *
     * 制作并加载Excel 模板，填充数据响应到浏览器（下载）
     *
     * 写出到excel中
     */
    @Test
    public void testWriteExcel() throws IOException {
        //1.用获取excel模板的真实路径
        String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);
        String templatePath =  path + "template/练习.xlsx";
        System.out.println("templatePath = " + templatePath);
        //2.读取excel模板,创建excel对象
        XSSFWorkbook wb = new XSSFWorkbook(templatePath);
        //3.读取sheet对象
        Sheet sheet = wb.getSheetAt(0);
        //4.定义一些可复用的对象
        int rowIndex = 0; //行的索引
        int cellIndex = 0; //单元格的索引
        Row nRow = null;
        Cell nCell = null;
        //5.读取大标题行
        nRow = sheet.getRow(rowIndex++); // 使用后 +1
        //6.读取大标题的单元格
        nCell = nRow.getCell(cellIndex);
        //7.设置大标题的内容
        LocalDate localDate = LocalDate.now();
        String bigTitle =  localDate.toString() + " 新增用户表";
        nCell.setCellValue(bigTitle);
        //8.跳过第二行(模板的小标题,即excel设置好样式的表头,我们要用)
        rowIndex++;
        //9.读取第三行,获取它的样式
        nRow = sheet.getRow(rowIndex);
        //读取行高
        float lineHeight = nRow.getHeightInPoints();
        //10.获取第三行的5个单元格中的样式
        CellStyle cs1 = nRow.getCell(cellIndex++).getCellStyle();
        CellStyle cs2 = nRow.getCell(cellIndex++).getCellStyle();
        CellStyle cs3 = nRow.getCell(cellIndex++).getCellStyle();
        CellStyle cs4 = nRow.getCell(cellIndex++).getCellStyle();
        CellStyle cs5 = nRow.getCell(cellIndex++).getCellStyle();
        //11.通过月份查询新增用户列表
        List<User> users = getUsers();
        //12.遍历数据
        for (User user : users) {
            //13.创建数据行
            nRow = sheet.createRow(rowIndex++);
            //16.设置数据行高
            nRow.setHeightInPoints(lineHeight);
            //17.重置cellIndex,从第一列开始写数据
            cellIndex = 0;
            //18.创建数据单元格，设置单元格内容和样式
            //用户名
            nCell = nRow.createCell(cellIndex++);
            nCell.setCellStyle(cs1);
            nCell.setCellValue(user.getUserName());
            //性别
            nCell = nRow.createCell(cellIndex++);
            nCell.setCellStyle(cs2);
            nCell.setCellValue(user.getGender());
            //年龄
            nCell = nRow.createCell(cellIndex++);
            nCell.setCellStyle(cs3);
            nCell.setCellValue(user.getAge());
            //手机号
            nCell = nRow.createCell(cellIndex++);
            nCell.setCellStyle(cs4);
            nCell.setCellValue(user.getPhone());
            //邮箱
            nCell = nRow.createCell(cellIndex++);
            nCell.setCellStyle(cs5);
            nCell.setCellValue(user.getEmail());
        }

        //最后，下载新增用户表，字节数组的输出流，它可存可取，带缓冲区
        File file = new File(path + "test/练习下载.xlsx");
        File parentFile = file.getParentFile();
        System.out.println(parentFile.isDirectory());
        System.out.println(parentFile.exists());
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        //将工作簿写到输出流中
        wb.write(fos);

        fos.close();
        wb.close();
    }

    /**
     * 2.批量导入(上传)
     * 在添加数据时，通过批量导入可大大减少人力。但是批量导入需要代码解析固定格式的模板，
     * 因此我们最好给用户提供模板下载功能。我们同样以导入用户表为例：
     * 统一 excel 模板格式
     *
     * 从excel中读取 批量导入用户
     */
    @Test
    public void testImportExcel() throws IOException {
        //定义一个list 集合保存从excel解析的用户
        List<User> list = new ArrayList<>();
        //1.读取上传的文件
        String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);
        String templatePath =  path + "template/练习2.xlsx";
        System.out.println("templatePath = " + templatePath);
        File file = new File(templatePath);
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        //2.获取工作表对象
        XSSFSheet sheet = wb.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        System.out.println("lastRowNum = " + lastRowNum);

        //跳过大标题行
        for(int rowIndex = 1; rowIndex <= lastRowNum ; rowIndex++){
            Row row = sheet.getRow(rowIndex);
            int cellIndex = 0;
            //5.创建用户对象
            User user = new User();
            user.setUserName(getValue(row.getCell(cellIndex++)).toString());
            user.setGender(getValue(row.getCell(cellIndex++)).toString());
            user.setAge(NumberUtils.toInt(getValue(row.getCell(cellIndex++)).toString()));
            user.setPhone(getValue(row.getCell(cellIndex++)).toString());
            user.setEmail(getValue(row.getCell(cellIndex++)).toString());
            //6.将用户对象保存到集合中
            list.add(user);
            System.out.println(user);
        }
//        //3.得到行的迭代器
//        Iterator<Row> iterator = sheet.iterator();
//        int rowNum = 0;
//        while (iterator.hasNext()) {
//            Row row = iterator.next();
//            //跳过标题行
//            if (rowNum == 0) {
//                rowNum++;
//                continue;
//            }
//            //4.遍历,
//            int cellIndex = 0;
//            //5.创建用户对象
//            User user = new User();
//            user.setUserName(getValue(row.getCell(cellIndex++)).toString());
//            user.setGender(getValue(row.getCell(cellIndex++)).toString());
//            user.setAge(NumberUtils.toInt(getValue(row.getCell(cellIndex++)).toString()));
//            user.setPhone(getValue(row.getCell(cellIndex++)).toString());
//            user.setEmail(getValue(row.getCell(cellIndex++)).toString());
//            //6.将用户对象保存到集合中
//            list.add(user);
//            System.out.println(user);
//        }
        //7.读取完数据后,调用service层方法进行批量保存
        System.out.println();
        System.out.println(list);
    }
    /**
     * 获取单元格内的数据,并进行格式转换
     * @param cell
     * @return
     */
    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:// 数值和日期均是此类型,需进一步判断
                if (DateUtil.isCellDateFormatted(cell)) {
                    //是日期类型
                    return cell.getDateCellValue();
                } else {
                    //是数值类型
                    return cell.getNumericCellValue();
                }
            default:
                return null;
        }
    }

    /**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    /**
     * 百万数据报表的导出导入
     * 　　当我们碰到数据量比较大的时候（百万级），我们该如何通过使用 POI 对百万级数据报表进行导入和导出的操作呢？
     * 我们知道，Excel可以分为早期的 Excel2003版本（使用POI的HSSF对象操作）和 Excel2007版本（使用POI的 XSSF操作），
     *
     * 两者对百万数据的支持如下：
     *  HSSFWorkbook 最大行数和列数限制 最大支持65536行
     *  XSSFWorkbook 最大支持1048576行
     *
     *  XSSFWorkbook 单个 sheet 表就支持近百万条数据。但实际运行时还可能存在问题，
     *  原因是执行 POI 报表所产生的行对象，单元格对象，字体对象，他们都不会销毁，这就有导致 OOM 的风险。
     *  我们可以使用JDK提供的性能工具 Jvisualvm 来监视程序的运行情况，包括 CUP,垃圾回收，内存的分配和使用情况（Jvisualvm位于JAVA_HOME/bin目录下，双击打开即可）。
     */

    /**
     * 百万数据报表导出
     * ​ 　　基于 XSSFWork 导出 Excel 报表，是通过将所有单元格对象保存到内存中，当所有的 Excel 单元格全部创建完成之后一次性写入到 Excel 并导出。
     * 当百万数据级别的Excel 导出时，随着表格的不断创建，内存中对象越来越多，直至内存溢出。
     *
     *
     * Apache Poi 提供了 SXSSFWork 对象，专门用于处理大数据量 Excel 报表导出。
     * 在实例化 SXSSFWork 这个对象时，可以指定在内存中所产生的 POI 导出相关对象的数量（默认 100），
     * 一旦内存中的对象的个数达到这个指定值时，就将内存中的这些对象的内容写入到磁盘中（XML 的文件格式），就可以将这些对象从内存中销毁，
     * 以后只要达到这个值，就会以类似的处理方式处理，直至 Excel 导出完成。
     * SXSSFWorkbook它支持百万级数据的POI,但是不支持模板打印也不支持太多的样式。因此我们需要通过自定义的方式来进行导出。
     * @return
     */


    /**
     * 百万数据报表导出
     * 下载用户新增表
     */
    @Test
    public void printExcel()throws Exception{
        //1.创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();

        // 病例导出用的这个，指定大小的， 创建 createCell 也是简单格式的
//        SXSSFWorkbook wb = new SXSSFWorkbook(1000);//默认值是100

        //2.创建Sheet对象
        Sheet sheet = wb.createSheet();
        //3.定义一些可复用的对象
        int rowIndex = 0;//行的索引
        int cellIndex = 1;//单元格的索引
        Row nRow = null;
        Cell nCell = null;
        //4.设置列的宽度（列索引，列宽*256  理解为固定写法）
        sheet.setColumnWidth(1,26*256);
        sheet.setColumnWidth(2,12*256);
        sheet.setColumnWidth(3,29*256);
        sheet.setColumnWidth(4,12*256);
        sheet.setColumnWidth(5,15*256);
        //5.创建大标题行   大标题：2019年5月份新增用户表
        nRow = sheet.createRow(rowIndex++);//使用的是0,使用完了+1
        //设置大标题行的高度
        nRow.setHeightInPoints(36);
        //6.创建大标题的单元格
        nCell = nRow.createCell(cellIndex);
        //7.合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,5));
        //8.设置大标题内容
        String bigTitle = "2019-01".replaceAll("-0","-").replaceAll("-","年") + "月份新增用户表";//inputDate  2015-01  2015年1月份出货表
        nCell.setCellValue(bigTitle);
        //9.创建小标题内容
        String[] titles = new String[]{"用户名","性别","年龄","手机号","邮箱"};
        //10.创建小标题行
        nRow = sheet.createRow(rowIndex++);//使用的是1，使用完了再加1
        //设置小标题行高
        nRow.setHeightInPoints(26.25f);
        //12.创建小标题的单元格
        for(String title : titles){
            nCell = nRow.createCell(cellIndex++);
            //设置小标题内容
            nCell.setCellValue(title);
        }
        //13.获取要生成的数据（数据库内的用户数据）
        List<User> list = getUsers();
        //14.遍历数据
        for(User user : list){
            for(int i=0;i<5000;i++) {
                //15.创建数据行
                nRow = sheet.createRow(rowIndex++);
                //16.设置数据行高
                nRow.setHeightInPoints(24);
                //17.重置cellIndex
                cellIndex = 1;
                //18.创建数据单元格，设置单元格内容
                //用户名
                nCell = nRow.createCell(cellIndex++);
                nCell.setCellValue(user.getUserName());
                //性别
                nCell = nRow.createCell(cellIndex++);
                nCell.setCellValue(user.getGender());
                //年龄
                nCell = nRow.createCell(cellIndex++);
                nCell.setCellValue(user.getAge());
                //手机号
                nCell = nRow.createCell(cellIndex++);
                nCell.setCellValue(user.getPhone());
                //邮箱
                nCell = nRow.createCell(cellIndex++);
                nCell.setCellValue(user.getEmail());
            }
        }

        //最后，下载新增用户表，字节数组的输出流，它可存可取，带缓冲区
        String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
        File file = new File(path + "test/练习下载2.xlsx");
        File parentFile = file.getParentFile();
        System.out.println(parentFile.isDirectory());
        System.out.println(parentFile.exists());
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        //将工作簿写到输出流中
        wb.write(fos);

        fos.close();
        wb.close();
    }

    /**
     * 百万数据报表导入
     *
     * 导入，其实就是读取，读取excel的两种思路：
     *  第一种：全部读取
     *      优势：对excel的增删改查都方便
     *      弊端：由于要加载完整合excel文件，如果文件过大时，对内存消耗严重
     *
     *  第二种：按事件触发
     *      触发到什么事件，就读什么内容。
     *      事件分为：
     *          读到行的开始
     *          读到行的结束
     *          读到一行的内容
     *      优势：执行解析效率高，因为它是按照事件触发的。一次只读一行数据
     *      弊端：不利于保存，更新和删除。因为它没有读完整个excel，所以对整个excel的结构不清楚。
     *      它适用于数据量级比较大的情况
     *
     *  第一步：导入POI坐标后创建处理器
     *  第二步：创建解析器
     */
    @Test
    public void testReadExcelMillion() throws Exception {
        //测试解析桌面百万级excel文件
        String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
        String filePath = path + "test/练习下载2.xlsx";
        new ExcelParse().parse(filePath);
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("孙悟空","男",28,"13788888888","sun@163.com"));
        users.add(new User("猪八戒","",25,"13766666","zhu@qq.com"));
        users.add(new User("唐僧","女",26,"1375555555","tang@qq.com"));

        return users;
    }
}


class User{
    private String userName;
    private String gender;
    private Integer age;
    private String phone;
    private String email;

    public User() {
    }

    public User(String userName, String gender, Integer age, String phone, String email) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

/**
 * 这个类谁用谁写（读取excel内容要做的事，实现接口，重写方法）
 * @author Mr.song
 * @date 2019/05/19 20:11
 * 第一步：导入POI坐标后创建处理器
 */
class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    /**
     * 每一行创建一个user对象,当此行解析结束之后，打印user对象
     */
    private User user;

    //开始解析某一行，int ：行号
    @Override
    public void startRow(int i) {
        if (i >= 2) { //跳过标题行，开始创建user对象
            user = new User();
        }
    }

    //此行解析结束
    @Override
    public void endRow(int i) {
        System.out.println(user);
        //这里简单打印，可以存入列表。当列表内user对象大于1000时，存入数据库
    }
    /**
     * 获取当前行的每一个单元格数据
     * @param cellName    ： 当前单元格名称 ： B32   C23   DXX
     * @param cellValue   ： 当前单元格数据
     * @param xssfComment ： 单元格注释
     *                    用户名    性别    年龄    手机号 邮箱
     */
    @Override
    public void cell(String cellName, String cellValue, XSSFComment xssfComment) {
        String name = cellName.substring(0, 1);//B2--->B   F2---->F
        if (user != null) {
            switch (name) {
                case "B": {
                    user.setUserName(cellValue);
                    break;
                }
                case "C": {
                    user.setGender(cellValue);
                    break;
                }
                case "D": {
                    user.setAge(Integer.parseInt(cellValue));
                    break;
                }
                case "E": {
                    user.setPhone(cellValue);
                    break;
                }
                case "F": {
                    user.setEmail(cellValue);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}

/**解析器，写法基本固定
 * @author Mr.song
 * @date 2019/05/19 20:08
 * 第二步：创建解析器
 *
 */
 class ExcelParse {
    public void parse (String path) throws Exception {
        //解析器
        SheetHandler hl = new SheetHandler();
        //1.根据 Excel 获取 OPCPackage 对象
        OPCPackage pkg = OPCPackage.open(path, PackageAccess.READ);
        try {
            //2.创建 XSSFReader 对象
            XSSFReader reader = new XSSFReader(pkg);
            //3.获取 SharedStringsTable 对象
            SharedStringsTable sst = reader.getSharedStringsTable();
            //4.获取 StylesTable 对象
            StylesTable styles = reader.getStylesTable();
            XMLReader parser = XMLReaderFactory.createXMLReader();
            // 处理公共属性
            parser.setContentHandler(new XSSFSheetXMLHandler(styles,sst, hl, false));
            XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) reader.getSheetsData();
            //逐行读取逐行解析
            while (sheets.hasNext()) {
                InputStream sheetstream = sheets.next();
                InputSource sheetSource = new InputSource(sheetstream);
                try {
                    parser.parse(sheetSource);
                } finally {
                    sheetstream.close();
                }
            }
        } finally {
            pkg.close();
        }
    }

}
