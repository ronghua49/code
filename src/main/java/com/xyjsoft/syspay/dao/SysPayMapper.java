package com.xyjsoft.syspay.dao;

import java.util.List;

import com.xyjsoft.core.base.MyBatisDao;
import com.xyjsoft.syspay.model.SysPay;

/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPayMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:11
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysPayMapper  extends MyBatisDao<String, SysPay>{

	/**
	 * 添加支付宝微信支付设置表
	 * @param record
	 * @return
	 */
    int add(SysPay record);

    /**
     * 删除支付宝微信支付设置表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改支付宝微信支付设置表
     * @param record
     * @return
     */
    int update(SysPay record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    SysPay findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysPay> findPage();

    List<SysPay> get();
    
}