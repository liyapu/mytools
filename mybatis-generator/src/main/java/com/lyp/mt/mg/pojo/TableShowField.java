package com.lyp.mt.mg.pojo;

import java.util.Date;

public class TableShowField {
    private Integer id;

    private String tableName;

    private String fieldName;

    private String fieldShow;

    private Integer fieldOrder;

    private Byte valid;

    private Date createTime;

    private Date updateTime;

    public TableShowField(Integer id, String tableName, String fieldName, String fieldShow, Integer fieldOrder, Byte valid, Date createTime, Date updateTime) {
        this.id = id;
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.fieldShow = fieldShow;
        this.fieldOrder = fieldOrder;
        this.valid = valid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TableShowField() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldShow() {
        return fieldShow;
    }

    public void setFieldShow(String fieldShow) {
        this.fieldShow = fieldShow == null ? null : fieldShow.trim();
    }

    public Integer getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}