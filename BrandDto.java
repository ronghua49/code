package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @author: haohua.rong
 * @description:
 * @contact: 13512835407@163.com
 * @date: 2020/5/25
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BrandDto {

    private AddressVo address;
    private Orgvo orgvo;
    private Liabilitycenter liabilitycenter;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Liabilitycenter {
        private String pk_controlarea;
        private String pk_fatherorg;
    }


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AddressVo {
        private String code;
        private String country;
        private String province;
        private String city;
        private String vsection;
        private String detailinfo;
        private String postcode;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Orgvo {
        private String code;
        private String name;
        private String shortname;
        private String mnecode;
        private String pk_fatherorg;
        private String pk_group;
        private String enablestate;
        private String entitytype;
        private String principal;
        private String tel;
        private String address;
        private String memo;
        private String pk_corp;
        private String pk_accperiodscheme;
        private String pk_currtype;
        private String pk_exratescheme;
        private String workcalendar;
        private String countryzone;
        private String pk_format;
        private String pk_timezone;
        private String taxcode;
        private String pk_controlarea;
        private String organizationcode;
        private String ncindustry;

    }


}