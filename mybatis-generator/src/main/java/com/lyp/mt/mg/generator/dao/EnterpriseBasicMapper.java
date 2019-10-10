package com.lyp.mt.mg.generator.dao;

import com.lyp.mt.mg.generator.pojo.EnterpriseBasic;
import java.util.List;

public interface EnterpriseBasicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseBasic record);

    EnterpriseBasic selectByPrimaryKey(Long id);

    List<EnterpriseBasic> selectAll();

    int updateByPrimaryKey(EnterpriseBasic record);
}