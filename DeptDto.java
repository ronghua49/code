package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;



/**
 * @author: haohua.rong
 * @description:
 * @contact: 13512835407@163.com
 * @date: 2020/4/22
 */
@Data
public class DeptDto {

    private String code;
    private String name;
    private String shortname;
    private String mnecode;
    private String pk_fatherorg;
    private String pk_group;
    private String pk_org;
    private String depttype;
    private String deptlevel;
    private String enablestate;
    private String createdate;
    private String deptcanceldate;
    private String hrcanceled;
    private String displayorder;
    private String orgtype13;
    private String orgtype17;
    private String principal;
    private String tel;
    private String memo;

}