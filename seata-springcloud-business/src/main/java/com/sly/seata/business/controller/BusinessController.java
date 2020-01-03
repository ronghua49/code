package com.sly.seata.business.controller;

import com.sly.seata.business.service.BusinessService;
import com.sly.seata.business.service.impl.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2020/1/3 14:01
 */
@RequestMapping("/business")
@RestController
public class BusinessController {

    @Autowired
    private BusinessServiceImpl businessService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Map<String, Object> purchase(@RequestParam("accountId") String accountId, @RequestParam("orderId") String orderId,
                                        @RequestParam("storageId") String storageId) {
        return businessService.purchase(accountId, orderId, storageId);

    }


}
