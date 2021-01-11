package com.lyp.mt.mg.utils;

import com.lyp.mt.mg.enums.BusinessEnum;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-11-04 20:00
 *
 *
 * INSERT INTO `test`.`business_const` (`id`, `type`, `name`) VALUES ('1', '1', '北京市');
 * INSERT INTO `test`.`business_const` (`id`,`type`, `code`, `name`) VALUES ('1','2', '西南地区', '河南');
 *
 * INSERT INTO `tusdao_child_hospital`.`control_organ_info` (`organ_id_show`, `organ_name`, `organ_classify`, `organ_nature`, `organ_province`, `hemopathy`, `solid_tumor`, `national_point`, `foton_center`, `organ_status`, `create_user`, `create_time`) VALUES ('gogan_id_show', '医院名称', 'classify', 'nature', 'province', 'hem', 'solid', 'poin', 'fotoncenter', '1', '1', '2019-11-01 13:36:55');
 */
public class ExcelUtils {

    /**
     * 读取excel 生成sql语句，补充经纬度
     *
     *  UPDATE `ncpcs_user`.`control_organ_info` SET `longitude` = '300', `latitude` = '400' WHERE (`organ_name` = '院');
     */
    @Test
    public void testLongitudeLatitude() throws IOException {
        String pathStr  = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/机构列表0819 -发给老师(2).xlsx";
        FileInputStream fis = new FileInputStream(pathStr);
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *    1.XSSFWorkbook
         *    2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 获得第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        String sql = "UPDATE `ncpcs_user`.`control_organ_info` SET `longitude` = '%s', `latitude` = '%s' WHERE (`organ_name` = '%s');";
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rows; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            String organName = sheetRow.getCell(2).toString().trim();
            // 经度 longitude
            String longitude = sheetRow.getCell(6).toString().trim();
            // 维度 latitude
            String latitude =  sheetRow.getCell(7).toString().trim();
            System.out.println(String.format(sql,longitude,latitude,organName));
        }

    }

    @Test
    public void testObtainProvince() throws Exception {
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/表_1105.xlsx";
        FileInputStream fis = new FileInputStream(pathStr);
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *    1.XSSFWorkbook
         *    2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 获得第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();
        List<String> provinces = new ArrayList<>();
        for (int i = 1; i < rows; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            int cellNum = 3;
            XSSFCell cell = sheetRow.getCell(cellNum);
            String cellStr = cell.toString().trim();
            if(StringUtils.isNotBlank(cellStr) && !"/".equals(cellStr)){
                if(!provinces.contains(cellStr)){
                    provinces.add(cellStr);
                }
            }
        }

        int id = 2;
        int code = 1;
        StringBuilder sb = new StringBuilder();
        System.out.println("INSERT INTO `test`.`business_const` (`id`, `type`, `code`,`name`) VALUES ('1', '1','0', '未知');");
        for(String s : provinces){
//            sb.append(s.trim()).append(":").append(code++).append(";");
            String sql = "INSERT INTO `test`.`business_const` (`id`, `type`, `code`,`name`) VALUES ('"+ (id++)+"', '1','"+code+++"', '"+s+"');";
            System.out.println(sql);
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testObtainArea() throws Exception {
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/机构列表_1105.xlsx";
        FileInputStream fis = new FileInputStream(pathStr);
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *    1.XSSFWorkbook
         *    2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 获得第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(1);

        int rows = sheet.getPhysicalNumberOfRows();
        List<String> areas = new ArrayList<>();
        int id = 35;
        for (int i = 1; i < rows; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
//            int lastCellNum = sheetRow.getLastCellNum();
//            for(int j = 0; j < lastCellNum; j++){
//                System.out.print(sheetRow.getCell(j)+"\t");
//            }
            String area = sheetRow.getCell(1).toString().trim();
            String province = sheetRow.getCell(0).toString().trim();
            String sql = "INSERT INTO `test`.`business_const` (`id`,`type`, `code`, `name`) VALUES ('"+(id++)+"','2', '"+area+"', '"+province+"');";
            System.out.println(sql);

        }

//        int id = 2;
        int code = 1;
        StringBuilder sb = new StringBuilder();
//        for(String s : provinces){
////            sb.append(s.trim()).append(":").append(code++).append(";");
//            String sql = "INSERT INTO `test`.`business_const` (`id`, `type`, `code`,`name`) VALUES ('"+ (id++)+"', '1','"+code+++"', '"+s+"');";
//            System.out.println(sql);
//        }
        System.out.println(sb.toString());
    }


    @Test
    public void testOrganProvinceMap(){
        String str = "330000: 浙江省,340000: 安徽省,350000: 福建省,360000: 江西省,370000: 山东省,410000: 河南省,420000: 湖北省,430000: 湖南省,440000: 广东省,450000: 广西壮族自治区,460000: 海南省,500000: 重庆市,510000: 四川省,520000: 贵州省,530000: 云南省,540000: 西藏自治区,610000: 陕西省,620000: 甘肃省,630000: 青海省,640000: 宁夏回族自治区,650000: 新疆维吾尔自治区,710000: 台湾,810000: 香港特别行政区,820000: 澳门特别行政区,900000: 钓鱼岛";
        String[] splitArr = str.split(",");
        for(String split : splitArr){
            String[] arr = split.split(":");
            String code = arr[0].trim();
            String name = arr[1].trim();
            String sql = "INSERT INTO `test`.`organ_province_map` (`code`, `name`) VALUES ('"+code+"', '"+name+"');";
            System.out.println(sql);
        }
    }

    @Test
    public void poiTestMethod() throws Exception {
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/机构列表_1105.xlsx";
        FileInputStream fis = new FileInputStream(pathStr);
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *    1.XSSFWorkbook
         *    2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        HashMap<String,String> provinces = new HashMap<>();
        String provinceStr = "110000: 北京市,120000: 天津市,130000: 河北省,140000: 山西省,150000: 内蒙古自治区,210000: 辽宁省,220000: 吉林省,230000: 黑龙江省,310000: 上海市,320000: 江苏省,330000: 浙江省,340000: 安徽省,350000: 福建省,360000: 江西省,370000: 山东省,410000: 河南省,420000: 湖北省,430000: 湖南省,440000: 广东省,450000: 广西壮族自治区,460000: 海南省,500000: 重庆市,510000: 四川省,520000: 贵州省,530000: 云南省,540000: 西藏自治区,610000: 陕西省,620000: 甘肃省,630000: 青海省,640000: 宁夏回族自治区,650000: 新疆维吾尔自治区,710000: 台湾,810000: 香港特别行政区,820000: 澳门特别行政区,900000: 钓鱼岛";
        String[] splitArr = provinceStr.split(",");
        for(String str : splitArr){
            String[] split = str.split(":");
            provinces.put(split[1].trim(),split[0].trim());
        }
        System.out.println("provinces size :" + provinces.size());

        // 获得第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rows; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            // 获取第0格数据
            short lastCellNum = sheetRow.getLastCellNum();
//            for (int j = 2; j < lastCellNum; j++) {
//                XSSFCell cell = sheetRow.getCell(j);
//                // 调用toString方法获取内容
//                System.out.print(cell + "\t");
//            }
            String organIdShow = sheetRow.getCell(0).toString().trim();
            String organName = sheetRow.getCell(2).toString().trim();
            String provinceName = sheetRow.getCell(3).toString().trim();
            String provinceCode = "";
            if("/".equals(provinceName)){
                provinceCode = "";
            }else{
                provinceCode =  MapUtils.getString(provinces,provinceName);
            }

            String organClassifyName = sheetRow.getCell(6).toString().trim();
            int organClassifyNameCode = BusinessEnum.OrganClassify.getCodeByName(organClassifyName);

            String organNatureName = sheetRow.getCell(7).toString().trim();
            int organNatureNameCode = BusinessEnum.OrganNature.getCodeByName(organNatureName);

            String hemopathy = sheetRow.getCell(8).toString().trim();
            int hemopathyCode = BusinessEnum.PropertyStatus.getCodeByDesc(hemopathy);
            String solidTumor = sheetRow.getCell(9).toString().trim();
            int solidTumorCode = BusinessEnum.PropertyStatus.getCodeByDesc(solidTumor);
            String nationalPoint = sheetRow.getCell(10).toString().trim();
            int nationalPointCode = BusinessEnum.PropertyStatus.getCodeByDesc(nationalPoint);
            String fotonCenter = sheetRow.getCell(11).toString().trim();
            int fotonCenterCode = BusinessEnum.PropertyStatus.getCodeByDesc(fotonCenter);

            String sql = "INSERT INTO `test`.`control_organ_info` (`organ_id_show`, `organ_name`, `organ_classify`, `organ_nature`, `organ_province`, `hemopathy`, `solid_tumor`, `national_point`, `foton_center`, `organ_status`, `create_user`, `create_time`, `update_time`) VALUES ('"+organIdShow+"', '"+organName+"', '"+organClassifyNameCode+"', '"+organNatureNameCode+"', '"+provinceCode+"', '"+hemopathyCode+"', '"+solidTumorCode+"', '"+nationalPointCode+"', '"+fotonCenterCode+"', '1', '1', '2019-11-01 13:36:55', '2019-11-01 13:36:55');";
            System.out.println(sql);
        }
    }


    @Test
    public void poiTestMethod2() throws Exception {
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/area.xlsx";
        FileInputStream fis = new FileInputStream(pathStr);
        /**
         * 这里根据不同的excel类型
         * 可以选取不同的处理类：
         *    1.XSSFWorkbook
         *    2.HSSFWorkbook
         */
        // 获得工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 获得第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(2);

        int rows = sheet.getPhysicalNumberOfRows();

        Integer id = null;
        String cityName;
        Integer firstParentId = null;
        Integer secondParentId = null;
        String sql;

        for (int i = 1; i < rows; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);

            String idStr = sheetRow.getCell(0).toString().trim().replace(".0","");
            if(StringUtils.isBlank(idStr)){
                idStr = sheetRow.getCell(2).toString().trim().replace(".0","");
                if(StringUtils.isBlank(idStr)){
                    idStr = sheetRow.getCell(4).toString().trim().replace(".0","");
                    id = Integer.parseInt(idStr);
                    cityName = sheetRow.getCell(5).toString().trim();
                    sql = "INSERT INTO `city_dict` (`id`, `city_name`, `parent_id`, `city_code`) VALUES ('"+id+"', '"+cityName+"', '"+secondParentId+"', '');";
                }else{
                    id = Integer.parseInt(idStr);
                    secondParentId = id;
                    cityName = sheetRow.getCell(3).toString().trim();
                    sql = "INSERT INTO `city_dict` (`id`, `city_name`, `parent_id`, `city_code`) VALUES ('"+id+"', '"+cityName+"', '"+firstParentId+"', '');";

                }
            }else{
                id = Integer.parseInt(idStr);
                firstParentId = id;
                cityName = sheetRow.getCell(1).toString().trim();
                sql = "INSERT INTO `city_dict` (`id`, `city_name`, `parent_id`, `city_code`) VALUES ('"+id+"', '"+cityName+"', '100000', '');";
            }

            System.out.println(sql);
        }
    }
}
