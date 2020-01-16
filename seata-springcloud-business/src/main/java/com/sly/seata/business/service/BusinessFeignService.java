package com.sly.seata.business.service;

import com.sly.seata.business.service.impl.BusinesshystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-springcloud-account",fallback = BusinesshystrixImpl.class)
public interface BusinessFeignService {

    @RequestMapping(method = RequestMethod.POST, value = "/account/insert")
    String purchase(@RequestParam("accountId") String accountId,
                                 @RequestParam("orderId") String orderId, @RequestParam("storageId") String storageId);

    @GetMapping("/test")
    String getTest(@RequestParam String name);
}
