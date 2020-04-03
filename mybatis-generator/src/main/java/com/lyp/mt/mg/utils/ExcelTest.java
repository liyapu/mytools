//package com.lyp.mt.mg.utils;
//
//import com.tusdao.base.utils.Md5Utils;
//import com.tusdao.control.business.operate.UserBusiness;
//import com.tusdao.control.constants.DictConstants;
//import com.tusdao.control.dao.ExtendOrganInfoMapper;
//import com.tusdao.control.dao.ExtendUserInfoMapper;
//import com.tusdao.control.enu.BusinessEnum;
//import com.tusdao.control.model.base.ControlOrganInfo;
//import com.tusdao.control.model.base.ControlUserInfo;
//import com.tusdao.control.service.OrganService;
//import com.tusdao.control.service.UserService;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
///**
// * @author: liyapu
// * @description:
// * @date 2020-01-19 11:03
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ExcelTest {
//
//    @Autowired
//    OrganService organService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserBusiness userBusiness;
//    @Autowired
//    ExtendOrganInfoMapper extendOrganInfoMapper;
//    @Autowired
//    ExtendUserInfoMapper extendUserInfoMapper;
//
//
//    Logger log = LoggerFactory.getLogger(getClass());
//
//    //不同环境，这两个角色id可能需要修改
//    //录入员角色 id
//    static long ROLE_LURU = 139L;
//    //审核员角色 id
//    static long ROLE_SHENHE = 140L;
//    //监测点管理员id
//    static long USER_ID_ADMIN = 443L;
//
//    //数据库中创建时间唯一约束，不想修改数据库了，这里修改创建时间
//    static Long createTime  = System.currentTimeMillis();
//    static Long addTime  = 2000L;
//
//    @Test
//    public void test01(){
//        System.out.println("测试测试aaaaaaaaaaa");
//    }
//
//    /**
//     * 批量导入用户
//     */
//    @Test
//    public void insertBatchUser() throws IOException, InterruptedException {
//        String filePath = getExcelFilePath();
//        File file = new File(filePath);
//        InputStream inputStream = new FileInputStream(file);
//        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
//
//        // 获取医院列表信息
//        List<ControlOrganInfo> organInfoList = getOrganInfoList(wb);
//        //医院列表添加到数据库
//        int successCountOrgan = insertOrganInfoDb(organInfoList);
//        log.info("=================成功添加机构 {} 家",successCountOrgan);
//
//        //获取用户信息列表
//        List<ControlUserInfo> userInfoList = getUserInfoList(wb);
//        //保存用户信息到数据库
//        int successCountUser = insertUserInfoList(userInfoList);
//        log.info("=================成功添加用户 {} 个",successCountUser);
//
//
//        if(successCountUser >= 1){
//            //绑定用户角色信息到数据库
//            int successCountRole = insetUserRole(userInfoList);
//            log.info("=================成功绑定角色 {} 个",successCountRole);
//        }
//
//
////        log.info("--------机构-------");
////        log.info(JSONObject.toJSONString(organInfoList));
////        log.info("--------用户-------");
////        log.info(JSONObject.toJSONString(userInfoList));
//    }
//
//    private String getExcelFilePath() {
////        获取 excel 文件的位置
//        String userDir = System.getProperty("user.dir");
//        String filePath = userDir + File.separator + "build" + File.separator + "resources" + File.separator + "test" + File.separator + "批量用准备.xlsx";
//
////        System.out.println("filePath = " + filePath);
//        return filePath;
//    }
//
//
//    private List<ControlOrganInfo>  getOrganInfoList(XSSFWorkbook wb) {
//        List<ControlOrganInfo> organInfoList = new ArrayList<>();
//
//        //读取医院列表sheet页
//        XSSFSheet sheet = wb.getSheet("医院");
//
//        int lastRowNum = sheet.getLastRowNum();
//        log.info("机构列表 lastRowNum = {}",lastRowNum);
//
//        //跳过标题行
//        for(int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++){
//            Row row = sheet.getRow(rowIndex);
//            int cellIndex = 0;
//            //创建机构对象
//            ControlOrganInfo organInfo = new ControlOrganInfo();
//            organInfo.setOrganName(getCellValue(row.getCell(cellIndex++)));
//            organInfo.setOrganProvince(getCellValue(row.getCell(cellIndex++)));
//            cellIndex++;
//            organInfo.setOrganCode(getCellValue(row.getCell(cellIndex++)));
//            organInfo.setOrganClassify((byte) BusinessEnum.OrganClassify.MONITOR_POINT.getCode());
//            organInfo.setOrganNature((byte)BusinessEnum.OrganNature.MATERNAL_CHILD_HOSPITAL.getCode());
//            organInfo.setHemopathy((byte)BusinessEnum.PropertyStatus.NO.getCode());
//            organInfo.setSolidTumor((byte)BusinessEnum.PropertyStatus.NO.getCode());
//            organInfo.setNationalPoint((byte)BusinessEnum.PropertyStatus.YES.getCode());
//            organInfo.setFotonCenter((byte)BusinessEnum.PropertyStatus.YES.getCode());
//            organInfo.setOrganStatus((byte)BusinessEnum.StatusEnum.YES.getCode());
//
//            organInfo.setCreateUser(USER_ID_ADMIN);
//
//            // 创建时间
//            createTime += addTime;
//            Date date = new Date(createTime);
//            organInfo.setCreateTime(date);
//            organInfo.setUpdateTime(date);
//
//            organInfoList.add(organInfo);
//        }
//        return organInfoList;
//    }
//
//
//    public static String getCellValue(Cell cell) {
//        String cellValue = "";
//        if (cell == null) {
//            return cellValue;
//        }
//        //判断数据的类型
//        switch (cell.getCellType()) {
//            case NUMERIC: //数字
//                cellValue = stringDateProcess(cell);
//                break;
//            case STRING: //字符串
//                cellValue = String.valueOf(cell.getStringCellValue());
//                break;
//            case BOOLEAN: //Boolean
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
//                break;
//            case BLANK: //空值
//                cellValue = "";
//                break;
//            case ERROR: //故障
//                cellValue = "非法字符";
//                break;
//            default:
//                cellValue = "未知类型";
//                break;
//        }
//        return cellValue;
//    }
//
//
//    public static String stringDateProcess(Cell cell) {
//        String result = new String();
//        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
//            SimpleDateFormat sdf = null;
//            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//                sdf = new SimpleDateFormat("HH:mm");
//            } else {// 日期
//                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            }
//            Date date = cell.getDateCellValue();
//            result = sdf.format(date);
//        } else if (cell.getCellStyle().getDataFormat() == 58) {
//            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            double value = cell.getNumericCellValue();
//            Date date = org.apache.poi.ss.usermodel.DateUtil
//                    .getJavaDate(value);
//            result = sdf.format(date);
//        } else {
//            double value = cell.getNumericCellValue();
//            CellStyle style = cell.getCellStyle();
//            DecimalFormat format = new DecimalFormat();
//            String temp = style.getDataFormatString();
//            // 单元格设置成常规
//            if (temp.equals("General")) {
//                format.applyPattern("#");
//            }
//            result = format.format(value);
//        }
//        return result;
//    }
//
//
//    private int insertOrganInfoDb(List<ControlOrganInfo> organInfoList) {
//        int successCount = 0;
//        for(ControlOrganInfo organInfo : organInfoList){
//            //检查机构code和名称的唯一性
//            if (!organService.uniCheckOrganCode(organInfo.getOrganCode())) {
//                log.warn("批量创建失败----添加机构失败---机构编码重复 organCode = " + organInfo.getOrganCode());
//                continue;
//            }
//            if (!organService.uniCheckOrganName(organInfo.getOrganName())) {
//                log.warn("批量创建失败----添加机构失败---机构名称重复 organName = " + organInfo.getOrganName());
//                continue;
//            }
//
//            String nextOrganIdShow = organService.obtainNextOrganIdShow(organInfo.getOrganProvince());
//            organInfo.setOrganIdShow(nextOrganIdShow);
//            organInfo.setAppId(nextOrganIdShow);
//            organInfo.setAppSecret(SaltUtils.getAppSecret());
//
//            int rows = extendOrganInfoMapper.insertSelective(organInfo);
//            if(rows >= 1){
//                successCount++;
//            }
//        }
//        return successCount;
//    }
//
//
//    private List<ControlUserInfo> getUserInfoList(XSSFWorkbook wb) throws InterruptedException {
//        List<ControlUserInfo> userInfoList = new ArrayList<>();
//        //获取 用户sheet页
//        XSSFSheet sheet = wb.getSheet("用户");
//
//        int lastRowNum = sheet.getLastRowNum();
//        log.info("用户信息 lastRowNum = " + lastRowNum);
//
//        //跳过标题行
//        for(int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++){
//            Row row = sheet.getRow(rowIndex);
//            int cellIndex = 0;
//            //创建用户对象
//            ControlUserInfo userInfo = new ControlUserInfo();
//            userInfo.setUserName(getCellValue(row.getCell(cellIndex++)));
//            String userPhone = getCellValue(row.getCell(cellIndex++));
//            userInfo.setUserPhone(userPhone);
//            //校验手机号是否重复
//            boolean existResult = userService.judgeUserExist(userPhone);
//            if (existResult) {
//                log.warn("批量创建失败----用户 {}, phone:{},此手机号已存在，此用户数据无效",userInfo.getUserName(),userPhone);
//                continue;
//            }
//
//            //设置密码
//            userInfo.setPassword(Md5Utils.generate(getCellValue(row.getCell(cellIndex++))));
//            //用户标签
//            userInfo.setUserTag(NumberUtils.toByte(getCellValue(row.getCell(cellIndex++)),(byte)2));
//
//            //获取机构名字
//            String organName = getCellValue(row.getCell(cellIndex++));
//            ControlOrganInfo queryOrgan = new ControlOrganInfo();
//            queryOrgan.setOrganName(organName);
//            ControlOrganInfo organInfoDb = extendOrganInfoMapper.selectBySelective(queryOrgan);
//            if(organInfoDb == null){
//                log.warn("批量创建失败----用户 {}, phone:{},的机构为空，此用户数据无效",userInfo.getUserName(),userInfo.getUserPhone());
//                continue;
//            }
//            Long organId = organInfoDb.getOrganId();
//            userInfo.setOrganId(organId);
//
//            // 创建时间
//            createTime += addTime;
//            Date date = new Date(createTime);
//            userInfo.setCreateTime(date);
//            userInfo.setUpdateTime(date);
//            //设置用户为启用状态
//            userInfo.setUserStatus(DictConstants.USER_STATUS.STATUS_VALID);
//            //创建人
//            userInfo.setCreateUser(USER_ID_ADMIN);
//            userInfo.setUserEmail(userInfo.getUserPhone()+"@163.com");
//            userInfo.setIdCard("110101199003077934");
//
//            //将用户对象保存到集合中
//            userInfoList.add(userInfo);
//        }
//        return userInfoList;
//    }
//
//
//    private int insertUserInfoList(List<ControlUserInfo> userInfoList) {
//        int successCount = 0;
//        for(ControlUserInfo userInfo : userInfoList){
//            String nextUserIdShow = userBusiness.obtainNextUserIdShow(userInfo.getOrganId());
//            userInfo.setUserIdShow(nextUserIdShow);
//            int rows = extendUserInfoMapper.insert(userInfo);
//            if(rows >= 1){
//                successCount++;
//            }
//        }
//        return successCount;
//    }
//
//    private int insetUserRole(List<ControlUserInfo> userInfoList) {
//        int successCount = 0;
//        boolean status = false;
//        for(ControlUserInfo userInfo : userInfoList){
//            Long userId = userInfo.getUserId();
//            Byte userTag = userInfo.getUserTag();
//            Long organId = userInfo.getOrganId();
//            if(BusinessEnum.UserTag.ENTRY.getCode() == userTag){
//                //新增录入员
//                Long [] roleArr = {ROLE_LURU};
//                status = userBusiness.bindUserRoles(userId, roleArr, organId, USER_ID_ADMIN);
//            }else if(BusinessEnum.UserTag.ADMIN.getCode() == userTag){
//                //新增审核员
//                Long [] roleArr = {ROLE_SHENHE};
//                status = userBusiness.bindUserRoles(userId,roleArr,organId,USER_ID_ADMIN);
//            }else{
//                //新增录加审
//                Long [] roleArr = {ROLE_LURU,ROLE_SHENHE};
//                status = userBusiness.bindUserRoles(userId,roleArr,organId,USER_ID_ADMIN);
//            }
//
//            if(status){
//                successCount++;
//            }
//        }
//        return successCount;
//    }
//
//
//
//}
