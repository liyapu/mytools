package com.lyp.mt.mg.dao;

import com.lyp.mt.mg.pojo.TableShowField;
import java.util.List;

public interface TableShowFieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TableShowField record);

    TableShowField selectByPrimaryKey(Integer id);

    List<TableShowField> selectAll();

    int updateByPrimaryKey(TableShowField record);
}