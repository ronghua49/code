package com.sly.seata.business.controller;
import com.sly.seata.business.service.BusinessFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private BusinessFeignService feignService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    @GlobalTransactional
    public String purchase() {
        String accountId = "1";

        String orderId = "2";
        String storageId = "3";
        String purchase = feignService.purchase(accountId, orderId, storageId);
        return purchase;

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @GlobalTransactional(timeoutMills = 300000)
    public String test() {
        String purchase = feignService.getTest("hahahah");
        int i = 1/0;
        return purchase;

    }



}
