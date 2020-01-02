package com.xyjsoft.syspay.feign;

import org.springframework.stereotype.Component;

import com.xyjsoft.core.exception.XyjException;

@Component
public class PayOrderfoFeignFallback implements PayOrderFeignService{

	@Override
	public boolean payBySuccess(String code, String sign,String type) {
		throw new XyjException("服务器开小差了， 请稍后重试。");
	}

	@Override
	public boolean JYpayBySuccess(String code, String sign, String type) {
		throw new XyjException("服务器开小差了， 请稍后重试。");
	}

}
