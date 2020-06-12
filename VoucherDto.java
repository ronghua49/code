package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

@Data
public class VoucherDto {
    private String voucherno;
    private String vouchertype;
    private String year;
    private String pk_system;
    private String voucherkind;
    private String pk_accountingbook;
    private String period;
    private String prepareddate;
    private String pk_prepared;
    private String pk_org;
    private String detailindex;
    private String pk_accasoa;
    private String explanation;
    private String pk_currtype;
    private String pk_Checktype;
    private String assid;
    private String debitamount;
    private String creditamount;
    private String localcreditamount;
    private String localdebitamount;

}
