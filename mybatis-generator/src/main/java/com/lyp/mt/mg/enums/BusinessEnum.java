package com.lyp.mt.mg.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: liyapu
 * @description: 业务常量
 * @date 2019-11-05 13:35
 */
public interface BusinessEnum {

    /**
     * 机构分类枚举
     */
     enum OrganClassify{
         NATIONAL_COMMITTEE(1,"员会"),
         National_Monitor(2,"测中心"),
         PROVINCIAL_COMMITTEES(3,"委"),
         Monitor_point(4,"点");

         private int code;
         private String name;

         OrganClassify(int code,String name){
             this.code = code;
             this.name = name;
         }

         public int getCode() {
             return code;
         }

         public void setCode(int code) {
             this.code = code;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public static int getCodeByName(String name){
             OrganClassify[] values = OrganClassify.values();
             for(OrganClassify oc : values){
                 if(oc.getName().equals(name)){
                     return oc.getCode();
                 }
             }
             return Monitor_point.getCode();
         }

         public static String getNameByCode(int code){
             OrganClassify[] values = OrganClassify.values();
             for(OrganClassify oc : values){
                 if(oc.getCode() == code){
                     return oc.getName();
                 }
             }
             return Monitor_point.getName();
         }

         public static List<String> getAllNames(){
             return Arrays.stream(OrganClassify.values())
                     .map(o -> o.getName())
                     .collect(Collectors.toList());
         }
     }

    /**
     * 机构性质枚举
     */
    enum OrganNature{
         UNKNOWN(0,"未知"),
         MANAGEMENT_ORGAN(1,"管理机构"),
         GENERAL_HOSPITAL(2,"性医院"),
         MATERNAL_CHILD_HOSPITAL(3,"儿童专科医院"),
         CANCER_HOSPITAL(4,"科医院");

         private int code;
         private String name;

         OrganNature(int code,String name){
             this.code = code;
             this.name = name;
         }

         public int getCode() {
             return code;
         }

         public void setCode(int code) {
             this.code = code;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public static int getCodeByName(String name){
             OrganNature[] values = OrganNature.values();
             for(OrganNature on : values){
                 if(on.getName().equals(name)){
                     return on.getCode();
                 }
             }
             return UNKNOWN.getCode();
         }

         public static String getNameByCode(int code){
             OrganNature[] values = OrganNature.values();
             for(OrganNature on : values){
                 if(on.getCode() == code){
                     return on.getName();
                 }
             }
             return UNKNOWN.getName();
         }

         public static List<String> getAllNames(){
             return Arrays.stream(OrganNature.values())
                     .map(o -> o.getName())
                     .collect(Collectors.toList());
         }
     }

    /**
     * 属性状态枚举值
     */
    enum PropertyStatus{
         NONE(0,"无"),
         NO(1,"否"),
         YES(2,"是");

         private int code;
         private String desc;
         PropertyStatus(int code,String desc){
             this.code = code;
             this.desc = desc;
         }

         public int getCode() {
             return code;
         }

         public void setCode(int code) {
             this.code = code;
         }

         public String getDesc() {
             return desc;
         }

         public void setDesc(String desc) {
             this.desc = desc;
         }

         public static int getCodeByDesc(String desc){
             PropertyStatus[] values = PropertyStatus.values();
             for(PropertyStatus ps : values){
                 if(ps.getDesc().equals(desc)){
                     return ps.getCode();
                 }
             }
             return NONE.getCode();
         }

         public static String getDescByCode(int code){
             PropertyStatus[] values = PropertyStatus.values();
             for(PropertyStatus ps : values){
                 if(ps.getCode() == code){
                     return ps.getDesc();
                 }
             }
             return NONE.getDesc();
         }
     }
}
