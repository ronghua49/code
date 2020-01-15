package com.sly.seata.business.service.impl;

import com.sly.seata.business.service.BusinessFeignService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2020/1/15 11:03
 */
@Component
public class BusinesshystrixImpl implements BusinessFeignService {
    @Override
    public String purchase(String accountId, String orderId, String storageId) {
      return  "httpFeign 调用失败！";
    }

    @Override
    public String getTest(String name) {
        return "httpFeign 调用失败";
    }
}
