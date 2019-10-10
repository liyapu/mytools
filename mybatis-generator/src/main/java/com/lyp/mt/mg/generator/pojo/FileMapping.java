package com.lyp.mt.mg.generator.pojo;

import lombok.Data;

/**
 * 文件上传映射表
 *
 * @author liyapu
 * @date 2019-10-10 04:17:38
 */
@Data
public class FileMapping {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 原始文件路径
     */
    private String fileUrl;

    /**
     * oss上存储文件路径
     */
    private String filePath;

    /**
     * 重试次数
     */
    private Byte retryTimes;
}