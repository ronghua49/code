package com.haohua.Dto;    /*
 * @author  Administrator
 * @date 2019/2/20
 */

import java.math.BigDecimal;

public class Order {

    private String proName;

    private BigDecimal proPrice;


    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }
}
