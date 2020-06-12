package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

/**
 * @author: haohua.rong
 * @description:
 * @contact: 13512835407@163.com
 * @date: 2020/4/22
 */
@Data
public class BalatypeDto {
    private String pk_org;
    private String code;
    private String name;
    private String netbankflag;
    private String dataoriginflag;
    private String fundtype;
    private String cash;

}