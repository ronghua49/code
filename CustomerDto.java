package com.shuyue.finance_server.dto.request.thirdparty.yongyou;
import lombok.Data;

@Data
public class CustomerDto {

    private String pk_group;

    private String pk_org;

    private String code;

    private String name;

    private String pk_custclass;

    private String pk_country;

    private String pk_timezone;

    private String pk_format;
    private String shortname;
    private String mnecode;
    private String pk_areacl;
    private String issupplier;
    private String pk_supplier;
    private String custprop;
    private String pk_financeorg;
    private String taxpayerid;
    private String pk_customer_main;
    private String trade;
    private String isfreecust;
    private String registerfund;
    private String legalbody;
    private String ecotypesincevfive;
    private String pk_custtaxes;
    private String corpaddress;
    private String url;
    private String tel1;
    private String tel2;
    private String tel3;
    private String fax1;
    private String fax2;
    private String email;
    private String memo;
    private String frozenflag;
    private String enablestate;
}
