package com.lyp.mt.mg.generator.dao;

import com.lyp.mt.mg.generator.pojo.Keyword;
import java.util.List;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    Keyword selectByPrimaryKey(Integer id);

    List<Keyword> selectAll();

    int updateByPrimaryKey(Keyword record);
}