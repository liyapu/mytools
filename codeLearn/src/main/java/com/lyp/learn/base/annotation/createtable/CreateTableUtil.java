package com.lyp.learn.base.annotation.createtable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-08 22:37
 */
public class CreateTableUtil {

    public static void createTable(Class<?> clazz){
        boolean isDbTableAnnotation = clazz.isAnnotationPresent(DBTable.class);
        if(!isDbTableAnnotation){
            System.out.println("不是 DBTable 注解");
            return;
        }

        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        String tableName = dbTable.name();

        List<String> columnList = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            Annotation[] fieldAnnotations = field.getAnnotations();
            if(fieldAnnotations.length == 0){
                continue;
            }

            //处理SqlInteger注解
            if(fieldAnnotations[0] instanceof SQLInteger){
                SQLInteger sqlInteger = (SQLInteger) fieldAnnotations[0];
                String nameInt = sqlInteger.name();
                String constraintsDesc = getConstraint(sqlInteger.constraint());
                columnList.add(nameInt + " INT " + constraintsDesc);
            }

            //处理SqlString注解
            if(fieldAnnotations[0] instanceof SQLString){
                SQLString sqlString = (SQLString)fieldAnnotations[0];
                String nameStr = sqlString.name();
                int value = sqlString.value();
                String constraintsDesc = getConstraint(sqlString.constraint());
                columnList.add(nameStr + " VARCHAR(" + value + ") " + constraintsDesc);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(tableName)
                .append("(");
        for(String column : columnList){
            sb.append("\n" + column + ",");
        }
        String finalTable = sb.substring(0,sb.length()-1) + "\n);";
        System.out.println(finalTable);
    }

    /**
     * 判断该字段是否有其他约束
     * @param constraints
     * @return
     */
    private static String getConstraint(Constraints constraints){
        String constraintsDesc = "";
        if(constraints.primaryKey()){
            constraintsDesc += " PRIMARY KEY ";
        }
        if(constraints.unique()){
            constraintsDesc += " UNIQUE ";
        }
        if(!constraints.allowNull()){
            constraintsDesc += " NOT NULL ";
        }
        return constraintsDesc;
    }
}
