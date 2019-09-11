package com.lyp.learn.base.annotation.quserysql;

import java.lang.reflect.Field;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 20:42
 */
public class TableUtil {

    public static void getQuerySql(Object obj) throws Exception{
        Class clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");

        //1. 获取表名
        boolean isTableAnnotation = clazz.isAnnotationPresent(Table.class);
        if(isTableAnnotation){
            Table table = (Table) clazz.getDeclaredAnnotation(Table.class);
            String tableName = table.value();
            sb.append(tableName);
        }

        sb.append(" where 1=1 ");

        //2. 获取字段名和字段值
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            //2.1 字段名
            String columnName = "";
            boolean isColumnAnnotation = field.isAnnotationPresent(Column.class);
            if(isColumnAnnotation){
                Column column = field.getAnnotation(Column.class);
                columnName = column.value();
            }
            //2.2 字段值
            field.setAccessible(true);
            Object fieldValue = field.get(obj);

            if(fieldValue != null){
                sb.append(" and ").append(columnName).append(" = ");
                if(fieldValue instanceof  String){
                    sb.append("'").append(fieldValue).append("'");
                }else{
                    sb.append(fieldValue);
                }
            }
        }

        System.out.println(sb.toString());
    }

}
