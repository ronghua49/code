package com.shuyue.finance_server.dto.request.thirdparty.yongyou;

import lombok.Data;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BillDto {

    @XmlAttribute
    private String id;

    @XmlElement(name = "billhead")
     private CustomerDto customerDto;

    @XmlElement(name = "billhead")
    private EmployeeDto employeeDto;

    @XmlElement(name = "billhead")
    private SupplierDto supplierDto;

    @XmlElement(name = "billhead")
    private DeptDto deptDto;

    @XmlElement(name = "billhead")
    private BalatypeDto balatypeDto;

    @XmlElement(name = "billhead")
    private BrandDto brandDto;

    @XmlElement(name = "billhead")
    private BankDto bankDto;

    @XmlElement(name = "billhead")
    private BankAccountDto bankAccountDto;

    @XmlElement(name = "billhead")
    private IncomeAndExpenditureDto incomeAndExpenditureDto;
}