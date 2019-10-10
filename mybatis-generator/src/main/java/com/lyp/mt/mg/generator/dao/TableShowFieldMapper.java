package com.lyp.mt.mg.generator.dao;

import com.lyp.mt.mg.generator.pojo.TableShowField;
import java.util.List;

public interface TableShowFieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableShowField record);

    TableShowField selectByPrimaryKey(Integer id);

    List<TableShowField> selectAll();

    int updateByPrimaryKey(TableShowField record);
}