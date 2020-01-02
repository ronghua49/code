package com.xyjsoft.syspay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyjsoft.core.base.MyBatisDao;
import com.xyjsoft.core.base.impl.AbstractManagerImpl;
import com.xyjsoft.core.page.MybatisPageHelper;
import com.xyjsoft.core.page.PageRequest;
import com.xyjsoft.core.page.PageResult;
import com.xyjsoft.syspay.dao.SysPayMapper;
import com.xyjsoft.syspay.model.SysPay;
import com.xyjsoft.syspay.service.SysPayService;

/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPayServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class SysPayServiceImpl extends AbstractManagerImpl<String, SysPay> implements SysPayService {

	@Autowired
	private SysPayMapper sysPayMapper;

	@Override
	public int save(SysPay record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysPayMapper.add(record);
		}
		return sysPayMapper.update(record);
	}

	@Override
	public int delete(SysPay record) {
		return sysPayMapper.delete(record.getId());
	}

	@Override
	public int delete(List<SysPay> records) {
		for(SysPay record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysPay findById(Long id) {
		return sysPayMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysPayMapper);
	}
	
	@Override
	protected MyBatisDao<String, SysPay> getDao() {
		return sysPayMapper;
	}

	@Override
	public SysPay get() {
		List<SysPay> sysPays = sysPayMapper.get();
		if(sysPays.size() == 0) {
			return null;
		}else{
			return sysPays.get(0);
		}
	}

	@Override
	public int deleteT(List<Long> ids) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
