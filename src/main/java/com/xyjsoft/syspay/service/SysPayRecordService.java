package com.xyjsoft.syspay.service;
import java.util.List;
import java.util.Map;

import com.xyjsoft.core.base.Manager;
import com.xyjsoft.core.service.CurdService;
import com.xyjsoft.syspay.model.SysPayRecord;

/**
 * ---------------------------
 * 支付信息表 (SysPayRecordService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysPayRecordService extends CurdService<SysPayRecord>,Manager<String,SysPayRecord>  {


	List verifyPayType(String code);

	SysPayRecord findByCode(String code);

}
