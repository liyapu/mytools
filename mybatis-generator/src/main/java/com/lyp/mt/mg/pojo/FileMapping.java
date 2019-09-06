package com.lyp.mt.mg.pojo;

import java.util.Date;

public class FileMapping {
    private Integer id;

    private String fileUrl;

    private String filePath;

    private Byte retryTimes;

    private Byte valid;

    private Date createTime;

    private Date updateTime;

    public FileMapping(Integer id, String fileUrl, String filePath, Byte retryTimes, Byte valid, Date createTime, Date updateTime) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
        this.retryTimes = retryTimes;
        this.valid = valid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FileMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Byte getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Byte retryTimes) {
        this.retryTimes = retryTimes;
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