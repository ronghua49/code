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
public class BankDto {
    private String pk_group;
    private String pk_org;
    private String code;
    private String name;
    private String shortname;
    private String pk_banktype;
    private String pk_fundorg;
    private String pk_fatherbank;
    private String mnecode;
    @XmlElement(name = "地址")
    private BankAddressVo bankAddressVo;
    private String address;
    private String def1;
    private String def2;
    private String def3;
    private String def4;
    private String def5;
    private String creator;
    private String creationtime;
    private String modifier;
    private String modifiedtime;
    private List<ItemVo> itemVos;
    private String innercode;
    private String seq;
    private String dataoriginflag;
    private String pk_workcalendar;
    private String areacode;
    private String bankarea;
    private String orgnumber;
    private String province;
    private String city;
    private String customernumber;
    private String issigned;
    private String pk_country;
    private String enablestate;
    private String swiftcode;
    private String iscustbank;
    private String pk_combine;
    private String pcombinename;
    private String isiban;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BankAddressVo {
        private String code;
        private String country;
        private String province;
        private String city;
        private String vsection;
        private String detailinfo;
        private String postcode;
        private String status;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemVo {
        private String dataoriginflag;
        private List<Linkman> linkman;

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Linkman {
            private String address;
            private String anniversary;
            private String birthday;
            private String cell;
            private String code;
            private String email;
            private String fax;
            private String memo;
            private String name;
            private String phone;
            private String postcode;
            private String sex;
            private String vjob;
            private String webaddress;

        }

    }

}
