package com.tusdao.control.dao.base;

import com.tusdao.control.model.base.ControlOrganInfo;
import com.tusdao.control.model.base.ControlOrganInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ControlOrganInfoMapper {
    long countByExample(ControlOrganInfoExample example);

    int deleteByExample(ControlOrganInfoExample example);

    int deleteByPrimaryKey(Long organId);

    int insert(ControlOrganInfo record);

    int insertSelective(ControlOrganInfo record);

    List<ControlOrganInfo> selectByExample(ControlOrganInfoExample example);

    ControlOrganInfo selectByPrimaryKey(Long organId);

    int updateByExampleSelective(@Param("record") ControlOrganInfo record, @Param("example") ControlOrganInfoExample example);

    int updateByExample(@Param("record") ControlOrganInfo record, @Param("example") ControlOrganInfoExample example);

    int updateByPrimaryKeySelective(ControlOrganInfo record);

    int updateByPrimaryKey(ControlOrganInfo record);
}