package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.Sale;

public class Huawei  implements Sale {
    @Override
    public void sale() {
        System.out.println("华为销售手机");
    }
    @Override
    public int price(int price) {
        return (price+100);
    }

}
