package com.xyjsoft.syspay.service;
import com.xyjsoft.core.base.Manager;
import com.xyjsoft.core.service.CurdService;
import com.xyjsoft.syspay.model.SysPay;

/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPayService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:11
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysPayService extends CurdService<SysPay>,Manager<String,SysPay>  {

	SysPay get();

}
