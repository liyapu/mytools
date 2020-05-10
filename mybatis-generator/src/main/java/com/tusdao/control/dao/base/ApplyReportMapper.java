package com.tusdao.control.dao.base;

import com.tusdao.control.model.base.ApplyReport;
import com.tusdao.control.model.base.ApplyReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyReportMapper {
    long countByExample(ApplyReportExample example);

    int deleteByExample(ApplyReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApplyReport record);

    int insertSelective(ApplyReport record);

    List<ApplyReport> selectByExample(ApplyReportExample example);

    ApplyReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApplyReport record, @Param("example") ApplyReportExample example);

    int updateByExample(@Param("record") ApplyReport record, @Param("example") ApplyReportExample example);

    int updateByPrimaryKeySelective(ApplyReport record);

    int updateByPrimaryKey(ApplyReport record);
}