package com.lyp.mt.mg.dao;

import com.lyp.mt.mg.pojo.FileMapping;
import java.util.List;

public interface FileMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileMapping record);

    FileMapping selectByPrimaryKey(Integer id);

    List<FileMapping> selectAll();

    int updateByPrimaryKey(FileMapping record);
}