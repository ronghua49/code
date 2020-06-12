package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @author: haohua.rong
 * @description:
 * @contact: 13512835407@163.com
 * @date: 2020/4/21
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class VoucherHeadDto {

    private String pk_voucher;
    private String pk_vouchertype;
    private String year;
    private String pk_system;
    private String voucherkind;
    private String pk_accountingbook;
    private String discardflag;
    private String period;
    private String no;
    private String attachment;
    private String prepareddate;
    private String pk_prepared;
    private String pk_casher;
    private String signflag;
    private String pk_checked;
    private String tallydate;
    private String pk_manager;
    private String memo1;
    private String memo2;
    private String reserve1;
    private String reserve2;
    private String siscardflag;
    private String pk_org;
    private String pk_org_v;
    private String pk_group;
    private DetailsDto details;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetailsDto {

        @XmlElement(name = "item")
        private List<DebitItem> debitItem;

        @XmlElement(name = "item")
        private List<CreditItem> creditItem;

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class CashFlow {
            private cashFlowItem item;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class cashFlowItem {

                private String m_pk_currtype;
                private String money;
                private String moneyglobal;
                private String moneygroup;
                private String moneymain;
                private String pk_cashflow;
                private String pk_innercorp;
            }

        }

        /**
         * 借方分录
         */
        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class DebitItem {

            private String detailindex;
            private String explanation;
            private String verifydate;
            private String price;
            private String excrate2;
            private String debitquantity;
            private String debitamount;
            private String localdebitamount;
            private String groupdebitamount;
            private String globaldebitamount;
            private String pk_currtype;
            private String pk_accasoa;
            private String pk_unit;
            private String pk_unit_v;
            private String accsubjcode;

            private String creditamount;
            private String groupcreditamount;
            private String localcreditamount;

            @XmlElementWrapper(name = "ass")
            private List<ItemD> item;
            private CashFlow cashFlow;
            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class ItemD {
                private String pk_Checktype;
                private String pk_Checkvalue;
            }


        }

        /**
         * 贷方分录
         */
        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class CreditItem {
            private String creditquantity;
            private String creditamount;
            private String localcreditamount;
            private String groupcreditamount;
            private String globalcreditamount;
            private String detailindex;
            private String explanation;
            private String verifydate;
            private String price;
            private String excrate2;
            private String pk_currtype;
            private String pk_accasoa;
            private String pk_unit;
            private String pk_unit_v;
            private String accsubjcode;

            private String debitquantity;
            private String debitamount;
            private String localdebitamount;
            private String groupdebitamount;
            private String globaldebitamount;

            @XmlElementWrapper(name = "ass")
            private List<DebitItem.ItemD> item;
            private CashFlow cashFlow;
        }
    }
}