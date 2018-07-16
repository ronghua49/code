package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.Sale;

public class Apple implements Sale {
    @Override
    public void sale() {
        System.out.println("Apple 公司销售手机一部");
    }
    @Override
    public int price(int price){
        return (price+5000);
    }
}
