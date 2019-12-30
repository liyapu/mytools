package com.lyp.mt.mg.utils;

import com.lyp.mt.enums.BusinessEnum;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
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
    @Test
    public void testObtainProvince() throws Exception {
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/儿童医院机构列表_1105.xlsx";
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
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/儿童医院机构列表_1105.xlsx";
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
        String pathStr = "/Users/liyapu/myGitRepository/mytools/mybatis-generator/src/main/resources/儿童医院机构列表_1105.xlsx";
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

/**
 *
 provinces size :33
 机构名称	省份	市	法人代表	机构分类	机构性质	是否血液病	是否实体瘤	是否国家定点	是否福棠中心
 国家卫生健康委员会	/	/	/	国家卫生健康委员会	管理机构	/	/	/	/
 国家儿童肿瘤监测中心	/	/	/	国家儿童肿瘤监测中心	管理机构	/	/	/	/
 北京市卫生健康委员会	北京市	/	/	省、市级卫健委	管理机构	/	/	/	/
 天津市卫生健康委员会	天津市	/	/	省、市级卫健委	管理机构	/	/	/	/
 河北省卫生健康委员会	河北省	/	/	省、市级卫健委	管理机构	/	/	/	/
 山西省卫生健康委员会	山西省	/	/	省、市级卫健委	管理机构	/	/	/
 内蒙古自治区卫生健康委员会	内蒙古自治区	/	/	省、市级卫健委	管理机构	/	/	/	/
 辽宁省卫生健康委员会	辽宁省	/	/	省、市级卫健委	管理机构	/	/	/	/
 吉林省卫生健康委员会	吉林省	/	/	省、市级卫健委	管理机构	/	/	/	/
 黑龙江省卫生健康委员	黑龙江省	/	/	省、市级卫健委	管理机构	/	/	/	/
 上海市卫生健康委员会	上海市	/	/	省、市级卫健委	管理机构	/	/	/	/
 江苏省卫生健康委员会	江苏省	/	/	省、市级卫健委	管理机构	/	/	/	/
 浙江省卫生健康委员会	浙江省	/	/	省、市级卫健委	管理机构	/	/	/	/
 安徽省卫生健康委员会	安徽省	/	/	省、市级卫健委	管理机构	/	/	/	/
 福建省卫生健康委员会	福建省	/	/	省、市级卫健委	管理机构	/	/	/	/
 江西省卫生健康委员会	江西省	/	/	省、市级卫健委	管理机构	/	/	/	/
 山东省卫生健康委员会	山东省	/	/	省、市级卫健委	管理机构	/	/	/	/
 河南省卫生健康委员会	河南省	/	/	省、市级卫健委	管理机构	/	/	/	/
 湖北省卫生健康委员会	湖北省	/	/	省、市级卫健委	管理机构	/	/	/	/
 湖南省卫生健康委员会	湖南省	/	/	省、市级卫健委	管理机构	/	/	/	/
 广东省卫生健康委员会	广东省	/	/	省、市级卫健委	管理机构	/	/	/	/
 广西壮族自治区卫生健康委员会	广西壮族自治区	/	/	省、市级卫健委	管理机构	/	/	/	/
 海南省卫生健康委员会	海南省	/	/	省、市级卫健委	管理机构	/	/	/	/
 重庆市卫生健康委员会	重庆市	/	/	省、市级卫健委	管理机构	/	/	/	/
 四川省卫生健康委员会	四川省	/	/	省、市级卫健委	管理机构	/	/	/	/
 贵州省卫生健康委员会	贵州省	/	/	省、市级卫健委	管理机构	/	/	/	/
 云南省卫生健康委员会	云南省	/	/	省、市级卫健委	管理机构	/	/	/	/
 西藏自治区卫生健康委员会	西藏自治区	/	/	省、市级卫健委	管理机构	/	/	/	/
 陕西省卫生健康委员会	陕西省	/	/	省、市级卫健委	管理机构	/	/	/	/
 甘肃省卫生健康委员会	甘肃省	/	/	省、市级卫健委	管理机构	/	/	/	/
 青海省卫生健康委员会	青海省	/	/	省、市级卫健委	管理机构	/	/	/	/
 宁夏回族自治区卫生健康委员会	宁夏回族自治区	/	/	省、市级卫健委	管理机构	/	/	/	/
 新疆维吾尔自治区卫生健康委员会	新疆维吾尔自治区	/	/	省、市级卫健委	管理机构	/	/	/	/
 新疆生产建设兵团卫生健康委员会	新疆生产建设兵团	/	/	省、市级卫健委	管理机构	/	/	/	/
 沈阳市儿童医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	是
 内蒙古自治区妇幼保健院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	是
 深圳市儿童医院	广东省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	是
 广州市妇女儿童医疗中心	广东省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 贵阳市妇幼保健院	贵州省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 湖南省儿童医院	湖南省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 江西省儿童医院	江西省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 安徽省儿童医院	安徽省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 四川大学华西第二医院	四川省	#N/A	#N/A	监测点	综合性医院	是	是	是	是
 河南省儿童医院	河南省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 西安市儿童医院	陕西省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 甘肃省妇幼保健院	甘肃省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 青海省妇女儿童医院	青海省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 山西省儿童医院	山西省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 天津市儿童医院	天津市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 大连市儿童医院	辽宁省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 哈尔滨市儿童医院	黑龙江省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 乌鲁木齐儿童医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 长春市儿童医院	吉林省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 武汉儿童医院	湖北省	#N/A	#N/A	监测点	#N/A	是	是	是	是
 首都医科大学附属北京儿童医院	北京市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 昆明市儿童医院	云南省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 南京市儿童医院	江苏省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 苏州大学附属儿童医院	江苏省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 河北省儿童医院	河北省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	是
 中山大学肿瘤防治中心	广东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 广东省妇幼保健院	广东省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 中山大学眼科中心	广东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 南方医科大学附属第三医院	广东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 钦州市第一人民医院	广西壮族自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 梧州市红十字会医院	广西壮族自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 湖南省肿瘤医院	湖南省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 南昌大学第二附属医院	江西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 江西省肿瘤医院	江西省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 江西省人民医院	江西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 金华市中心医院	浙江省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 浙江省肿瘤医院	浙江省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 浙江大学医学院附属第二医院	浙江省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 蚌埠医学院第一附属医院	安徽省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 安徽省第二人民医院	安徽省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 安徽医科大学第一附属医院	安徽省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 皖南医学院弋矶山医院	安徽省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 四川省肿瘤医院	四川省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 四川大学华西医院	四川省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 郑州大学第三附属医院	河南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河南中医药大学第一附属医院	河南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 新乡医学院第一附属医院	河南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 郑州大学第二附属医院	河南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河南省人民医院	河南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 西安交通大学第一附属医院	陕西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 陕西省人民医院	陕西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 唐都医院	陕西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 甘肃省武威肿瘤医院	甘肃省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 定西市人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 天水市人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河西学院附属张掖人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 武威市人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 临夏州人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 青海省第五人民医院	青海省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 青海省人民医院	青海省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 山西省眼科医院	山西省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 天津市肿瘤医院	天津市	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 大连医科大学附属第二医院	辽宁省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 辽宁省肿瘤医院	辽宁省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 哈尔滨医科大学附属第二医院	黑龙江省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 哈尔滨医科大学附属肿瘤医院	黑龙江省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 内蒙古医科大学附属医院	内蒙古自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 赤峰市医院	内蒙古自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 吉林大学第二医院	吉林省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 延边大学附属医院	吉林省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 吉林大学中日联谊医院	吉林省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 厦门市儿童医院	福建省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 厦门大学附属中山医院	福建省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 福建省妇幼保健院	福建省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 福建省立医院	福建省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 武汉大学中南医院	湖北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 武汉大学人民医院	湖北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 湖北省肿瘤医院	湖北省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 湖北省妇幼保健院	湖北省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 首都医科大学附属北京天坛医院	北京市	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 海南省儿童医院	海南省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 三亚市人民医院	海南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 海南省肿瘤医院	海南省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 三亚中心医院	海南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 临沂市肿瘤医院	山东省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 临沂市中心医院	山东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 临沂市人民医院	山东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 青岛市中心医院	山东省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 青岛市妇女儿童医院	山东省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 山东省肿瘤医院	山东省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 济南市儿童医院	山东省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 新疆生产建设兵团第六师医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 新疆生产建设兵团第一师阿拉尔医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 新疆生产建设兵团第三师医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 新疆生产建设兵团第七师医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 新疆生产建设兵团第十三师红星医院	新疆生产建设兵团	#N/A	#N/A	监测点	#N/A	否	是	是	否
 重庆医科大学附属永川医院	重庆市	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 重庆市肿瘤医院	重庆市	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 重庆市三峡中心医院儿童分院	重庆市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 昆明医科大学第二附属医院	云南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 云南省第二人民医院	云南省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 云南省肿瘤医院	云南省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 中卫市人民医院	宁夏回族自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 银川市第一人民医院	宁夏回族自治区	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 宁夏妇幼保健院	宁夏回族自治区	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 江苏省肿瘤医院	江苏省	#N/A	#N/A	监测点	肿瘤专科医院	否	是	是	否
 苏州大学附属第一医院	江苏省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 南京鼓楼医院	江苏省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 江苏省妇幼保健院	江苏省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	否	是	是	否
 河北省中医院	河北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河北医科大学第三医院	河北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河北医科大学第一医院	河北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 河北省人民医院	河北省	#N/A	#N/A	监测点	综合性医院	否	是	是	否
 柳州市人民医院	广西壮族自治区	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 广西壮族自治区人民医院	广西壮族自治区	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 南华大学附属第一医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 南华大学附属第二医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 郴州市第一人民医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 赣南医学院第一附属医院	江西省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 赣州市人民医院	江西省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 四川省妇幼保健院	四川省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	否	是	否
 西南医科大学附属医院	四川省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 山西白求恩医院（山西大医院）	山西省	#N/A	#N/A	监测点	#N/A	是	否	是	否
 山西医科大学第二医院	山西省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 天津中医药大学第一附属医院	天津市	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 泉州市第一医院	福建省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 首都儿科研究所附属儿童医院	北京市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	否	是	否
 山东省千佛山医院（山东第一医科大学第一附属医院）	山东省	#N/A	#N/A	监测点	#N/A	是	否	是	否
 重庆医科大学附属第二医院	重庆市	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 西藏自治区人民医院	西藏自治区	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 上海交通大学医学院附属瑞金医院	上海市	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 同济大学附属同济医院	上海市	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 海军军医大学第一附属医院（上海长海医院）	上海市	#N/A	#N/A	监测点	#N/A	是	否	是	否
 廊坊市中医医院	河北省	#N/A	#N/A	监测点	综合性医院	是	否	是	否
 南方医科大学南方医院	广东省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中山大学附属第一医院	广东省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中山大学孙逸仙纪念医院	广东省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 广西医科大学第一附属医院	广西壮族自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 贵州省人民医院	贵州省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 遵义医学院附属医院	贵州省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 贵州医科大学附属医院	贵州省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中南大学湘雅医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中南大学湘雅二医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中南大学湘雅三医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 湖南省人民医院	湖南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 南昌大学第一附属医院	江西省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 浙江大学医学院附属第一医院	浙江省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 温州医科大学附属第二医院	浙江省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 浙江大学医学院附属儿童医院	浙江省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 安徽医科大学第二附属医院	安徽省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 安徽省立医院	安徽省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 成都市妇女儿童中心医院	四川省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 四川省人民医院	四川省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 河南科技大学第一附属医院	河南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 河南省肿瘤医院	河南省	#N/A	#N/A	监测点	肿瘤专科医院	是	是	是	否
 郑州大学第一附属医院	河南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 西北妇女儿童医院	陕西省	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 西安交通大学第二附属医院	陕西省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 甘肃省人民医院	甘肃省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 兰州大学第一医院	甘肃省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 兰州大学第二医院	甘肃省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 青海大学附属医院	青海省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 山西医科大学第一医院	山西省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 山西省肿瘤医院	山西省	#N/A	#N/A	监测点	肿瘤专科医院	是	是	是	否
 山西省人民医院	山西省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中国医学科学院血液病医院	天津市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中国医科大学附属盛京医院	辽宁省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 哈尔滨医科大学附属第一医院	黑龙江省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 哈尔滨市血液病研究所	黑龙江省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 新疆维吾尔自治区人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 新疆医科大学第一附属医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 内蒙古自治区人民医院	内蒙古自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 吉林大学第一医院	吉林省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 长春中医药大学附属医院	吉林省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 厦门大学附属第一医院	福建省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 福建医科大学附属协和医院	福建省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 华中科技大学同济医学院附属同济医院	湖北省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 华中科技大学同济医学院附属协和医院	湖北省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 首都医科大学附属北京同仁医院	北京市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 首都医科大学附属北京世纪坛医院	北京市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 北京大学第一医院	北京市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 北京大学人民医院	北京市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 中国医学科学院北京协和医院	北京市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 解放军总医院海南医院	海南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 海南省人民医院	海南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 山东省立医院（山东省儿童医院）	山东省	#N/A	#N/A	监测点	#N/A	是	是	是	否
 山东大学齐鲁医院	山东省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 青岛大学附属医院	山东省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 新疆生产建设兵团医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 新疆生产建设兵团第一师医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 石河子大学医学院第一附属医院	新疆生产建设兵团	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 陆军军医大学附属第二医院（新桥医院）	重庆市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 重庆医科大学附属儿童医院	重庆市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 昆明医科大学第一附属医院	云南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 云南省第一人民医院	云南省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 宁夏医科大学总医院	宁夏回族自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 宁夏回族自治区人民医院	宁夏回族自治区	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 上海交通大学医学院附属新华医院	上海市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 上海市儿童医院	上海市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 复旦大学附属儿科医院	上海市	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 上海交通大学医学院附属上海儿童医学中心	上海市	#N/A	#N/A	监测点	儿童专科医院、妇幼保健院	是	是	是	否
 江苏省人民医院	江苏省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 河北医科大学第二医院	河北省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 河北医科大学第四医院	河北省	#N/A	#N/A	监测点	综合性医院	是	是	是	否
 辽健集团抚矿总医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 本溪市中心医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 锦州市妇婴医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 辽阳市第三人民医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 朝阳市第二医院	辽宁省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 牡丹江市肿瘤医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 双鸭山双矿医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 大庆油田总医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 齐医附属第二医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 佳木斯市中心医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 牡丹江医学院附属第二医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 鸡西市人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 鸡西鸡矿医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 鹤岗市人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 伊春林业管理局中心医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 伊春市第一医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 双鸭山市人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 黑龙江省农垦红兴隆管理局中心医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 七台河市人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 绥化市第一医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 黑河市第一人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 齐齐哈尔市第一医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 齐医附属第一医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 齐医附属第三医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 大庆市人民医院	黑龙江省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 自治区肿瘤医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 伊犁州新华医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 伊犁州友谊医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 伊犁州奎屯医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 塔城地区人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 阿勒泰地区人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 博州人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 克拉玛依市中心医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 昌吉州人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 乌鲁木齐市友谊医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 吐鲁番市人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 哈密市中心医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 哈密市第二人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 巴州人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 阿克苏地区第一人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 阿克苏地区第二人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 喀什地区第一人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 喀什地区第二人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 和田地区人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 克州人民医院	新疆维吾尔族自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 赤峰学院附属医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 内蒙古国际蒙医医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 包钢集团第三职工医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 包头市第四医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 内蒙古包钢医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 呼伦贝尔市人民医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 兴安盟人民医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 通辽市医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 内蒙古民族大学附属医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 乌兰察布市中心医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 巴彦淖尔市医院	内蒙古自治区	#N/A	#N/A	监测点	#N/A	否	否	否	否
 莆田市第一医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福建医科大学附属第一医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福州市第二医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福建省漳州市医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 泉州市儿童医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福建医科大学附属第二医院
 （省属医院 地址在泉州）	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 三明市第一医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 莆田学院附属医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福建省南平市第一医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 龙岩市第一医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 宁德市闽东医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福鼎市医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 福建省福州儿童医院	福建省	#N/A	#N/A	监测点	#N/A	否	否	否	否
 宁德市医院	福建省	#N/A	#N/A	监测点
 */
