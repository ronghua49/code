package com.xyjsoft.syspay.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name="xyj-base2pay-master",fallback=PayOrderfoFeignFallback.class)
public interface PayOrderFeignService{
	@RequestMapping(value="/appOrderRecharge/payBySuccess",method=RequestMethod.POST)
	public boolean payBySuccess(@RequestParam(value = "code") String code,
			@RequestParam(value = "sign") String sign,
			@RequestParam(value = "type") String type);
	
	@RequestMapping(value="/appOrder/payBySuccess",method=RequestMethod.POST)
	public boolean JYpayBySuccess(@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "sign", required = true) String sign,
			@RequestParam(value = "type", required = true) String type);

}
