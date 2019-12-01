package com.tusdao.mapper.base;

import com.tusdao.entity.base.NewsCenter;
import com.tusdao.entity.base.NewsCenterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsCenterMapper {
    long countByExample(NewsCenterExample example);

    int deleteByExample(NewsCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsCenter record);

    int insertSelective(NewsCenter record);

    List<NewsCenter> selectByExampleWithBLOBs(NewsCenterExample example);

    List<NewsCenter> selectByExample(NewsCenterExample example);

    NewsCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsCenter record, @Param("example") NewsCenterExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsCenter record, @Param("example") NewsCenterExample example);

    int updateByExample(@Param("record") NewsCenter record, @Param("example") NewsCenterExample example);

    int updateByPrimaryKeySelective(NewsCenter record);

    int updateByPrimaryKeyWithBLOBs(NewsCenter record);

    int updateByPrimaryKey(NewsCenter record);
}