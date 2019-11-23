package com.tusdao.mapper.base;

import com.tusdao.entity.base.QuestionAnswer;
import com.tusdao.entity.base.QuestionAnswerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionAnswerMapper {
    long countByExample(QuestionAnswerExample example);

    int deleteByExample(QuestionAnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionAnswer record);

    int insertSelective(QuestionAnswer record);

    List<QuestionAnswer> selectByExample(QuestionAnswerExample example);

    QuestionAnswer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionAnswer record, @Param("example") QuestionAnswerExample example);

    int updateByExample(@Param("record") QuestionAnswer record, @Param("example") QuestionAnswerExample example);

    int updateByPrimaryKeySelective(QuestionAnswer record);

    int updateByPrimaryKey(QuestionAnswer record);
}