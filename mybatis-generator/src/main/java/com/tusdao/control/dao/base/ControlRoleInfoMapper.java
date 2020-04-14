package com.tusdao.control.dao.base;

import com.tusdao.control.model.base.ControlRoleInfo;
import com.tusdao.control.model.base.ControlRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ControlRoleInfoMapper {
    long countByExample(ControlRoleInfoExample example);

    int deleteByExample(ControlRoleInfoExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(ControlRoleInfo record);

    int insertSelective(ControlRoleInfo record);

    List<ControlRoleInfo> selectByExample(ControlRoleInfoExample example);

    ControlRoleInfo selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") ControlRoleInfo record, @Param("example") ControlRoleInfoExample example);

    int updateByExample(@Param("record") ControlRoleInfo record, @Param("example") ControlRoleInfoExample example);

    int updateByPrimaryKeySelective(ControlRoleInfo record);

    int updateByPrimaryKey(ControlRoleInfo record);
}