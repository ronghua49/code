package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author: haohua.rong
 * @description:
 * @contact: 13512835407@163.com
 * @date: 2020/5/26
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountDto {
    private String accclass;
    private String pk_org;
    private String pk_group;
    private String accnum;
    private String accname;
    private String code;
    private String name;
    private String pk_bankdoc;
    private String pk_banktype;
    private String mnecode;
    private String accopendate;
    private String arapprop;
    private String netqueryflag;
    private String accattribute;
    private String genebranprop;
    private String corrgeneaccount;
    private String groupaccount;
    private String accstate;
    private String accountproperty;
    private String address;
    private String contactpsn;
    private String tel;
    private String memo;
    private String accxhdate;
    private String pk_netbankinftp;
    private String areacode;
    private String combinenum;
    private String orgnumber;
    private String bankarea;
    private String province;
    private String city;
    private String customernumber;
    private String issigned;
    private String financeorg;
    private String controlorg;
    private String enablestate;
    private String combineaccname;
    private String qrybalanceitf;

@XmlElementWrapper(name ="bankaccsub" )
    private List<Item> item;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        private String pk_currtype;
        private String code;
        private String name;
        private String acctype;
        private String isconcerted;
        private String concertedmny;
        private String fronzenstate;
        private String fronzenmny;
        private String frozendate;
        private String defrozendate;
        private String overdraftmny;
        private String overdrafttype;
        private String payarea;
        private String istrade;

    }

}