package com.haohua.erp.mapper;

import com.haohua.erp.entity.PartsStream;
import com.haohua.erp.entity.PartsStreamExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsStreamMapper {
    long countByExample(PartsStreamExample example);

    int deleteByExample(PartsStreamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartsStream record);

    int insertSelective(PartsStream record);

    List<PartsStream> selectByExample(PartsStreamExample example);

    PartsStream selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartsStream record, @Param("example") PartsStreamExample example);

    int updateByExample(@Param("record") PartsStream record, @Param("example") PartsStreamExample example);

    int updateByPrimaryKeySelective(PartsStream record);

    int updateByPrimaryKey(PartsStream record);

    List<PartsStream> findStreamWithParamMap(Map<String,Object> paramMap);
}