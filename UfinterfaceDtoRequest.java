package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import com.shuyue.finance_server.dto.response.thirdparty.YonyouVoucherResponse;
import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Data
@XmlRootElement(name = "ufinterface")
@XmlAccessorType(XmlAccessType.FIELD)
public class UfinterfaceDtoRequest {

    @XmlAttribute
    private String account;

    @XmlAttribute
    private String billtype;

    @XmlAttribute
    private String businessunitcode;

    @XmlAttribute
    private String filename;

    @XmlAttribute
    private String groupcode;

    @XmlAttribute
    private String isexchange;

    @XmlAttribute
    private String orgcode;

    @XmlAttribute
    private String receiver;

    @XmlAttribute
    private String replace;

    @XmlAttribute
    private String roottag;

    @XmlAttribute
    private String sender;

    @XmlAttribute
    private String successful;

    @XmlElement
    private BillDto bill;

    @XmlElementWrapper(name = "voucher")
    private  List<VoucherHeadDto> voucher_head;

    @XmlElement
    private YonyouVoucherResponse sendresult;


}
