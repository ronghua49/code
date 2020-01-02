package com.xyjsoft.syspay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyjsoft.core.base.MyBatisDao;
import com.xyjsoft.core.base.impl.AbstractManagerImpl;
import com.xyjsoft.core.query.PageList;
import com.xyjsoft.core.query.QueryFilter;
import com.xyjsoft.core.page.MybatisPageHelper;
import com.xyjsoft.core.page.PageRequest;
import com.xyjsoft.core.page.PageResult;
import com.xyjsoft.syspay.dao.SysPayRecordMapper;
import com.xyjsoft.syspay.model.SysPayRecord;
import com.xyjsoft.syspay.service.SysPayRecordService;

/**
 * ---------------------------
 * 支付信息表 (SysPayRecordServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class SysPayRecordServiceImpl extends AbstractManagerImpl<String, SysPayRecord> implements SysPayRecordService {

	@Autowired
	private SysPayRecordMapper sysPayRecordMapper;

	@Override
	public int save(SysPayRecord record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysPayRecordMapper.add(record);
		}
		return sysPayRecordMapper.update(record);
	}

	@Override
	public int delete(SysPayRecord record) {
		return sysPayRecordMapper.delete(record.getId());
	}

	@Override
	public int delete(List<SysPayRecord> records) {
		for(SysPayRecord record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysPayRecord findById(Long id) {
		return sysPayRecordMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysPayRecordMapper);
	}
	
	@Override
	protected MyBatisDao<String, SysPayRecord> getDao() {
		return sysPayRecordMapper;
	}

	@Override
	public SysPayRecord findByCode(String code) {
		SysPayRecord sysPayRecord = sysPayRecordMapper.findByCode(code);
		return sysPayRecord;
	}

	@Override
	public List verifyPayType(String code) {
		List list = new  ArrayList<>();
		Boolean flag = false;
		for (int i = 0; i < 3; i++) {
			SysPayRecord sysPayRecord = findByCode(code);
			if (sysPayRecord != null) {
				if ("1".equals(sysPayRecord.getCodestate())) {
					flag = true;
					list.add(true);
					return list;
				}
			}
			try {
				Thread.currentThread().sleep(700);// 毫秒
			} catch (Exception e) {
				throw new RuntimeException("线程异常");
			}
		}
		list.add(false);
		return list;
	}
	
	@Override
	public PageList<SysPayRecord> query(QueryFilter queryFilter) throws Exception {
		return super.query(queryFilter);
	}

	@Override
	public int deleteT(List<Long> ids) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
