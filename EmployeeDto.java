package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto {

    private String pk_org;

    private String code;

    private String name;

    private String mobile;


    private String pk_country;

    private String pk_timezone;

    private String pk_format;

    private String usedname;
    private String birthdate;
    private String sex;
    private String idtype;
    private String id;
    private String mnecode;
    private String joinworkdate;
    private Addressvo addressvo;
    private String addr;
    private String officephone;
    private String homephone;
    private PsnjobsDto psnjobs;

    private String pk_group;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Addressvo {
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
    public static class PsnjobsDto {
        private XmlItem item;

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class XmlItem {
            private String pk_group;
            private String pk_org;
            private String psncode;
            private String pk_psncl;
            private String pk_dept;
            private String ismainjob;
            private String indutydate;
        }
    }
}
