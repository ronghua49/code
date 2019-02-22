package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2019/2/20
 */

import com.haohua.Dto.Order;
import com.haohua.exception.BusyException;
import com.haohua.exception.LackOfGoodsException;
import com.haohua.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 悲观锁，单线程访问，线程同步(或者用数据库锁也可以实现)
     * @return
     */
    @GetMapping("/huawei")
    public  synchronized  Order qianggou()  {
       Order order =  productService.getOrder();
       if(order!=null){
           return order;
       }else {
        throw new LackOfGoodsException();
       }
    }
    /**
     * 乐观锁实现
     * @return
     */
    @GetMapping("/huawei2")
    public  Order qianggou2() throws BusyException {

        Order order =  productService.getOrder2();
        if(order!=null){
            return order;
        }else {
            throw new LackOfGoodsException();
        }
    }

}
