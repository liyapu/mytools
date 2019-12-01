package com.tusdao.mapper.base;

import com.tusdao.entity.base.DownloadArea;
import com.tusdao.entity.base.DownloadAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DownloadAreaMapper {
    long countByExample(DownloadAreaExample example);

    int deleteByExample(DownloadAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadArea record);

    int insertSelective(DownloadArea record);

    List<DownloadArea> selectByExample(DownloadAreaExample example);

    DownloadArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadArea record, @Param("example") DownloadAreaExample example);

    int updateByExample(@Param("record") DownloadArea record, @Param("example") DownloadAreaExample example);

    int updateByPrimaryKeySelective(DownloadArea record);

    int updateByPrimaryKey(DownloadArea record);
}