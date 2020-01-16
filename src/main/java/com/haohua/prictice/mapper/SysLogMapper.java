package com.haohua.prictice.mapper;
import com.haohua.prictice.SysLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface SysLogMapper extends MyBatisDao<String, SysLog>{
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
    
    List<SysLog> findPage();
    
    List<SysLog> findPageByUserName(@Param(value = "userName") String userName);
}