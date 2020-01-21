package com.lyp.learn.easyexcel.read;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-20 17:35
 *
 * 1. 全部的字段，使用 index 索引指定与excel列的对应关系
 *    index 从0开始
 *   @ExcelProperty(index = 0)
 * 2.
 */
//@Data
public class Hospital {
//    @ExcelProperty(index = 3)
//    private String organCode;
//
//    @ExcelProperty(index = 1)
//    private Integer provinceCode;
//
//    @ExcelProperty(index = 0)
//    private String name;
//
//    @ExcelProperty(index = 2)
//    private String provinceName;
//
////    private LocalDateTime createTime;

//
//    @ExcelProperty("医院名称")
//    private String name;
//
//    @ExcelProperty("省份编码")
//    private Integer provinceCode;
//
//
//    @ExcelProperty("省份名称")
//    private String provinceName;
//
//    @ExcelProperty("组织机构代码")
//    private String organCode;
//


    private String name;

    private Integer provinceCode;


    private String provinceName;

    private String organCode;

//    private LocalDateTime createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }
}
