package com.tusdao.mapper.base;

import com.tusdao.entity.base.NewsAnnex;
import com.tusdao.entity.base.NewsAnnexExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsAnnexMapper {
    long countByExample(NewsAnnexExample example);

    int deleteByExample(NewsAnnexExample example);

    int deleteByPrimaryKey(Integer annexId);

    int insert(NewsAnnex record);

    int insertSelective(NewsAnnex record);

    List<NewsAnnex> selectByExample(NewsAnnexExample example);

    NewsAnnex selectByPrimaryKey(Integer annexId);

    int updateByExampleSelective(@Param("record") NewsAnnex record, @Param("example") NewsAnnexExample example);

    int updateByExample(@Param("record") NewsAnnex record, @Param("example") NewsAnnexExample example);

    int updateByPrimaryKeySelective(NewsAnnex record);

    int updateByPrimaryKey(NewsAnnex record);
}