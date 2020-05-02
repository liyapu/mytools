package com.lyp.learn.junit;

import com.google.common.base.Strings;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
public class FirstTest {

    @Test
    public void testImg(){
        String str = "        <img src=\"img/java/jvm%s.png\"/><br/>";
        int num = 151;
        for (int i = 0; i < 300; i++) {
            System.out.println(String.format(str,num++));
        }
    }

    /**
     * 测试上传附件，重命名之后，匹配 uuid 字符串
     */
    @Test
    public void testUUid2(){
//        String uuid1 = "在e65deb4c-a110-49c8-a4ef-6e69447968d6";
//        String uuid1 = "在e65deb4ca11049c8a4ef6e69447968d6";
        String uuid1 = "北京儿童医院——监测平台近期开发计划_c5cf113415be40ab8c7fab3bde192acb.xlsx";
//        String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
//        System.out.println(uuid1.matches(regex));

//        String reg = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
        Pattern p= Pattern.compile("_[0-9a-f]{32}\\.");
        Matcher m=p.matcher(uuid1);
        System.out.println(m.find());
    }


    @Test
    public void testEqual(){
//        AssertEquals.assertEquals(2, 1+1);
    }

    @Test
    public void testintByte(){
        int a = 10;
        byte b = 10;
        System.out.println(a == b);
    }

    @Test
    public void testintInteger(){
        int a = 6666;
        Integer b = 6666;
        System.out.println(a == b);
    }

    @Test
    public void testBigDecimal(){
        BigDecimal bd1 = new BigDecimal(0.1);
        System.out.println(bd1);
        BigDecimal bd2 = new BigDecimal("0.1");
        System.out.println(bd2);
    }

    @Test
    public void testIdNumber(){
        String idNum = "530111197308175070";
    }

    @Test
    public void testDot(){
        String pk = "com.lyp.learn";
        String newPk = pk.replace(".", File.separator);
        System.out.println(newPk);
    }

    @Test
    public void userIdRole(){
        String temp = "'1100','1101','1102','1103','1104','1105','1106','1107','1108','1109','1110','1111','1112','1113','1114','1115','1116','1117','1118','1119','1120','1121','1122','1123','1124','1125','1126','1127','1128','1129','1130','1131','1132','1133','1134','1135','1136','1137','1138','1139','1140','1141','1142','1143','1144','1145','1146','1147','1148','1149','1150','1151','1152','1153','1154','1155','1156','1157','1158','1159','1160','1161','1162','1163','1164','1165','1166','1167','1168','1169','1170','1171','1172','1173','1174','1175','1176','1177','1178','1179','1180','1181','1182','1183','1184','1185','1186','1187','1188','1189','1190','1191','1192','1193','1194','1195','1196','1197','1198','1199','1200','1201','1202','1203','1204','1205','1206','1207','1208','1209','1210','1211','1212','1213','1214','1215','1216','1217','1218','1219','1220','1221','1222','1223','1224','1225','1226','1227','1228','1229','1230','1231','1232','1233','1234','1235','1236','1237','1238','1239','1240','1241','1242','1243','1244','1245','1246','1247','1248','1249','1250','1251','1252','1253','1254','1255','1256','1257','1258','1259','1260','1261','1262','1263','1264','1265','1266','1267','1268','1269','1270','1271','1272','1273','1274','1275','1276','1277','1278','1279','1280','1281','1282','1283','1284','1285','1286','1287','1288','1289','1290','1291','1292','1293','1294','1295','1296','1297','1298','1299','1300','1301','1302','1303','1304','1305','1306','1307','1308','1309','1310','1311','1312','1313','1314','1315','1316','1317','1318','1319','1320','1321','1322','1323','1324','1325','1326','1327','1328','1329','1330','1331','1332','1333','1334','1335','1336','1337','1338','1339','1340','1341','1342','1343','1344','1345','1346','1347','1348','1349','1350','1351','1352','1353','1354','1355','1356','1357','1358','1359','1360','1361','1362','1363','1364','1365','1366','1367','1368','1369','1370','1371','1372','1373','1374','1375','1376','1377','1378','1379','1380','1381','1382','1383','1384','1385','1386','1387','1388','1389','1390','1391','1392','1393','1394','1395','1396','1397','1398','1399','1400','1401','1402','1403','1404','1405','1406','1407','1408','1409','1410','1411','1412','1413','1414','1415','1416','1417','1418','1419','1420','1421','1422','1098'";

        String[] arr = temp.split(",");
        System.out.println(arr.length);
//        System.out.println(arr[3]);
//        System.out.println(Integer.parseInt(arr[3]));
        // INSERT INTO `control_user_role_rel` (`user_id`, `role_id`, `rel_time`, `oper_user`) VALUES ('111', '155', '2020-04-15 14:01:23', '443');
        String sql = "INSERT INTO `control_user_role_rel` (`user_id`, `role_id`, `rel_time`, `oper_user`) VALUES (%s, '155', '%s', '443');";
        for(String s : arr){
            String result = Strings.lenientFormat(sql,s, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println(result);
        }
    }
}
