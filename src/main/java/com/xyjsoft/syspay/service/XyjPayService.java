package com.xyjsoft.syspay.service;

import java.util.Map;

import com.alipay.api.response.AlipayTradePayResponse;
import com.xyjsoft.syspay.model.SysPayRecord;

public interface XyjPayService {

	void rejectMethodZFB(Map<String, String> params);
	void Trade_pay(SysPayRecord sysPayRecord,AlipayTradePayResponse response);
	void swiftpassTrade_pay(SysPayRecord sysPayRecord, Map<String, String> resultMap);
	void rejectMethodWX(Map jsona);

}
