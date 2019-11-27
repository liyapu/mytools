package com.lyp.mt.mg.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://www.cnblogs.com/dintalk/p/10891599.html
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
        //3.创建行对象(索引从0开始)
        Row nRow = sheet.createRow(0);
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
     * 我们通过自定义生成 Excel 报表文件很是麻烦，特别是字体、样式比较复杂的时候。这时候我们可以考虑使用准备好的 Excel 模板，这样我们只需关注模板中的数据即可。
     * <p>
     * 制作并加载Excel 模板，填充数据响应到浏览器（下载）
     *
     * 写出到excel中
     */
    @Test
    public void testExcel02() throws IOException {
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
    public void ImportExcel() throws IOException {
        //定义一个list 集合保存从excel解析的用户
        List<User> list = new ArrayList<>();
        //1.读取上传的文件
        String path = ExcelUtil.class.getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);
        String templatePath =  path + "template/练习.xlsx";
        System.out.println("templatePath = " + templatePath);
        File file = new File(templatePath);
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        //2.获取工作表对象
        XSSFSheet sheet = wb.getSheetAt(0);
        //3.得到行的迭代器
        Iterator<Row> iterator = sheet.iterator();
        int rowNum = 0;
        while (iterator.hasNext()) {
            Row row = iterator.next();
            //跳过标题行
            if (rowNum == 0) {
                rowNum++;
                continue;
            }
            //4.遍历,
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
        //7.读取完数据后,调用service层方法进行批量保存
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
