package com.haohua.prictice.service;

import com.haohua.prictice.SysLog;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("sysLogServiceImpl")
public class SysLogServiceImpl implements SysLogService {

	public int save(SysLog record) {
		/*		String methodName = record.getMethod();
		if(!(methodName.contains("get") 
				|| methodName.contains("find") 
				|| methodName.contains("query")
				|| methodName.contains("list")
				|| methodName.contains("updateState"))) {*/
		if (true) {
			//保存InfluxDB
			Map<String, String> tags = new HashMap<String, String>();
			tags.put("userName", record.getUserName());
			tags.put("ip", record.getIp());
			Map<String, Object> fields = new HashMap<>();
			fields.put("operation", record.getOperation());
			fields.put("method", record.getMethod());
			fields.put("params", record.getParams());
			fields.put("error_message", record.getErrorMessage());
			// 存数据库 或者 时序数据库
			return 1;
		} else {
			// 存入mysql 数据库
		}
		return 1;
	}



	@Override
	public void createSuccessLog(String message) {

	}

	@Override
	public void createErrorLog(String message) {

	}
}
