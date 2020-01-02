package com.xyjsoft.syspay.dao;

import java.util.List;

import com.xyjsoft.core.base.MyBatisDao;
import com.xyjsoft.syspay.model.SysPayRecord;

/**
 * ---------------------------
 * 支付信息表 (SysPayRecordMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysPayRecordMapper  extends MyBatisDao<String, SysPayRecord>{

	/**
	 * 添加支付信息表
	 * @param record
	 * @return
	 */
    int add(SysPayRecord record);

    /**
     * 删除支付信息表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改支付信息表
     * @param record
     * @return
     */
    int update(SysPayRecord record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysPayRecord findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysPayRecord> findPage();

	SysPayRecord findByCode(String code);
    
}